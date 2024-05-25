package com.comrade.service;

import com.comrade.entity.Order;
import com.comrade.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("orderService")
@AllArgsConstructor
public class OrderService implements CommonService<Order>{

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> fetchAll() {
        return orderRepository.findAll();
    }
}
