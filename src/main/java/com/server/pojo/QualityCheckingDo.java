package com.server.pojo;

import java.io.Serializable;

/**
 * @Description 质检报告实体类
 * @Author xg.chen
 * @Date 11:20 2020/3/19
 **/

public class QualityCheckingDo implements Serializable {
    // ID
    private String id;
    // 质检编号
    private String qualityCheckingId;
    // 生产日期
    private String dateOfManufacture;
    // 跨天生产日期
    private String dateOfManufacture1;
    // 时间从
    private String productionTimeStart;
    // 时间至
    private String productionTimeEnd;
    // 物料Id
    private String materielId;
    // 物料描述
    private String materielName;
    // 报告预览
    private String qualityChecking;
    // 开始时间
    private String dateOfManufactureStart;
    // 结束时间
    private String dateOfManufactureEnd;
    // 生产时间（开始）
    private String dateOfManufactureStartTime;
    // 生产时间（结束）
    private String dateOfManufactureEndTime;
    //工厂
    private String factory;
    //批次编号
    private String batchNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualityCheckingId() {
        return qualityCheckingId;
    }

    public void setQualityCheckingId(String qualityCheckingId) {
        this.qualityCheckingId = qualityCheckingId;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getDateOfManufacture1() {
        return dateOfManufacture1;
    }

    public void setDateOfManufacture1(String dateOfManufacture1) {
        this.dateOfManufacture1 = dateOfManufacture1;
    }

    public String getProductionTimeStart() {
        return productionTimeStart;
    }

    public void setProductionTimeStart(String productionTimeStart) {
        this.productionTimeStart = productionTimeStart;
    }

    public String getProductionTimeEnd() {
        return productionTimeEnd;
    }

    public void setProductionTimeEnd(String productionTimeEnd) {
        this.productionTimeEnd = productionTimeEnd;
    }

    public String getMaterielId() {
        return materielId;
    }

    public void setMaterielId(String materielId) {
        this.materielId = materielId;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getQualityChecking() {
        return qualityChecking;
    }

    public void setQualityChecking(String qualityChecking) {
        this.qualityChecking = qualityChecking;
    }

    public String getDateOfManufactureStart() {
        return dateOfManufactureStart;
    }

    public void setDateOfManufactureStart(String dateOfManufactureStart) {
        this.dateOfManufactureStart = dateOfManufactureStart;
    }

    public String getDateOfManufactureEnd() {
        return dateOfManufactureEnd;
    }

    public void setDateOfManufactureEnd(String dateOfManufactureEnd) {
        this.dateOfManufactureEnd = dateOfManufactureEnd;
    }

    public String getDateOfManufactureStartTime() {
        return dateOfManufactureStartTime;
    }

    public void setDateOfManufactureStartTime(String dateOfManufactureStartTime) {
        this.dateOfManufactureStartTime = dateOfManufactureStartTime;
    }

    public String getDateOfManufactureEndTime() {
        return dateOfManufactureEndTime;
    }

    public void setDateOfManufactureEndTime(String dateOfManufactureEndTime) {
        this.dateOfManufactureEndTime = dateOfManufactureEndTime;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        return "QualityCheckingDo{" +
                "id='" + id + '\'' +
                ", qualityCheckingId='" + qualityCheckingId + '\'' +
                ", dateOfManufacture='" + dateOfManufacture + '\'' +
                ", dateOfManufacture1='" + dateOfManufacture1 + '\'' +
                ", productionTimeStart='" + productionTimeStart + '\'' +
                ", productionTimeEnd='" + productionTimeEnd + '\'' +
                ", materielId='" + materielId + '\'' +
                ", materielName='" + materielName + '\'' +
                ", qualityChecking='" + qualityChecking + '\'' +
                ", dateOfManufactureStart='" + dateOfManufactureStart + '\'' +
                ", dateOfManufactureEnd='" + dateOfManufactureEnd + '\'' +
                ", dateOfManufactureStartTime='" + dateOfManufactureStartTime + '\'' +
                ", dateOfManufactureEndTime='" + dateOfManufactureEndTime + '\'' +
                ", factory='" + factory + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                '}';
    }
}
