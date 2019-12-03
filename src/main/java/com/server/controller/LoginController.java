package com.server.controller;


import com.alibaba.fastjson.JSON;
import com.core.util.*;
import com.server.pojo.DeptAdmin;
import com.server.pojo.PropertyInfo;
import com.server.pojo.UserInfoDo;
import com.server.service.UserService;
import net.sf.json.JSONObject;
import com.core.config.Constant;
import com.core.helper.UserHelper;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Description 钉钉H5页面授权登录
 * @Author xg.chen
 * @Date 9:13 2019/5/9
 **/
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    /**
     * 登录钉钉授权页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "login";
    }

    /**
     * 计算当前请求的jsapi签名数据
     * 如果签名数据是用过ajax异步请求的话，签名中的url必须是给用户展示页面的url
     * @param request
     * @return
     */
    @PostMapping("/config")
    @ResponseBody
    public String getConfig(@RequestParam("url") String urlString,HttpServletRequest request) {
        String queryString = request.getQueryString();

        String queryStringEncode = null;
        String url;
        if (queryString != null) {
            queryString = URLDecoder.decode(queryString);
            url = urlString + "?" + queryStringEncode;
        } else {
            url = urlString;
        }

        //确认url与配置的应用首页地址一致
        logger.debug("index url:"+url);

        //随机字符串
        String nonceStr = UUID.randomUUID().toString();
        long timeStamp = System.currentTimeMillis() / 1000;
        String signedUrl = url;
        String accessToken = null;
        String ticket = null;
        String signature = null;

        try {
            accessToken = AccessTokenUtil.getToken();

            ticket = AccessTokenUtil.getJsapiTicket(accessToken);
            signature = AccessTokenUtil.sign(ticket, nonceStr, timeStamp, signedUrl);

        } catch (OApiException e) {
            e.printStackTrace();
        }

        Map<String, Object> configValue = new HashMap<>();
        configValue.put("jsticket", ticket);
        configValue.put("signature", signature);
        configValue.put("nonceStr", nonceStr);
        configValue.put("timeStamp", timeStamp);
        configValue.put("corpId", Constant.CORP_ID);
        configValue.put("agentId", Constant.AGENT_ID);

        String config = JSON.toJSONString(configValue);
        return config;
    }

    /**
     * @Description 获取用户信息
     * @Author xg.chen
     * @Date 11:04 2019/5/10
     **/
    @GetMapping("/userinfo")
    @ResponseBody
    public String getUserInfo (@RequestParam("code") String code, @RequestParam("corpid") String corpid) {
        logger.debug("code:" + code + " cropId:" + corpid);
        String userJson = null;
        String json = null;
        try {
            //获取accessToken,注意正是代码要有异常流处理
            String accessToken = AccessTokenUtil.getToken();
            CorpUserDetail user = UserHelper.getUser(accessToken, UserHelper.getUserInfo(accessToken, code).getUserid());
            userJson = JSON.toJSONString(user);
            JSONObject obj = new JSONObject().fromObject(userJson);//将json字符串转换为json对象
            UserInfoDo userInfo = (UserInfoDo)JSONObject.toBean(obj,UserInfoDo.class);//将建json对象转换为Person对象
            //将用户信息放入缓存
            session.setAttribute("userInfo", userInfo);
            json = JSONUtils.beanToJson(userInfo);
            logger.debug("userInfo:"+json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * @Description 泛微分发盘点信息
     * @Author xg.chen
     * @Date 13:29 2019/11/26
    **/
    @PostMapping("/sendInfo")
    @ResponseBody
    public String sendInfo(@PathParam("id")String id) throws IOException {
        //1.根据总表的Id获取总表中成本中心
        PropertyInfo propertyInfo = userService.primary_getPropertyInfoTotalById(id);
        //2.根据获取到的总表的中成本中心去获取维护的管理员信息
        String loginId = "";
        if (propertyInfo!=null) {
            DeptAdmin deptAdmin = userService.primary_getDeptAdminByCostCen(propertyInfo.getCostCenter());
            if (deptAdmin!=null) {
                loginId = deptAdmin.getJobnumber();
            } else {
                return "error";
            }
        }
        //3.根据获取到的管理员数据分发到指定的人
        UserInfoDo userInfoDo = userService.primary_getUserInfoByLoginId(loginId);
        if (userInfoDo != null) {
            String string = AccessTokenUtil.sendDDMessage(Constant.AGENT_ID, userInfoDo.getUserid(), Constant.MESSAGE_URL);
            logger.debug("[sendDDMessage]:" + string);
        }
        return "success";
    }

    /**
     * 资产盘点首页---资产盘点信息列表
     * @param jobnumber
     * @param modle
     * @return
     */
    @GetMapping("/loginIndex")
    public String loginIndex(@PathParam("jobnumber") String jobnumber,Model modle) {
        //根据userid和jobnumber获取资产盘点信息
        //先去泛微的数据判断当前盘点人是否维护
        DeptAdmin deptAdmin = userService.primary_getDeptAdminByJobnumber(jobnumber);
        if (deptAdmin == null) {
            return "error";
        }
        //如果维护了就根据维护的成本中心获取对应的资产盘点列表
        List<PropertyInfo> propertyList = userService.primary_getPropertyList(deptAdmin.getCostCenter());
        modle.addAttribute("propertyList",propertyList);
        return "index";
    }

    /**
     * 扫描资产条码或者输入资产编码来获取资产明细
     * @param propertyId
     * @param model
     * @return
     */
    @GetMapping("/scanInfo")
    public String scanInfo(@PathParam("propertyId") String propertyId, Model model){
        //资产条码处理：公司编号+资产编号
        //取后面7位数
        String[] strs = propertyId.split("-");
        if (strs.length==0) {
            return "error_1";
        }
        String propertyId1 = strs[1].toString().trim();
        UserInfoDo userInfoDo = (UserInfoDo) session.getAttribute("userInfo");
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setJobnumber(userInfoDo.getJobnumber());
        propertyInfo.setPropertyId("00000"+propertyId1);
        //获取固定资产明细信息
        PropertyInfo propertyInfo1 = userService.primary_getPropertyInfoByPropertyId(propertyInfo);
        //没有获取到数据返回一个错误错误页面
        if (propertyInfo1 == null) {
            return "error_1";
        }
        //获取到数据则返回动态页面
        if (StringUtils.isNotEmpty(propertyInfo.getPicUrl())) {
            propertyInfo1.setPicUrl("/dd/images/"+propertyInfo1.getPicUrl().replace("/files/",""));
        }
        model.addAttribute("propertyInfo",propertyInfo1);
        return "propertyInfo";
    }

    /**
     * 固定资产明细资产详情页
     * @param id
     * @return
     */
    @GetMapping("/propertyInfo")
    public String propertyInfo(@PathParam("id") String id, Model model) {
        //获取固定资产明细信息
        PropertyInfo propertyInfo = userService.primary_getPropertyInfoById(id);
        //图片显示处理
        if (StringUtils.isNotEmpty(propertyInfo.getPicUrl())) {
            propertyInfo.setPicUrl("/dd/images/"+propertyInfo.getPicUrl().replace("/files/",""));
        }
        model.addAttribute("propertyInfo",propertyInfo);
        return "propertyInfo";
    }

    /**
     * @Description 提交资产修改数据
     * @Author xg.chen
     * @Date 9:45 2019/5/14
     **/
    @PostMapping("/submitData")
    @ResponseBody
    public R submitData(PropertyInfo propertyInfo) {
        int count = userService.primary_updatePropertyInfoById(propertyInfo);
        if (count == 0) {
            return R.error("数据更新失败：数据出错");
        } else if (count == -1) {
            return R.error("数据更新失败：SAP回写错误");
        }
        return R.ok();
    }

    /**
     * @Description 上传文件
     * @Author xg.chen
     * @Date 11:34 2019/5/15
     **/
    @PostMapping("/uploadFile")
    @ResponseBody
    public R uploadFile(HttpServletRequest request, @PathParam("id")String id) {
        //上传数据库
        String flagFile = uploadUtils.uploadImageFiles(request, id, "0");
        if (flagFile.equals("error_1")) {
            return R.error("上传图片不合法");
        } else if (flagFile.equals("error_2")) {
            return R.error("上传的图片最大不能大于10Mb");
        }
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setId(id);
        propertyInfo.setPicUrl(flagFile);
        int count = userService.primary_updatePropertyInfoById4pic(propertyInfo);
        if (count == 0) {
            return R.error("图片上传失败");
        }
        return R.ok();
    }
}
