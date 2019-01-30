package com.ppx.shareui.controller;

import com.ppx.shareui.config.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/*")
public class TestController {

    @Autowired
    private DataSourceConfig dataSourceConfig;
    @Autowired
    private IdentityService identityService;
    @RequestMapping("/user")
    @ResponseBody
    public String getUser(HttpServletRequest request){
        System.out.println(dataSourceConfig);
        System.out.println(dataSourceConfig.getDataSource());
        UserEntityImpl userEntity=new UserEntityImpl();
        userEntity.setId(request.getParameter("id"));
        userEntity.setRevision(0);
        identityService.saveUser(userEntity);
        return "{\"login\":\"shareui\"}";
    }
}
