package com.xdarker.security.controller;

import com.xdarker.security.dto.PriceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/7 21:54
 */
@RestController
@RequestMapping("/prices")
@Slf4j
public class PriceController {

    @GetMapping("/{id}")
    public PriceDto getPrice(@PathVariable("id") Long id){

        PriceDto priceDto = new PriceDto();
        priceDto.setId(id);
        priceDto.setPrice(new BigDecimal(100));
        log.info("id:{}", id);
        return priceDto;
    }
}
