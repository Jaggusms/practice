@Component
public class PaymentClient {

    private final RestClient restClient;

    public PaymentClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8082")
                .build();
    }

    public String callPaymentService(Long orderId) {

        return restClient.get()
                .uri("/payments/{id}", orderId)
                .retrieve()
                .body(String.class);
    }
}