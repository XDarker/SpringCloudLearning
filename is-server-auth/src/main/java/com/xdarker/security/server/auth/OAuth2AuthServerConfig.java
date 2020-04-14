package com.xdarker.security.server.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @Created by XDarker
 * @Description 认证服务器配置
 * @Date 2020/4/8 22:42
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

//    public static void main(String[] args) {
//
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
//
//    }
    /**
     * 客户端应用 配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(dataSource);
//        clients.inMemory()
//                .withClient("orderApp")
//                .secret(new BCryptPasswordEncoder().encode("123456"))
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(3600)
//                //访问哪些资源服务器
//                .resourceIds("order-server")
//                //授权类型
//                .authorizedGrantTypes("password")
//                .and()
//
//                .withClient("orderService")
//                .secret(new BCryptPasswordEncoder().encode("123456"))
//                .scopes("read")
//                .accessTokenValiditySeconds(3600)
//                //访问哪些资源服务器
//                .resourceIds("order-server")
//                //授权类型
//                .authorizedGrantTypes("password");



    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager);

    }
}
