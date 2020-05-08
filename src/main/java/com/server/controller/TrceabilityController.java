package com.server.controller;

import com.core.config.Constant;
import com.core.config.DataSourceType;
import com.core.util.JSONUtils;
import com.core.util.PageUtils;
import com.core.util.Query;
import com.core.util.R;
import com.server.pojo.*;
import com.server.service.UserService;
import org.apache.commons.lang.StringUtils;
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
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/proIndex")
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
    public String productTrceabilityS(String dataCode, Model model){
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        CodeInfoDo codeInfoDo = new CodeInfoDo();
        ProductInfoDo productInfoDo = new ProductInfoDo();
        //非正常入口进入
        if (userInfoDo == null) {
            return "error";
        }
        if (dataCode.length()==16) {//A
            session.setAttribute("SCode_",dataCode);
            codeInfoDo.setStatus("A");
            codeInfoDo.setDate(dataCode.substring(0, 8));//年月日
            codeInfoDo.setFactory(dataCode.substring(8, 9).toUpperCase());//工厂
            codeInfoDo.setTime(dataCode.substring(9, 14));//时间
            codeInfoDo.setLineCode(dataCode.substring(14, 15));//线别
            productInfoDo = userService.primary_getProductInfosByCode(codeInfoDo);
            if (productInfoDo != null) {
                QualityCheckingDo qualityCheckingDo = userService.thirdly_getQualityChecking(productInfoDo.getBatchNumber());
                if (StringUtils.isNotEmpty(qualityCheckingDo.getQualityChecking())) {
                    productInfoDo.setQurl(qualityCheckingDo.getQualityChecking());
                }
            }
        }//杯码
        if (dataCode.length()==25) {//B
            session.setAttribute("SCode_",dataCode);
            codeInfoDo.setStatus("B");
            codeInfoDo.setDate(dataCode.substring(0, 8));//年月日
            codeInfoDo.setFactory(dataCode.substring(8, 9).toUpperCase());//工厂
            codeInfoDo.setTime(dataCode.substring(9, 17));//时间
            codeInfoDo.setCode(dataCode.substring(17, 23));//流水码
            codeInfoDo.setLineCode(dataCode.substring(23, 25));//线别
            productInfoDo = userService.primary_getProductInfosByCode(codeInfoDo);
            if (productInfoDo != null) {
                QualityCheckingDo qualityCheckingDo = userService.thirdly_getQualityChecking(productInfoDo.getBatchNumber());
                if (StringUtils.isNotEmpty(qualityCheckingDo.getQualityChecking())) {
                    productInfoDo.setQurl(qualityCheckingDo.getQualityChecking());
                }
            }
        }//箱码
        if (dataCode.length()==21) {//C
            session.setAttribute("SCode_",dataCode);
            codeInfoDo.setStatus("C");
            codeInfoDo.setDate(dataCode.substring(0, 8));//年月日
            codeInfoDo.setFactory(dataCode.substring(8, 9).toUpperCase());//工厂
            codeInfoDo.setTime(dataCode.substring(9, 14).toUpperCase());//字母（A/B/C…Z）+MMDD
            codeInfoDo.setCode(dataCode.substring(14, 19));//流水码
            codeInfoDo.setLineCode(dataCode.substring(19,21));//线别
            productInfoDo = userService.primary_getProductInfosByCode(codeInfoDo);
            if (productInfoDo != null) {
                QualityCheckingDo qualityCheckingDo = userService.thirdly_getQualityChecking(productInfoDo.getBatchNumber());
                if (qualityCheckingDo != null) {
                    productInfoDo.setQurl(Constant.Q_URL_TEST+qualityCheckingDo.getQualityChecking());
                } else {
                    productInfoDo.setQurl("质检报告没有上传");
                }
            }
        }//手工箱码
        session.setAttribute("productInfoDo_",productInfoDo);
        return JSONUtils.beanToJson(productInfoDo);
    }

    /**
     * 保存过程数据
     * @param productInfoDo
     * @return
     */
    @PostMapping("/saveInfo")
    @ResponseBody
    public R saveInfo(ProductInfoDo productInfoDo){
        UserInfoDo userInfoDo = (UserInfoDo)session.getAttribute("userInfo_");
        ProductInfoDo productInfoDo1 = (ProductInfoDo)session.getAttribute("productInfoDo_");
        productInfoDo1.setSCode((String)session.getAttribute("SCode_"));
        productInfoDo1.setUserId(userInfoDo.getJobnumber());
        productInfoDo1.setAdderss(productInfoDo.getAdderss());
        productInfoDo1.setIsCargo(productInfoDo.getIsCargo());
        //保存EXP数据库
        Long id  = userService.thirdly_saveProductInfo(productInfoDo1);
        if (id != 0L) {
            for(KunnrDo kunnrDo : productInfoDo1.getKunnrDos()) {
                kunnrDo.setpId(productInfoDo1.getId());
                int count = userService.thirdly_saveProductKunnrs(kunnrDo);
                if (count ==0 ) {
                    return R.error(-1,"数据保存失败");
                }
            }
        }
        return R.error(0, "数据保存成功");
    }

    /**
     * 产品追溯过程数据
     * @return
     */
    @GetMapping("/infoData")
    public String productInfoData(){
        return "productInfoData";
    }

    /**
     * 获取数据列表
     * @param params
     * @return
     */
    @GetMapping("/productDataList")
    @ResponseBody
    public PageUtils productDataList(@RequestParam Map<String ,Object> params) {
        Query query = new Query(params);
        List<ProductInfoDo> productDataList = userService.thirdly_getProductInfoDataList(query);
        for(ProductInfoDo productInfoDo : productDataList) {
            if(productInfoDo.getIsCargo().equals("N")){
                productInfoDo.setIsCargo("否");
            }
            if(productInfoDo.getIsCargo().equals("Y")){
                productInfoDo.setIsCargo("是");
            }
        }
        int count = userService.thirdly_getProductInfoData(query);
        PageUtils pageUtils = new PageUtils(productDataList, count);
        return pageUtils;
    }
}
