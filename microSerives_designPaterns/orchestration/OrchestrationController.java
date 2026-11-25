package com.example.saga.orchestration;

import com.example.saga.common.SagaMessage;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orchestration")
public class OrchestrationController {

    private final SagaOrchestrator orchestrator;

    public OrchestrationController(SagaOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping("/orders/{orderId}")
    public String createOrder(
            @PathVariable String orderId,
            @RequestParam double amount) {

        String sagaId = UUID.randomUUID().toString();

        SagaMessage message = new SagaMessage(
                sagaId,
                orderId,
                amount,
                "STARTED",
                null
        );

        orchestrator.startSaga(message);

        return "Orchestration Saga started: " + sagaId;
    }
}