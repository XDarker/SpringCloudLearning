package com.xdarker.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/19 16:31
 */
@Component
public class AuthorizationFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 3;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() throws ZuulException {

        logger.info("authorization start");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if (isNeedAuth(request)) {

            TokenInfo tokenInfo = (TokenInfo) request.getAttribute("tokenInfo");
            if (tokenInfo != null /*&& tokenInfo.isActive()*/) {

                if (!hasPermission(tokenInfo, request)) {
                    logger.info("audit log update fail 403");
                    handleError(403, requestContext);
                }

                requestContext.addZuulRequestHeader("username", tokenInfo.getUser_name());

            } else {
                if (!StringUtils.startsWith(request.getRequestURI(), "/token")) {
                    logger.info("audit log update fail 401");
                    handleError(401, requestContext);
                }

            }
        }

        return null;
    }

    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {

        return true /*RandomUtils.nextInt() % 2 == 0*/;
    }

    private void handleError(int status, RequestContext requestContext) {

        requestContext.getResponse().setContentType("application/json");
        requestContext.setResponseStatusCode(status);
        requestContext.setResponseBody("{\"message\":\"auth fail\"}");
        //表示不要往下走了
        requestContext.setSendZuulResponse(false);

    }

    private boolean isNeedAuth(HttpServletRequest request) {
        return true;
    }
}
