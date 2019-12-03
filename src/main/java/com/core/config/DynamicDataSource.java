package com.core.config;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description 配置动态切换数据源
 * AbstractRoutingDataSource:动态切换数据源的关键
 * @Author xg.chen
 * @Date 15:49 2019/5/15
 **/

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }
}
