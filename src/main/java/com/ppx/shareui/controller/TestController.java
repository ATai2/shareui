package com.ppx.shareui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/*")
public class TestController {

    @RequestMapping("/user")
    @ResponseBody
    public String getUser(){
        return "{\"login\":\"shareui\"}";
    }
}
