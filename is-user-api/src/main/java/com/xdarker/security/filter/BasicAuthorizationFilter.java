package com.xdarker.security.filter;

import com.lambdaworks.crypto.SCryptUtil;
import com.xdarker.security.entity.User;
import com.xdarker.security.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 17:30
 */
@Component
@Order(2)
public class BasicAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("BasicAuthorizationFilter start 22222222");
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authHeader)) {
            String token64 = StringUtils.substringAfter(authHeader, "Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");

            String username = items[0];
            String password = items[1];

            User user = userRepository.findByUsername(username);
            if (user != null && SCryptUtil.check(password, user.getPassword())) {
                request.setAttribute("user", user);
            }
        }

        filterChain.doFilter(request, response);

    }
}
