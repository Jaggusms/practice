package com.example.order.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentClient {

    private final RestClient restClient;

    public PaymentClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8082")
                .build();
    }

    public String processPayment(Long orderId) {

        return restClient.get()
                .uri("/payments/{orderId}", orderId)
                .retrieve()
                .body(String.class);
    }
}