package com.comrade.controller;

import com.comrade.entity.Order;
import com.comrade.service.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(@Qualifier("orderService") OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/fetchAll")
    public List<Order> fetchAll() {
        return orderService.fetchAll();
    }
}
