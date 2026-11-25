package com.example.saga.choreography;

import com.example.saga.common.KafkaMessageUtil;
import com.example.saga.common.SagaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final KafkaTemplate<String, String> kafka;
    private final KafkaMessageUtil util;

    public InventoryService(KafkaTemplate<String, String> kafka,
                            KafkaMessageUtil util) {
        this.kafka = kafka;
        this.util = util;
    }

    // Payment completed -> reserve inventory
    @KafkaListener(topics = "choreo.payment.completed",
            groupId = "choreo-inventory-service")
    public void reserveInventory(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Reserving inventory: " + message.orderId());

            if (message.orderId().contains("INVFAIL")) {
                throw new RuntimeException("Insufficient stock");
            }

            // Reserve inventory in database.

            SagaMessage reserved = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("choreo.inventory.reserved", util.toJson(reserved));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("choreo.inventory.failed", util.toJson(failed));
        }
    }

    // Order completion failed -> release reserved inventory
    @KafkaListener(topics = "choreo.order.completion.failed",
            groupId = "choreo-inventory-service")
    public void releaseInventory(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Releasing inventory: " + message.orderId());

            // Release reserved stock in database.

            SagaMessage released = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("choreo.inventory.released", util.toJson(released));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("choreo.inventory.release.failed", util.toJson(failed));
        }
    }
}