package com.example.order.controller;

import com.example.order.service.OrderService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}/payment")
    public String processPayment(@PathVariable Long orderId) {

        return orderService.processOrder(orderId);
    }
}