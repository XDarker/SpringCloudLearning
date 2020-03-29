package com.xdarker.security.service;

import com.xdarker.security.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 16:46
 */
public interface UserService {

    UserDto create(UserDto user);

    UserDto update(UserDto user);

    void delete(Long id);

    UserDto get(Long id);

    List<UserDto> query(String name);
}
