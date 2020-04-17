package com.server.controller;

import com.core.config.URLConstant;
import com.core.util.EncryptUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.taobao.api.ApiException;
import com.core.util.AccessTokenUtil;
import com.core.util.ServiceResult;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 企业内部E应用Quick-Start示例代码 实现了最简单的免密登录（免登）功能
 * @Author xg.chen
 * @Date 13:54 2019/5/5
 **/
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 欢迎页面,通过url访问，判断后端服务是否启动
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        String data = "2020/03/25-2020/03/26:上海,2020/03/26-2020/03/27:湖州,2020/03/27-2020/03/28:杭州";
        String key = "8B145089E1223415";
        String encrypt = EncryptUtil.encrypt(data, key);
        encrypt = EncryptUtil.replaceBlank(encrypt);
        logger.debug("加密前：" + data);
        logger.debug("加密后：" + encrypt);
        String desEncrypt = EncryptUtil.desEncrypt(encrypt, key);
        logger.debug("解密后：" + desEncrypt);
        return encrypt;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(HttpServletRequest request){
        String userid = request.getParameter("userid");
        System.out.print("userticket:"+userid);
        return "getUserInfo";
    }

    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param requestAuthCode 免登临时code
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult login(@RequestParam(value = "authCode") String requestAuthCode) {
        //获取accessToken,注意正是代码要有异常流处理
        String accessToken = AccessTokenUtil.getToken();

        //获取用户信息
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        //3.查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        String userId = response.getUserid();

        HashMap<String,String> userInfo = getUserName(accessToken, userId);
        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userName", userInfo.get("name"));
        resultMap.put("jobNumber", userInfo.get("jobnumber"));
        ServiceResult serviceResult = ServiceResult.success(resultMap);
        return serviceResult;
    }

    /**
     * 获取用户姓名
     *
     * @param accessToken
     * @param userId
     * @return
     */
    private HashMap<String,String> getUserName(String accessToken, String userId) {
        HashMap<String,String> userInfo = new HashMap<>();
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);

            userInfo.put("name",response.getName());
            userInfo.put("jobnumber",response.getJobnumber());

            return userInfo;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}


