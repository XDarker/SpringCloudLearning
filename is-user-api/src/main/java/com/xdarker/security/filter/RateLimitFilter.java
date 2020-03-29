package com.xdarker.security.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created by XDarker
 * @Description 限流过滤器
 * @Date 2020/3/1 16:19
 */
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    //流控控制器
    private RateLimiter rateLimiter = RateLimiter.create(1);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (rateLimiter.tryAcquire()) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("too many requesy!!!");
            response.getWriter().flush();
            return;
        }
    }
}
