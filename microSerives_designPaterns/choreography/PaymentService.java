package com.example.saga.choreography;

import com.example.saga.common.KafkaMessageUtil;
import com.example.saga.common.SagaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final KafkaTemplate<String, String> kafka;
    private final KafkaMessageUtil util;

    public PaymentService(KafkaTemplate<String, String> kafka,
                          KafkaMessageUtil util) {
        this.kafka = kafka;
        this.util = util;
    }

    // Order created -> process payment
    @KafkaListener(topics = "choreo.order.created",
            groupId = "choreo-payment-service")
    public void processPayment(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Processing payment: " + message.orderId());

            if (message.orderId().contains("PAYFAIL")) {
                throw new RuntimeException("Payment declined");
            }

            // Process payment and save payment record here.

            SagaMessage success = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("choreo.payment.completed", util.toJson(success));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("choreo.payment.failed", util.toJson(failed));
        }
    }

    // Inventory failure -> refund payment
    @KafkaListener(topics = {
            "choreo.inventory.failed",
            "choreo.order.completion.failed"
    }, groupId = "choreo-payment-service")
    public void compensatePayment(String payload) {

        SagaMessage message = util.fromJson(payload);

        refundPayment(message);
    }

    private void refundPayment(SagaMessage message) {

        try {

            System.out.println("Refunding payment: " + message.orderId());

            // Refund through payment gateway.
            // Update payment record to REFUNDED.

            SagaMessage refunded = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("choreo.payment.refunded", util.toJson(refunded));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("choreo.payment.refund.failed", util.toJson(failed));
        }
    }
}