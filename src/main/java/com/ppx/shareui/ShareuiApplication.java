package com.ppx.shareui;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Administrator
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,})
public class ShareuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareuiApplication.class, args);
    }

}

