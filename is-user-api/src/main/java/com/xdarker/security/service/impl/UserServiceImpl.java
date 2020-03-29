package com.xdarker.security.service.impl;

import com.xdarker.security.dto.UserDto;
import com.xdarker.security.entity.User;
import com.xdarker.security.repository.UserRepository;
import com.xdarker.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 16:46
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto create(UserDto info) {
        User user = new User();
        BeanUtils.copyProperties(info, user);
        userRepository.save(user);
        info.setId(user.getId());
        return info;
    }

    public UserDto update(UserDto user) {
        return null;
    }

    public void delete(Long id) {

    }

    public UserDto get(Long id) {
        return userRepository.findById(id).get().buildInfo();
    }

    public List<UserDto> query(String name) {
        return null;
    }
}
