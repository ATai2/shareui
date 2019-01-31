package com.ppx.shareui.interceptor;

import org.activiti.app.security.SecurityUtils;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */

public class ShareiuHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath=request.getServletPath();
        if (servletPath.startsWith("/app") || servletPath.startsWith("/shareui")||servletPath.startsWith("/idm")) {
            User currentUserObject = SecurityUtils.getCurrentUserObject();
            if (currentUserObject == null) {
                User user=new UserEntityImpl();
                user.setId("kermit");
                SecurityUtils.assumeUser(user);
            }
        }
        return true;
    }
}
