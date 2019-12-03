package com.core.util;

import com.alibaba.fastjson.JSONObject;
import com.core.config.Constant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.oapi.lib.aes.DingTalkJsApiSingnature;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.JsapiTicket;
import com.dingtalk.open.client.api.service.corp.JsapiService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.core.config.URLConstant.URL_GET_TOKKEN;
/**
 * @Description token工具类
 * @Author xg.chen
 * @Date 16:25 2019/5/7
 **/
public class AccessTokenUtil {
    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

    /**
     * @Description 获取access_token工具类
     * access_token有效时间为7200，后期可以将其保存在redis中
     * @Author xg.chen
     * @Date 16:26 2019/5/7
     **/
    public static String getToken() throws RuntimeException {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(URL_GET_TOKKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(Constant.APP_KEY);
            request.setAppsecret(Constant.APP_SECRET);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();
            return accessToken;
        } catch (ApiException e) {
            bizLogger.error("getAccessToken failed", e);
            throw new RuntimeException();
        }

    }

    /**
     * 获取JSTicket，用于js签名认证
     * @param accessToken
     * @return
     */
    public static String getJsapiTicket(String accessToken) {
        String jsTicket = "";
        ServiceFactory serviceFactory;
        try {
            serviceFactory = ServiceFactory.getInstance();
            JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);

            JsapiTicket jsapiTicket = jsapiService.getJsapiTicket(accessToken, "jsapi");
            jsTicket = jsapiTicket.getTicket();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsTicket;
    }

    /**
     * 生成数字签名0
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws OApiException
     */
    public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws OApiException {
        try {
            return DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp, ticket);
        } catch (Exception ex) {
            throw new OApiException(0, ex.getMessage());
        }
    }

    /**
     * @Description 发送消息工具类
     * @Author xg.chen
     * @Date 16:26 2019/5/7
     **/
    public static String sendDDMessage(Long agentId,String userId,String messageUrl) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(userId);
        request.setAgentId(agentId);
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("link");
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msg.getLink().setTitle("固定资资产盘点");
        msg.getLink().setText("["+userId+"]固定资资产盘点");
        msg.getLink().setMessageUrl(messageUrl);
        msg.getLink().setPicUrl("test");
        request.setMsg(msg);

        try {
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request,AccessTokenUtil.getToken());
            String jsonObject = JSONObject.toJSONString(response);
            return jsonObject;
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return "";
    }

}
