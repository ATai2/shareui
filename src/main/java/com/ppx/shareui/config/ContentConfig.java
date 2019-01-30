package com.ppx.shareui.config;

import org.activiti.content.storage.api.ContentStorage;
import org.activiti.content.storage.fs.FileSystemContentStorage;
import org.activiti.engine.impl.util.DefaultClockImpl;
import org.activiti.engine.runtime.Clock;
import org.activiti.form.api.FormRepositoryService;
import org.activiti.form.engine.impl.FormRepositoryServiceImpl;
import org.activiti.form.engine.impl.FormServiceImpl;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

/**
 * @author Administrator
 */
@Configuration
public class ContentConfig {
    @Bean
    public ContentStorage contentStorage(){
        ContentStorage c = new FileSystemContentStorage(new File("/"), 024, 1);
        return c;
    }
    @Bean
    public Clock clock(){
        Clock clock=new DefaultClockImpl();
        return clock;
    }
    @Bean
    public FormRepositoryService formRepositoryService(){
        FormRepositoryService formRepositoryService=new FormRepositoryServiceImpl();
        return formRepositoryService;
    }

    @Bean
    public org.activiti.form.api.FormService formService(){
        org.activiti.form.api.FormService formRepositoryService=new FormServiceImpl();
        return formRepositoryService;
    }

    @Bean
    public ServletRegistrationBean indexServletRegistration(){
        AnnotationConfigWebApplicationContext configApplicationContext=new AnnotationConfigWebApplicationContext();

        configApplicationContext.scan("org.activiti.app.rest");

        DispatcherServlet dispatcherServlet=new DispatcherServlet(configApplicationContext);
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(dispatcherServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/app/*");
        servletRegistrationBean.setName("app");
        return servletRegistrationBean;
    }













}
