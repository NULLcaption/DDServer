package com.server.service;

import com.server.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description user服务类
 * @Author xg.chen
 * @Date 16:29 2019/5/15
 **/
@Service
public interface UserService {

    UserInfoDo primary_getUserInfoByLoginId(String loginId);

    UserInfoDo secondary_getUserInfoByLoginId(String loginId);

    List<UserInfoDo> primary_getUserInfoList(String deptId);

    List<DeptAdmin> primary_getDeptAdminByJobnumber(String jobnumber);

    List<PropertyInfo> primary_getPropertyList(String costCenter);

    PropertyInfo primary_getPropertyInfoById(String id);

    int primary_updatePropertyInfoById(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoTotalById(String id);

    List<DeptAdmin> primary_getDeptAdminByCostCen(String costCenter);

    PropertyInfo primary_getPropertyInfoByPropertyId(PropertyInfo propertyInfo);

    int primary_updatePropertyInfoById4pic(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoByPropertyIdHZ(PropertyInfo propertyInfo);

    List<PropertyInfo> primary_getPropertyInfoListHZ(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoCountNum(String costCenter);

    UserInfoDo secondary_getUserInfoByUserId(String userid);

    QualityCheckingDo thirdly_getQualityChecking(String batchNumber);

    DeptAdmin primary_getDeptAdminByOutUserId(String outuserid);

    int primary_createExpressIndfo4DD(ExpressDo expressDo);

    List<ExpressDo> primary_getExpressInfoByUserId(String outuserid);

    ExpressDo primary_getExpressInfoById(String id);

    int primary_getExpressInfoId();
}
