package com.xdarker.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xdarker.security.entity.TokenInfo;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/5/10 11:23
 */
@Component
public class SessionTokenFilter extends ZuulFilter {

  public String filterType() {
    return "pre";
  }

  public int filterOrder() {
    return 0;
  }

  public boolean shouldFilter() {
    return true;
  }

  public Object run() throws ZuulException {

    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();

    TokenInfo tokenInfo = (TokenInfo) request.getSession().getAttribute("token");

    if(tokenInfo != null){
      requestContext.addZuulRequestHeader("Authorization", "bearer " + tokenInfo.getAccess_token());

    }


    return null;
  }
}
