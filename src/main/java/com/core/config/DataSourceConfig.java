package com.core.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description SpringBoot多数据源配置
 * @Author xg.chen
 * @Date 13:56 2019/5/15
 **/

@Configuration
@MapperScan(basePackages = "com.server.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    /**
     * @Description 主数据源
     * @Author xg.chen
     * @Date 14:26 2019/5/15
     **/
    @Primary
    @Bean(name="primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @Description 辅数据源
     * @Author xg.chen
     * @Date 14:28 2019/5/15
     **/
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @Description 动态切换数据源
     * @Author xg.chen
     * @Date 16:04 2019/5/15
     **/
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataScource(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                         @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.DataBaseType.PRIMARY, primaryDataSource);
        targetDataSource.put(DataSourceType.DataBaseType.SECONDARY, secondaryDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(primaryDataSource);
        return dataSource;
    }

    /**
     * 配置主数据源sqlSessionFactory
     * @param dynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactroy(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*.xml")
        );
        return bean.getObject();
    }

}
