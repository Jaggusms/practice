@Service
@RequiredArgsConstructor
public class CircuitBreakerService {

    private final PaymentClient paymentClient;

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "paymentFallback"
    )
    public String processPayment(Long orderId) {

        return paymentClient.callPaymentService(orderId);
    }

    public String paymentFallback(
            Long orderId,
            Throwable exception) {

        return "Payment service unavailable. Please try again later.";
    }
}