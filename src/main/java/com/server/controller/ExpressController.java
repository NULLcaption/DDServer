package com.server.controller;

import com.core.helper.LJFanweiUtil;
import com.core.util.R;
import com.server.pojo.DeptAdmin;
import com.server.pojo.ExpressDo;
import com.server.pojo.UserInfoDo;
import com.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * @Description 基于钉钉云桥的快递登记控制类
 * @Author xg.chen
 * @Date 16:26 2020/4/15
**/
@Controller
public class ExpressController {
    private static final Logger logger = LoggerFactory.getLogger(ExpressController.class);

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    /**
     * @Description 首页
     * @Author xg.chen
     * @Date 16:28 2020/4/15
    **/
    @GetMapping("/exprIndex")   
    public String expressIndex(HttpServletRequest request, Model model){
        String userid = request.getParameter("userid");
//        String userid = "083201343237723187";
        if (userid == null) {
            return "error_1";
        }
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        if (userInfoDo == null) {
            userInfoDo = userService.secondary_getUserInfoByUserId(userid);
            //获取OA中的组织
            DeptAdmin deptAdmin = userService.primary_getDeptAdminByOutUserId(userInfoDo.getOutuserid());
            userInfoDo.setOrgId(deptAdmin.getOrgId());
            userInfoDo.setOrgName(deptAdmin.getOrgName());
            session.setAttribute("userInfo_",userInfoDo);
        }
        model.addAttribute("userInfoDo",userInfoDo);
        return "expressIndex";
    }

    /**
     * 信息登记明细列表
     * @param index
     * @return
     */
    @GetMapping("expressInfo")
    public String expressInfo(String index, Model model){
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        if (index.equals("2")) {
            List<ExpressDo> expressDoList = userService.primary_getExpressInfoByUserId(userInfoDo.getOutuserid());
            for(ExpressDo expressDo : expressDoList) {
                if(expressDo.getExpressCompany().equals("0")){
                    expressDo.setExpressCompany("顺丰速运");
                }
                if(expressDo.getExpressCompany().equals("1")){
                    expressDo.setExpressCompany("EMS");
                }
                if(expressDo.getExpressCompany().equals("2")){
                    expressDo.setExpressCompany("中国邮政");
                }
                if(expressDo.getExpressCompany().equals("3")){
                    expressDo.setExpressCompany("百世快递");
                }
                if(expressDo.getExpressCompany().equals("4")){
                    expressDo.setExpressCompany("速尔快递");
                }
            }
            model.addAttribute("expressDoList",expressDoList);
            return "expressTotal";
        }
        model.addAttribute("userInfoDo", userInfoDo);
        return "expressInfo";
    }

    /**
     * 保存数据
     * @param expressDo
     * @return
     */
    @PostMapping("/saveData")
    @ResponseBody
    public R saveData(ExpressDo expressDo) throws ParseException {
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        expressDo.setOrgId(userInfoDo.getOrgId());
        expressDo.setUserId(userInfoDo.getOutuserid());
        expressDo.setState("0");
        int count = userService.primary_createExpressIndfo4DD(expressDo);
        if (count == 1) {
           //插入成功后需要重构垃圾泛微的数据库
            int id = userService.primary_getExpressInfoId();
            logger.debug("insert id--->:"+id);
            String string = LJFanweiUtil.HttpFWWebService(id);
            if ("ERROR".equals(string)) {
                return R.error(-1,"LJ泛微重构数据库失败");
            }
            return R.ok();
        }
        return R.error(1,"数据保存失败");
    }

    /**
     * 寄件详情页面
     * @param id
     * @return
     */
    @GetMapping("/expressDetail")
    public String expressDetail(String id, Model mode){
        ExpressDo expressDo = userService.primary_getExpressInfoById(id);
        // 快递公司
        if(expressDo.getExpressCompany().equals("0")){
            expressDo.setExpressCompany("顺丰速运");
        }
        if(expressDo.getExpressCompany().equals("1")){
            expressDo.setExpressCompany("EMS");
        }
        if(expressDo.getExpressCompany().equals("2")){
            expressDo.setExpressCompany("中国邮政");
        }
        if(expressDo.getExpressCompany().equals("3")){
            expressDo.setExpressCompany("百世快递");
        }
        if(expressDo.getExpressCompany().equals("4")){
            expressDo.setExpressCompany("速尔快递");
        }
        // 费用归属
        if(expressDo.getCostBelonging().equals("0")){
            expressDo.setCostBelonging("快递费");
        }
        if(expressDo.getCostBelonging().equals("1")){
            expressDo.setCostBelonging("其他费用");
        }
        mode.addAttribute("expressDo",expressDo);
        return "expressDetail";
    }
}
