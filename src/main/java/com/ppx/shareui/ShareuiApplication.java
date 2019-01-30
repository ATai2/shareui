package com.ppx.shareui;

import org.activiti.spring.boot.JpaProcessEngineAutoConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


/**
 * @author Administrator
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        LiquibaseAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
//        JpaProcessEngineAutoConfiguration.class,
})
public class ShareuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareuiApplication.class, args);
    }

}

