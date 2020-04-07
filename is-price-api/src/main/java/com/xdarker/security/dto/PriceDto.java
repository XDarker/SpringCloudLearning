package com.xdarker.security.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/7 22:10
 */
@Data
public class PriceDto {

    private Long id;

    private BigDecimal price;
}
