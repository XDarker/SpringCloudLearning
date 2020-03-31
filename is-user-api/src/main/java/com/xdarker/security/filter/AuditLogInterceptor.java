package com.xdarker.security.filter;

import com.xdarker.security.entity.User;
import com.xdarker.security.log.AuditLog;
import com.xdarker.security.repository.AuditLogRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/31 23:06
 */
@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(request.getMethod());
        auditLog.setPath(request.getRequestURI());
        auditLog.getUsername();

//        User user = (User) request.getAttribute("user");
//        if (user != null){
//            auditLog.setUsername(user.getUsername());
//        }
        auditLogRepository.save(auditLog);

        request.setAttribute("auditLogId", auditLog.getId());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 不管成功还是失败都会调该方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long auditLogId = (Long) request.getAttribute("auditLogId");

        AuditLog auditLog = auditLogRepository.findById(auditLogId).get();

        auditLog.setStatus(response.getStatus());

        auditLogRepository.save(auditLog);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
