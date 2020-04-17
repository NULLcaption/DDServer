package com.server.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 快递登记实体类
 * @Author xg.chen
 * @Date 13:13 2020/4/16
 **/

public class ExpressDo implements Serializable{

    private String id;
    private String userId;
    private String userName;
    private String orgId;
    private String orgName;
    private String dName;
    private String expressCompany;
    private String expressNum;
    private String costBelonging;
    private String explain;
    private String creatDate;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getCostBelonging() {
        return costBelonging;
    }

    public void setCostBelonging(String costBelonging) {
        this.costBelonging = costBelonging;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public ExpressDo() {
    }

    @Override
    public String toString() {
        return "ExpressDo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", dName='" + dName + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                ", expressNum='" + expressNum + '\'' +
                ", costBelonging='" + costBelonging + '\'' +
                ", explain='" + explain + '\'' +
                ", creatDate='" + creatDate + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
