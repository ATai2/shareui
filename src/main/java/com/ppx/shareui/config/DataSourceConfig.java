//package com.ppx.shareui.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
///**
// * @author Administrator
// */
//@Component
//@Configuration
////@EnableJpaRepositories({ "org.activiti.app.repository" })
////@EnableTransactionManagement
//public class DataSourceConfig {
//    @Autowired
//    Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//
//        return dataSource;
//    }
//}
