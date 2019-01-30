package com.ppx.shareui.controller;

import com.ppx.shareui.config.DataSourceConfig;
import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@RestController
@RequestMapping
public class IdmController {

    @Autowired
    private DataSourceConfig dataSourceConfig;
    @Autowired
    private IdentityService identityService;

    @RequestMapping("/app/authentication")
    @ResponseBody
    public String user(HttpServletRequest request){
        return "{\"login\":\"shareui@activiti.gov\"}";
    }

    @RequestMapping("/authenticate")
    @ResponseBody
    public String user2(HttpServletRequest request){
        return "{\"login\":\"shareui@activiti.gov\"}";
    }

    @RequestMapping("/account")
    @ResponseBody
    public String account(HttpServletRequest request){
        return "{\"id\":\"admin\",\"firstName\":null,\"lastName\":\"Administrator\",\"email\":\"admin\",\"fullName\":\" Administrator\",\"groups\":[{\"id\":\"ROLE_ADMIN\",\"name\":\"Superusers\",\"type\":\"security-role\"}]}";
    }

//    @RequestMapping("/app/rest/runtime/app-definitions")
//    @ResponseBody
//    public String runtime(HttpServletRequest request){
//        return "{\"size\":3,\"total\":3,\"start\":0,\"data\":[{\"defaultAppId\":\"kickstart\",\"name\":null,\"description\":null,\"modelId\":null,\"theme\":null,\"icon\":null,\"deploymentId\":null,\"deploymentKey\":null,\"tenantId\":null},{\"defaultAppId\":\"tasks\",\"name\":null,\"description\":null,\"modelId\":null,\"theme\":null,\"icon\":null,\"deploymentId\":null,\"deploymentKey\":null,\"tenantId\":null},{\"defaultAppId\":\"identity\",\"name\":null,\"description\":null,\"modelId\":null,\"theme\":null,\"icon\":null,\"deploymentId\":null,\"deploymentKey\":null,\"tenantId\":null}]}";
//    }

}
