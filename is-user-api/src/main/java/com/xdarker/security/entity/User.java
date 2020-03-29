package com.xdarker.security.entity;

import com.xdarker.security.dto.UserDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/1 15:15
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotBlank(message = "用户名不能为null")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public UserDto buildInfo(){
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(this, userDto);

        return userDto;
    }
}
