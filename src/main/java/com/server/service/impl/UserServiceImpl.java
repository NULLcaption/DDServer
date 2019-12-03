package com.server.service.impl;

import com.alibaba.fastjson.parser.SymbolTable;
import com.core.util.SAPConnectionPool;
import com.sap.conn.jco.*;
import com.server.mapper.UserMapper;
import com.server.mapper.UserSecondMapper;
import com.server.pojo.DeptAdmin;
import com.server.pojo.PropertyInfo;
import com.server.pojo.UserInfoDo;
import com.server.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description User服务实现类
 * @Author xg.chen
 * @Date 16:31 2019/5/15
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JCoDestination sapDestination;

    @Autowired
    private UserSecondMapper userSecondMapper;

    @Override
    public UserInfoDo primary_getUserInfoByLoginId(String loginId) {
        try {
            return userMapper.primary_getUserInfoByLoginId(loginId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfoDo secondary_getUserInfoByLoginId(String loginId) {
        try {
            return userSecondMapper.secondary_getUserInfoByLoginId(loginId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserInfoDo> primary_getUserInfoList(String deptId) {
        try {
            return userMapper.primary_getUserInfoList(deptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DeptAdmin primary_getDeptAdminByJobnumber(String jobnumber) {
        try {
            return userMapper.primary_getDeptAdminByJobnumber(jobnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PropertyInfo> primary_getPropertyList(String costCenter) {
        try {
            return userMapper.primary_getPropertyList(costCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PropertyInfo primary_getPropertyInfoById(String id) {
        try {
            return userMapper.primary_getPropertyInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int primary_updatePropertyInfoById(PropertyInfo propertyInfo) {
        try {
            logger.debug("++++> 开始连接SAP RFC 接口");
            JCoFunction function = sapDestination.getRepository().getFunction("ZBAPI_FIXEDASSET_CHANGE");
            //根据Id获取到固定资产信息
            PropertyInfo propertyInfo_1 = userMapper.primary_getPropertyInfoById(propertyInfo.getId());
            //SAP结构赋值
            JCoStructure jCoStructure = function.getImportParameterList().getStructure("IS_CGINFO");
            jCoStructure.setValue("MAIN_DESCRIPT", propertyInfo.getAddress());
            jCoStructure.setValue("ROOM",propertyInfo.getPersonInCharge());
            //SAP字段传值
            function.getImportParameterList().setValue("IV_ASSET",propertyInfo_1.getPropertyId());//资产编号
            function.getImportParameterList().setValue("IV_COMPANYCODE",propertyInfo_1.getCompany());//公司代码
            function.getImportParameterList().setValue( "IV_SUBNUMBER" ,"0000");//资产次级编号
            logger.debug("+++++> SAP 函数开始执行");
            //SAP 函数开始执行
            function.execute(sapDestination);
            String code = function.getExportParameterList().getValue("EV_CODE").toString();
            //返回信息
            String EV_MSG = function.getExportParameterList().getString("EV_MSG");
            if (!"0".equals(code)) {
                logger.debug(EV_MSG);
                return -1;
            } else {
                userMapper.primary_updatePropertyInfoById(propertyInfo);
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public PropertyInfo primary_getPropertyInfoTotalById(String id) {
        try {
            return userMapper.primary_getPropertyInfoTotalById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DeptAdmin primary_getDeptAdminByCostCen(String costCenter) {
        try {
            return userMapper.primary_getDeptAdminByCostCen(costCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PropertyInfo primary_getPropertyInfoByPropertyId(PropertyInfo propertyInfo) {
        try {
            return userMapper.primary_getPropertyInfoByPropertyId(propertyInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int primary_updatePropertyInfoById4pic(PropertyInfo propertyInfo) {
        try {
            userMapper.primary_updatePropertyInfoById4pic(propertyInfo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
