package com.server.controller;

import com.core.config.DataSourceType;
import com.core.util.JSONUtils;
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
import javax.websocket.Session;

/**
 * @Description 基于钉钉云桥的产品追溯控制类
 * @Author xg.chen
 * @Date 13:19 2020/3/3
**/
@Controller
public class TrceabilityController {
    private static final Logger logger = LoggerFactory.getLogger(TrceabilityController.class);

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    /**
     * @Description 首页
     * @Author xg.chen
     * @Date 11:04 2020/3/19
     **/
    @GetMapping("/prIndex")
    public String productIndex(HttpServletRequest request, Model model){
        String userid = request.getParameter("userid");
//        String userid = "083201343237723187";
        if (userid == null) {
            return "error_1";
        }
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        if (userInfoDo == null) {
            userInfoDo = userService.secondary_getUserInfoByUserId(userid);
            session.setAttribute("userInfo_",userInfoDo);
        }
        model.addAttribute("userInfoDo",userInfoDo);
        return "productIndex";
    }

    /**
     * 查询页面
     * @return
     */
    @GetMapping("/productIndexD")
    public String productIndexD(){
        return "productIndexS";
    }

    /**
     * 标准查询
     * @param dataCode 杯码或者箱码或者客户码。长度50，必填
     * @return
     */
    @GetMapping("/standard")
    @ResponseBody
    public String productTrceabilityS(String dataCode){
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        //非正常入口进入
        if (userInfoDo == null) {
            return "error:非正常入口进入";
        }
        if (dataCode.length()==16) {
            String date = dataCode.substring(0,8);//年月日
            String factory = dataCode.substring(8,9).toUpperCase();//工厂
            String time  = dataCode.substring(9,14);//时间
            String lineCode = dataCode.substring(14,15);//线别

        }//杯码
        if (dataCode.length()==25) {
            String date = dataCode.substring(0,8);//年月日
            String factory = dataCode.substring(8,9).toUpperCase();//工厂
            String time  = dataCode.substring(9,17);//时间
            String code = dataCode.substring(17,23);//流水码
            String lineCode = dataCode.substring(23,25);//线别

        }//箱码
        if (dataCode.length()==21) {
            String date = dataCode.substring(0,8);//年月日
            String factory = dataCode.substring(8,9).toUpperCase();//工厂
            String time  = dataCode.substring(9,14).toUpperCase();//字母（A/B/C…Z）+MMDD
            String code  = dataCode.substring(14,19);//流水码
            String lineCode = dataCode.substring(19,21);//线别

        }//手工箱码
        return JSONUtils.beanToJson(userInfoDo);
    }

    /**
     *
     * @param date 箱码上的生产日期及产地，长度9,必填
     * @param code 流水码,长度6,必填
     * @return
     */
    @PostMapping("/giftBox")
    @ResponseBody
    public String productTrceabilityG(String date, String code){
        return "";
    }
}
