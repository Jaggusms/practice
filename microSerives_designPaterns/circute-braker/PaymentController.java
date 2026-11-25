@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{id}")
    public String processPayment(@PathVariable Long id) {

        // Simulate payment service failure

        if (id == 500L) {
            throw new RuntimeException("Payment service unavailable");
        }

        return "Payment successful for order: " + id;
    }
}