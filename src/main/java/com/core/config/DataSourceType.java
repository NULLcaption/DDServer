package com.core.config;

import org.apache.naming.factory.DataSourceLinkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 配置线程安全的数据源
 * @Author xg.chen
 * @Date 15:46 2019/5/15
 **/
public class DataSourceType {

    private  static final Logger logger = LoggerFactory.getLogger(DataSourceType.class);

    public enum DataBaseType {
        PRIMARY,
        SECONDARY,
        THIRDLY
    }

    /**
     * @Description 使用threadLocal保证线程是安全的
     * @Author xg.chen
     * @Date 15:43 2019/5/15
     **/
    private static final ThreadLocal<DataBaseType> TYPE = new ThreadLocal<>();

    /**
     * @Description 向当前线程设置数据库
     * @Author xg.chen
     * @Date 15:43 2019/5/15
     **/
    public static void setDataBaseType(DataBaseType dataBaseType){
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        logger.debug("[now data source]:" + dataBaseType);
        TYPE.set(dataBaseType);
    }

    /**
     * @Description 获取数据源类型
     * @Author xg.chen
     * @Date 15:44 2019/5/15
    **/
    public static DataBaseType getDataBaseType() {
        DataBaseType dataBaseType = TYPE.get() == null ? DataBaseType.PRIMARY : TYPE.get();
        logger.debug("[get now data base type]:" + dataBaseType);
        return dataBaseType;
    }

    /**
     * @Description 清除当前数据源
     * @Author xg.chen
     * @Date 15:45 2019/5/15
     **/
    public static void cleartDataBaseType() {
        TYPE.remove();
    }

}
