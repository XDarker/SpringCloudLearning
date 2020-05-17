package com.xdarker.security.controller;

import com.xdarker.security.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
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
    public OrderDto create(@RequestBody OrderDto orderDto, @RequestHeader String username) {

        log.info("username:{}", username);
//        PriceDto priceDto =  restTemplate.getForObject("http://localhost:9060/prices/" + orderDto.getProductId(), PriceDto.class);
//
//        log.info("price:{}",priceDto.getPrice());
        return orderDto;
    }

    @GetMapping("/{id}")
    public OrderDto getInfo(@PathVariable Long id, @RequestHeader String username) {

        log.info("Order id:{}", id);
//        PriceDto priceDto =  restTemplate.getForObject("http://localhost:9060/prices/" + orderDto.getProductId(), PriceDto.class);
        log.info("username:{}", id);
//        log.info("price:{}",priceDto.getPrice());
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(id * 5);
        orderDto.setId(id);
        return orderDto;
    }
}
