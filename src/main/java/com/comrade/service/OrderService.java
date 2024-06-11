package com.comrade.service;

import com.comrade.entity.Order;
import com.comrade.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service("orderService")
@AllArgsConstructor
@Slf4j
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

    @Override
    public void update(){
        List<Order>  orderList =  orderRepository.findAll();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int chunkSize = 5000;
        List<List<Order>> listList = new ArrayList<>(orderList.stream().collect(Collectors.groupingBy(order -> atomicInteger.incrementAndGet()/chunkSize)).values());

        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        listList.forEach(orders -> {
            log.info("============Outer loop ");
            forkJoinPool.submit(() -> {
                orders.parallelStream().forEach(order -> {
                    log.info("============Inner loop ");
                    order.setOrderName(order.getOrderName()+"_UpDated");
                });
                log.info("============Before saving");
                orderRepository.saveAll(orders);
                log.info("============After saving");
            });
        });
    }
}
