import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // Run an asynchronous task
        CompletableFuture.supplyAsync(() -> {
            // Simulate a background computation or I/O operation
            return "hello world";
        })
        .thenApply(result -> {
            // Transform the result (converts to uppercase)
            return result.toUpperCase();
        })
        .thenAccept(processedResult -> {
            // Consume the final output
            System.out.println("Final Output: " + processedResult);
        });

        // Wait briefly to allow the asynchronous pipeline to finish before main exits
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}