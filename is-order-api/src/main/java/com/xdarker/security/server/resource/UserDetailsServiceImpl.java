package com.xdarker.security.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/9 22:01
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        //真实场景要读数据库
        return user;
    }
}
