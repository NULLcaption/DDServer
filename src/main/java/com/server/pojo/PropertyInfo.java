package com.server.pojo;

import java.io.Serializable;

/**
 * @Description 资产信息实体类
 * @Author xg.chen
 * @Date 13:17 2019/5/13
**/

public class PropertyInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private String propertyName;
    private String propertyId;
    private String propertyDate;
    private String costCenter;
    private String costCenterDescription;
    private String stateDescription;
    private String company;
    private String modelType;
    private String units;
    private String num;
    private String kunnrName;
    private String pnum;
    private String personInCharge;
    private String address;
    private String mark;
    private String leaveUnused;
    private String state;
    private String picUrl;
    private String jobnumber;

    private String countPNum;
    private String countWPNUm;
    private String countYPNum;

    public String getCountPNum() {
        return countPNum;
    }

    public void setCountPNum(String countPNum) {
        this.countPNum = countPNum;
    }

    public String getCountWPNUm() {
        return countWPNUm;
    }

    public void setCountWPNUm(String countWPNUm) {
        this.countWPNUm = countWPNUm;
    }

    public String getCountYPNum() {
        return countYPNum;
    }

    public void setCountYPNum(String countYPNum) {
        this.countYPNum = countYPNum;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLeaveUnused() {
        return leaveUnused;
    }

    public void setLeaveUnused(String leaveUnused) {
        this.leaveUnused = leaveUnused;
    }

    public String getKunnrName() {
        return kunnrName;
    }

    public void setKunnrName(String kunnrName) {
        this.kunnrName = kunnrName;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCostCenterDescription() {
        return costCenterDescription;
    }

    public void setCostCenterDescription(String costCenterDescription) {
        this.costCenterDescription = costCenterDescription;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyDate() {
        return propertyDate;
    }

    public void setPropertyDate(String propertyDate) {
        this.propertyDate = propertyDate;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toString() {
        return "PropertyInfo{" +
                "id='" + id + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", propertyDate='" + propertyDate + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", costCenterDescription='" + costCenterDescription + '\'' +
                ", stateDescription='" + stateDescription + '\'' +
                ", company='" + company + '\'' +
                ", modelType='" + modelType + '\'' +
                ", units='" + units + '\'' +
                ", num='" + num + '\'' +
                ", kunnrName='" + kunnrName + '\'' +
                ", pnum='" + pnum + '\'' +
                ", personInCharge='" + personInCharge + '\'' +
                ", address='" + address + '\'' +
                ", mark='" + mark + '\'' +
                ", leaveUnused='" + leaveUnused + '\'' +
                ", state='" + state + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", jobnumber='" + jobnumber + '\'' +
                '}';
    }
}
