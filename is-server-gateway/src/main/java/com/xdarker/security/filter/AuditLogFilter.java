package com.xdarker.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/19 16:29
 */
@Component
public class AuditLogFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 2;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() throws ZuulException {

        logger.info("audit log insert");
        return null;
    }
}
