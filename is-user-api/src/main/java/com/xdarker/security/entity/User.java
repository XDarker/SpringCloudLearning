package com.xdarker.security.entity;

import com.xdarker.security.dto.UserDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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

    @NotBlank(message = "用户名不能为null")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "用户名不能为null")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String permissions;

    public UserDto buildInfo(){
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(this, userDto);

        return userDto;
    }

    public boolean hasPermission(String method) {

        boolean result = false;
        if (StringUtils.equalsIgnoreCase("get", method)){
            result = StringUtils.contains(permissions, "r");

        }else{
            result = StringUtils.contains(permissions, "w");
        }
        return result;
    }
}
