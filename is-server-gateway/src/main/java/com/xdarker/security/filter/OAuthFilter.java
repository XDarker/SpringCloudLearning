package com.xdarker.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/17 22:52
 */

@Component
public class OAuthFilter extends ZuulFilter {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate = new RestTemplate();

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 1;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() throws ZuulException {

        logger.info("OAuth start");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if(StringUtils.startsWith(request.getRequestURI(), "/token")){
            //获取认证的 继续往下走
            return null;
        }

        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank(authHeader)){
            //没带请求头 继续往下走
            return null;
        }

        if (!StringUtils.startsWithIgnoreCase(authHeader, "bearer ")){
            return null;
        }

        try {

            TokenInfo info = getTokenInfo(authHeader);
            request.setAttribute("tokenInfo", info);

        }catch (Exception e){
            logger.error("get TokenInfo fail,{}", e);
        }

        return null;
    }

    private TokenInfo getTokenInfo(String authHeader) {


        String token = StringUtils.substringAfter(authHeader, "bearer ");
        String oauthServiceUrl = "http://localhost:9090/oauth/check_token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth("gateway", "123456");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("token", token);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params,httpHeaders);

        ResponseEntity<TokenInfo> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);

        logger.info("token info:{}", response.getBody().toString());
        return response.getBody();

    }
}
