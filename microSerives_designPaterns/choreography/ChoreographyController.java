package com.example.saga.choreography;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/choreography")
public class ChoreographyController {

    private final OrderService orderService;

    public ChoreographyController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders/{orderId}")
    public String createOrder(
            @PathVariable String orderId,
            @RequestParam double amount) {

        orderService.createOrder(orderId, amount);

        return "Choreography Saga started";
    }
}