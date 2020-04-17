package com.server.pojo;

import java.io.Serializable;

/**
 * @Description 产品追溯实体类
 * @Author xg.chen
 * @Date 14:39 2020/3/23
 **/

public class ProductInfoDo implements Serializable {

    public String proFactory;
    public String skuId;
    public String skuName;
    public String batchNumber;
    public String kunnrId;
    public String kunnrName;
    public String Qurl;
    public String adderss;
    public String isCargo;

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

    public String getKunnrId() {
        return kunnrId;
    }

    public void setKunnrId(String kunnrId) {
        this.kunnrId = kunnrId;
    }

    public String getKunnrName() {
        return kunnrName;
    }

    public void setKunnrName(String kunnrName) {
        this.kunnrName = kunnrName;
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

    @Override
    public String toString() {
        return "ProductInfoDo{" +
                "proFactory='" + proFactory + '\'' +
                ", skuId='" + skuId + '\'' +
                ", skuName='" + skuName + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", kunnrId='" + kunnrId + '\'' +
                ", kunnrName='" + kunnrName + '\'' +
                ", Qurl='" + Qurl + '\'' +
                ", adderss='" + adderss + '\'' +
                ", isCargo='" + isCargo + '\'' +
                '}';
    }
}
