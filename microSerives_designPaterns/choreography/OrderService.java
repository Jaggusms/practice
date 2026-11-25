package com.example.saga.choreography;

import com.example.saga.common.KafkaMessageUtil;
import com.example.saga.common.SagaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final KafkaTemplate<String, String> kafka;
    private final KafkaMessageUtil util;

    public OrderService(KafkaTemplate<String, String> kafka,
                        KafkaMessageUtil util) {
        this.kafka = kafka;
        this.util = util;
    }

    // Initial operation
    public void createOrder(String orderId, double amount) {

        String sagaId = UUID.randomUUID().toString();

        try {

            System.out.println("Creating order: " + orderId);

            if (orderId.contains("CREATEFAIL")) {
                throw new RuntimeException("Order creation failed");
            }

            // Save order to database here.

            SagaMessage event = new SagaMessage(
                    sagaId, orderId, amount, "SUCCESS", null
            );

            kafka.send("choreo.order.created", util.toJson(event));

        } catch (Exception e) {

            System.out.println("Order creation failed: " + e.getMessage());
        }
    }

    // Payment completed -> inventory reserved -> order completed
    @KafkaListener(topics = "choreo.inventory.reserved",
            groupId = "choreo-order-service")
    public void inventoryReserved(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Inventory reserved. Completing order.");

            if (message.orderId().contains("COMPLETEFAIL")) {
                throw new RuntimeException("Order completion failed");
            }

            // Update order status to COMPLETED in database.

            SagaMessage completed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("choreo.order.completed", util.toJson(completed));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("choreo.order.completion.failed",
                    util.toJson(failed));
        }
    }

    // Payment failure -> cancel order
    @KafkaListener(topics = "choreo.payment.failed",
            groupId = "choreo-order-service")
    public void paymentFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        cancelOrder(message);
    }

    // Inventory failure -> cancel order
    @KafkaListener(topics = "choreo.inventory.failed",
            groupId = "choreo-order-service")
    public void inventoryFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        cancelOrder(message);
    }

    // Order completion failure -> cancel order
    @KafkaListener(topics = "choreo.order.completion.failed",
            groupId = "choreo-order-service")
    public void orderCompletionFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        cancelOrder(message);
    }

    private void cancelOrder(SagaMessage message) {

        try {

            System.out.println("Cancelling order: " + message.orderId());

            // Update order status to CANCELLED in database.

            SagaMessage cancelled = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("choreo.order.cancelled", util.toJson(cancelled));

        } catch (Exception e) {

            System.out.println("Order cancellation failed: " + e.getMessage());
        }
    }
}