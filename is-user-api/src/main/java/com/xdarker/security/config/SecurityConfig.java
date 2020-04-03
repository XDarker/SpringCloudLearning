package com.xdarker.security.config;

import com.xdarker.security.dto.UserDto;
import com.xdarker.security.filter.AclInterceptor;
import com.xdarker.security.filter.AuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.RequestContext;

import java.util.Optional;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/31 23:17
 */
@Configuration
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private AuditLogInterceptor auditLogInterceptor;

    @Autowired
    private AclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(auditLogInterceptor);
        registry.addInterceptor(aclInterceptor);
    }

    @Bean
    public AuditorAware<String> auditorAware(){

        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                UserDto userDto = (UserDto) servletRequestAttributes.getRequest().getSession().getAttribute("user");
                String username = null;
                if (userDto != null){
                    username = userDto.getUsername();
                }
                return Optional.ofNullable(username);
            }
        };
    }
}
