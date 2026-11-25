package com.example.saga.choreography;

import com.example.saga.common.KafkaMessageUtil;
import com.example.saga.common.SagaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaMessageUtil util;

    public NotificationService(KafkaMessageUtil util) {
        this.util = util;
    }

    @KafkaListener(topics = "choreo.order.completed",
            groupId = "choreo-notification-service")
    public void orderCompleted(String payload) {

        SagaMessage message = util.fromJson(payload);

        System.out.println(
                "Notification: Order successfully completed: "
                        + message.orderId()
        );
    }
}