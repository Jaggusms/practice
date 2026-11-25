package com.example.saga.orchestration;

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

    @KafkaListener(topics = "orch.payment.process",
            groupId = "payment-service")
    public void processPayment(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Processing payment: " + message.orderId());

            if (message.orderId().contains("PAYFAIL")) {
                throw new RuntimeException("Payment declined");
            }

            // Call payment gateway and save payment here.

            SagaMessage success = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("orch.payment.success", util.toJson(success));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("orch.payment.failed", util.toJson(failed));
        }
    }

    @KafkaListener(topics = "orch.payment.refund",
            groupId = "payment-service")
    public void refundPayment(String payload) {

        SagaMessage message = util.fromJson(payload);

        try {

            System.out.println("Refunding payment: " + message.orderId());

            // Refund through payment gateway here.

            SagaMessage refunded = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "SUCCESS",
                    null
            );

            kafka.send("orch.payment.refunded", util.toJson(refunded));

        } catch (Exception e) {

            SagaMessage failed = new SagaMessage(
                    message.sagaId(),
                    message.orderId(),
                    message.amount(),
                    "FAILED",
                    e.getMessage()
            );

            kafka.send("orch.payment.refund.failed", util.toJson(failed));
        }
    }
}