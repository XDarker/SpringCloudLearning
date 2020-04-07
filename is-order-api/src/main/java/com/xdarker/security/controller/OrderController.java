package com.xdarker.security.controller;

import com.xdarker.security.dto.OrderDto;
import com.xdarker.security.dto.PriceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/4/7 21:54
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {


    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public OrderDto create(@RequestBody OrderDto orderDto) {

        PriceDto priceDto =  restTemplate.getForObject("http://localhost:9060/prices/" + orderDto.getProductId(), PriceDto.class);

        log.info("price:{}",priceDto.getPrice());
        return orderDto;
    }
}
