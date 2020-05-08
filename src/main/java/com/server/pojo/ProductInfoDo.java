package com.server.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 产品追溯实体类
 * @Author xg.chen
 * @Date 14:39 2020/3/23
 **/

public class ProductInfoDo implements Serializable {

    private Long id;
    private String SCode;
    private String proFactory;
    private String skuId;
    private String skuName;
    private String batchNumber;
    private String Qurl;
    private String adderss;
    private String isCargo;
    private String userId;
    private String kunnrName;
    private List<KunnrDo> kunnrDos;

    public String getKunnrName() {
        return kunnrName;
    }

    public void setKunnrName(String kunnrName) {
        this.kunnrName = kunnrName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public List<KunnrDo> getKunnrDos() {
        return kunnrDos;
    }

    public void setKunnrDos(List<KunnrDo> kunnrDos) {
        this.kunnrDos = kunnrDos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProFactory() {
        return proFactory;
    }

    public void setProFactory(String proFactory) {
        this.proFactory = proFactory;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getQurl() {
        return Qurl;
    }

    public void setQurl(String qurl) {
        Qurl = qurl;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    public String getIsCargo() {
        return isCargo;
    }

    public void setIsCargo(String isCargo) {
        this.isCargo = isCargo;
    }

}
