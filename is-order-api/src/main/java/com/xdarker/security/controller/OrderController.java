package com.xdarker.security.controller;

import com.xdarker.security.dto.OrderDto;
import com.xdarker.security.dto.PriceDto;
import com.xdarker.security.server.resource.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
    public OrderDto create(@RequestBody OrderDto orderDto, @AuthenticationPrincipal String username) {

        log.info("username:{}", username);
//        PriceDto priceDto =  restTemplate.getForObject("http://localhost:9060/prices/" + orderDto.getProductId(), PriceDto.class);
//
//        log.info("price:{}",priceDto.getPrice());
        return orderDto;
    }

    @GetMapping("/{id}")
    public OrderDto getInfo(@PathVariable Long id, @AuthenticationPrincipal User user) {

        log.info("Order id:{}", id);
        log.info("user id:{}", user.getId());
//        PriceDto priceDto =  restTemplate.getForObject("http://localhost:9060/prices/" + orderDto.getProductId(), PriceDto.class);
//
//        log.info("price:{}",priceDto.getPrice());
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(id);
        return orderDto;
    }
}
