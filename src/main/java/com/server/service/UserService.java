package com.server.service;

import com.server.pojo.DeptAdmin;
import com.server.pojo.PropertyInfo;
import com.server.pojo.UserInfoDo;
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

    DeptAdmin primary_getDeptAdminByJobnumber(String jobnumber);

    List<PropertyInfo> primary_getPropertyList(String costCenter);

    PropertyInfo primary_getPropertyInfoById(String id);

    int primary_updatePropertyInfoById(PropertyInfo propertyInfo);

    PropertyInfo primary_getPropertyInfoTotalById(String id);

    DeptAdmin primary_getDeptAdminByCostCen(String costCenter);

    PropertyInfo primary_getPropertyInfoByPropertyId(PropertyInfo propertyInfo);

    int primary_updatePropertyInfoById4pic(PropertyInfo propertyInfo);
}
