package com.server.mapper;

import com.server.pojo.DeptAdmin;
import com.server.pojo.ExpressDo;
import com.server.pojo.PropertyInfo;
import com.server.pojo.UserInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户数据持久化
 * @Author xg.chen
 * @Date 9:10 2019/5/16
 **/
@Repository
@Mapper
public interface UserMapper {
    UserInfoDo primary_getUserInfoByLoginId(String loginId);

    List<UserInfoDo> primary_getUserInfoList(String deptId);

    List<DeptAdmin> primary_getDeptAdminByJobnumber(String jobnumber);

    List<PropertyInfo> primary_getPropertyList(String costCenter);

    PropertyInfo primary_getPropertyInfoById(String id);

    void primary_updatePropertyInfoById(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoTotalById(String id);

    List<DeptAdmin> primary_getDeptAdminByCostCen(String costCenter);

    PropertyInfo primary_getPropertyInfoByPropertyId(PropertyInfo propertyInfo);

    void primary_updatePropertyInfoById4pic(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoByPropertyIdHZ(PropertyInfo propertyInfo);

    List<PropertyInfo> primary_getPropertyInfoListHZ(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoCountNum(String costCenter);

    DeptAdmin primary_getDeptAdminByOutUserId(String outuserid);

    void primary_createExpressIndfo4DD(ExpressDo expressDo);

    List<ExpressDo> primary_getExpressInfoByUserId(String outuserid);

    ExpressDo primary_getExpressInfoById(int id);

    int primary_getExpressInfoId();

}
