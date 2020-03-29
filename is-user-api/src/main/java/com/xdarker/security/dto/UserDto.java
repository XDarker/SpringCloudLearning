package com.xdarker.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Created by XDarker
 * @Description TODO  关注点分离  设计模式值六大原则——设计模式之六大原则——单一职责原则(SRP)
 * @Date 2020/3/1 15:15
 */
@Data //get set方法
public class UserDto {

    private Long id;

    private String name;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
