package com.core.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description 定义数据源AOP
 * @Author xg.chen
 * @Date 16:13 2019/5/15
 **/

@Aspect
@Component
public class DataSourceAop {

    private  static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Before("execution(* com.server.service.impl..*.primary*(..))")
    public void setDataSource2Primary() {
        logger.debug("主数据库业务");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.PRIMARY);
    }

    @Before("execution(* com.server.service.impl..*.secondary*(..))")
    public void setDataSource2Secondary() {
        logger.debug("辅数据库业务");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.SECONDARY);
    }
}
