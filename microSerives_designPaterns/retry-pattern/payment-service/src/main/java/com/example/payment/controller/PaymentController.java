package com.example.payment.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{orderId}")
    public String processPayment(@PathVariable Long orderId) {

        System.out.println("Payment request received for order: " + orderId);

        if (orderId == 101L) {
            throw new RuntimeException("Payment service temporarily unavailable");
        }

        return "Payment successful for order: " + orderId;
    }
}