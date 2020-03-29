package com.xdarker.security.controller;

import com.xdarker.security.dto.UserDto;
import com.xdarker.security.entity.User;
import com.xdarker.security.repository.UserRepository;
import com.xdarker.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 15:20
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto create(@RequestBody @Validated UserDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@RequestBody UserDto user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id, HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        if (user == null || user.getId() != id){
            throw new RuntimeException("身份认证信息异常，获取用户信息失败");
        }
        return userService.get(id);
    }

    @GetMapping
    public List<UserDto> query(String name) {

//        String sql = "select * from user where name = '" + name + "'";
//        List list = jdbcTemplate.queryForList(sql);
//        return list;
        return userService.query(name);
    }
}




