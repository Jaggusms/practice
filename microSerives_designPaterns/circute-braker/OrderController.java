@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/{id}/payment")
    public String processOrderPayment(@PathVariable Long id) {

        return circuitBreakerService.processPayment(id);
    }
}