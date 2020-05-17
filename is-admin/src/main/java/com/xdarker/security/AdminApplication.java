package com.xdarker.security;

import com.xdarker.security.entity.Credentials;
import com.xdarker.security.entity.TokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/5/3 21:44
 */
@SpringBootApplication
@RestController
@EnableZuulProxy
public class AdminApplication {

  private RestTemplate restTemplate = new RestTemplate();

  private final Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }

//  @PostMapping("/login")
//  public void login(@RequestBody Credentials credentials, HttpServletRequest request) {
//
//    String oauthServiceUrl = "http://localhost:9070/token/oauth/token";
//
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//    httpHeaders.setBasicAuth("admin", "123456");
//
//    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//    params.add("username", credentials.getUsername());
//    params.add("password", credentials.getPassword());
//    params.add("grant_type", "password");
//    params.add("scope", "read write");
//
//
//    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params, httpHeaders);
//
//    ResponseEntity<TokenInfo> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
//    request.getSession().setAttribute("token", response.getBody());
//  }

  @GetMapping("/oauth/callback")
  public void callback(@RequestParam String code,
                       @RequestParam String state,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

    logger.info("state:{}", state);

    String oauthServiceUrl = "http://localhost:9070/token/oauth/token";

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.setBasicAuth("admin", "123456");

    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("code", code);
    params.add("grant_type", "authorization_code");
    params.add("redirect_uri", "http://localhost:8080/oauth/callback");


    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params, httpHeaders);

    ResponseEntity<TokenInfo> token = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
    request.getSession().setAttribute("token", token.getBody());

    response.sendRedirect("/");
  }

  @PostMapping("/logout")
  public void logout(HttpServletRequest request){

    request.getSession().invalidate();

  }

  @GetMapping("/me")
  public TokenInfo me(HttpServletRequest request){

    TokenInfo info = (TokenInfo) request.getSession().getAttribute("token");
    return info;

  }

}
