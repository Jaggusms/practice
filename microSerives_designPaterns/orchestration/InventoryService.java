package com.example.saga.orchestration;

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

    @KafkaListener(topics = "orch.inventory.reserve",
            groupId = "inventory-service")
    public void reserveInventory(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Reserving inventory: " + message.orderId());

            if (message.orderId().contains("INVFAIL")) {
                throw new RuntimeException("Insufficient inventory");
            }

            // Reserve stock in database here.

            SagaMessage reserved = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("orch.inventory.reserved", util.toJson(reserved));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("orch.inventory.failed", util.toJson(failed));
        }
    }

    @KafkaListener(topics = "orch.inventory.release",
            groupId = "inventory-service")
    public void releaseInventory(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Releasing inventory: " + message.orderId());

            // Release reserved stock in database here.

            SagaMessage released = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("orch.inventory.released", util.toJson(released));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("orch.inventory.release.failed", util.toJson(failed));
        }
    }
}