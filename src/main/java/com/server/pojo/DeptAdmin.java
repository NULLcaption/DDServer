package com.server.pojo;

import java.io.Serializable;

/**
 * @Description 部门资产管理员
 * @Author xg.chen
 * @Date 14:33 2019/10/17
**/

public class DeptAdmin implements Serializable{

    private String id;//数据ID
    private String costCenter;//成本中心
    private String costCenterDesc;//成本中心描述
    private String deptAdmin;//部门资产管理员
    private String jobnumber;//钉钉工号
    private String userId;//用户ID
    private String orgId;
    private String orgName;

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

    public String getCostCenterDesc() {
        return costCenterDesc;
    }

    public void setCostCenterDesc(String costCenterDesc) {
        this.costCenterDesc = costCenterDesc;
    }

    public String getDeptAdmin() {
        return deptAdmin;
    }

    public void setDeptAdmin(String deptAdmin) {
        this.deptAdmin = deptAdmin;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
