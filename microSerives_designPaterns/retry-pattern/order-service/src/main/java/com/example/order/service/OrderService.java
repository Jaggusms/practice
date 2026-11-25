package com.example.order.service;

import com.example.order.client.PaymentClient;

import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentClient paymentClient;

    public OrderService(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Retry(
        name = "paymentService",
        fallbackMethod = "paymentFallback"
    )
    public String processOrder(Long orderId) {

        System.out.println("Calling Payment Service...");

        return paymentClient.processPayment(orderId);
    }

    public String paymentFallback(
            Long orderId,
            Throwable exception) {

        System.out.println("All retry attempts exhausted!");

        return "Payment failed after retries: "
                + exception.getMessage();
    }
}