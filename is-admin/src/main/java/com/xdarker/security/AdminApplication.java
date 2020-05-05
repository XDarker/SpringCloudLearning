package com.xdarker.security;

import com.xdarker.security.entity.Credentials;
import com.xdarker.security.entity.TokenInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/5/3 21:44
 */
@SpringBootApplication
@RestController
public class AdminApplication {

  private RestTemplate restTemplate = new RestTemplate();


  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }

  @PostMapping("/login")
  public void login(@RequestBody Credentials credentials, HttpServletRequest request){

    String oauthServiceUrl = "http://localhost:9070/token/oauth/token";

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.setBasicAuth("admin", "123456");

    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("username", credentials.getUsername());
    params.add("password", credentials.getPassword());
    params.add("grant_type", "password");
    params.add("scope", "read write");


    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params,httpHeaders);

    ResponseEntity<TokenInfo> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
    request.getSession().setAttribute("token", response.getBody());
  }

}
