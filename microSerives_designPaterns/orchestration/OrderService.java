package com.example.saga.orchestration;

import com.example.saga.common.KafkaMessageUtil;
import com.example.saga.common.SagaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final KafkaTemplate<String, String> kafka;
    private final KafkaMessageUtil util;

    public OrderService(KafkaTemplate<String, String> kafka,
                        KafkaMessageUtil util) {
        this.kafka = kafka;
        this.util = util;
    }

    @KafkaListener(topics = "orch.order.create",
            groupId = "order-service")
    public void createOrder(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Creating order: " + message.orderId());

            if (message.orderId().contains("CREATEFAIL")) {
                throw new RuntimeException("Order creation failed");
            }

            // Save order to database here.

            SagaMessage success = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("orch.order.created", util.toJson(success));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("orch.order.create.failed", util.toJson(failed));
        }
    }

    @KafkaListener(topics = "orch.order.complete",
            groupId = "order-service")
    public void completeOrder(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Completing order: " + message.orderId());

            // Update order status to COMPLETED in database.

            SagaMessage success = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("orch.order.completed", util.toJson(success));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("orch.order.completion.failed", util.toJson(failed));
        }
    }

    @KafkaListener(topics = "orch.order.cancel",
            groupId = "order-service")
    public void cancelOrder(String payload) {

        SagaMessage message = util.fromJson(payload);

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

            kafka.send("orch.order.cancelled", util.toJson(cancelled));

        } catch (Exception e) {

            System.out.println("CRITICAL: Order cancellation failed: "
                    + e.getMessage());
        }
    }
}