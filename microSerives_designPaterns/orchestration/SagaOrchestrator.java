package com.example.saga.orchestration;

import com.example.saga.common.KafkaMessageUtil;
import com.example.saga.common.SagaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaOrchestrator {

    private final KafkaTemplate<String, String> kafka;
    private final KafkaMessageUtil util;

    public SagaOrchestrator(
            KafkaTemplate<String, String> kafka,
            KafkaMessageUtil util) {
        this.kafka = kafka;
        this.util = util;
    }

    public void startSaga(SagaMessage message) {

        System.out.println("Saga started: " + message.sagaId());

        kafka.send("orch.order.create", util.toJson(message));
    }

    // STEP 1: Order created successfully
    @KafkaListener(topics = "orch.order.created",
            groupId = "orchestrator")
    public void orderCreated(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("Orchestrator: Order created");

        kafka.send("orch.payment.process", util.toJson(message));
    }

    // STEP 2: Order creation failed
    @KafkaListener(topics = "orch.order.create.failed",
            groupId = "orchestrator")
    public void orderCreationFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("Saga failed: Order creation failed");
    }

    // STEP 3: Payment successful
    @KafkaListener(topics = "orch.payment.success",
            groupId = "orchestrator")
    public void paymentSuccess(String payload) {

        SagaMessage message = util.fromJson(payload);

        if ("SUCCESS".equals(message.status())) {

            System.out.println("Orchestrator: Payment successful");

            kafka.send("orch.inventory.reserve", util.toJson(message));

        } else {

            kafka.send("orch.order.cancel", util.toJson(message));
        }
    }

    // STEP 4: Payment failed
    @KafkaListener(topics = "orch.payment.failed",
            groupId = "orchestrator")
    public void paymentFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("Payment failed: " + message.reason());

        kafka.send("orch.order.cancel", util.toJson(message));
    }

    // STEP 5: Inventory reserved successfully
    @KafkaListener(topics = "orch.inventory.reserved",
            groupId = "orchestrator")
    public void inventoryReserved(String payload) {

        SagaMessage message = util.fromJson(payload);

        if ("SUCCESS".equals(message.status())) {

            System.out.println("Orchestrator: Inventory reserved");

            kafka.send("orch.order.complete", util.toJson(message));

        } else {

            kafka.send("orch.payment.refund", util.toJson(message));
        }
    }

    // STEP 6: Inventory reservation failed
    @KafkaListener(topics = "orch.inventory.failed",
            groupId = "orchestrator")
    public void inventoryFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("Inventory failed: " + message.reason());

        kafka.send("orch.payment.refund", util.toJson(message));
    }

    // STEP 7: Order completed
    @KafkaListener(topics = "orch.order.completed",
            groupId = "orchestrator")
    public void orderCompleted(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("SAGA SUCCESS: " + message.orderId());
    }

    // STEP 8: Order completion failed
    @KafkaListener(topics = "orch.order.completion.failed",
            groupId = "orchestrator")
    public void orderCompletionFailed(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("Order completion failed");

        kafka.send("orch.inventory.release", util.toJson(message));
    }

    // STEP 9: Inventory released
    @KafkaListener(topics = "orch.inventory.released",
            groupId = "orchestrator")
    public void inventoryReleased(String payload) {

        SagaMessage message = util.fromJson(payload);

        if ("SUCCESS".equals(message.status())) {

            kafka.send("orch.payment.refund", util.toJson(message));

        } else {

            System.out.println("ALERT: Inventory release failed");
            kafka.send("orch.payment.refund", util.toJson(message));
        }
    }

    // STEP 10: Payment refunded
    @KafkaListener(topics = "orch.payment.refunded",
            groupId = "orchestrator")
    public void paymentRefunded(String payload) {

        SagaMessage message = util.fromJson(payload);

        if ("SUCCESS".equals(message.status())) {

            kafka.send("orch.order.cancel", util.toJson(message));

        } else {

            System.out.println("ALERT: Payment refund failed");
        }
    }

    // STEP 11: Order cancelled
    @KafkaListener(topics = "orch.order.cancelled",
            groupId = "orchestrator")
    public void orderCancelled(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println("SAGA COMPENSATED: " + message.orderId());
    }
}