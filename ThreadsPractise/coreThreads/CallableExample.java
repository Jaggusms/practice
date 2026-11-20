import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallableClass implements Callable<Integer> {

    @Overrides
    public Integer call() throws Exception {

        int sum = 0;

        for (int i = 1; i <= 5; i++) {
            sum += i;
        }

        return sum;
    }
}

public class CallableExample {

    public static void main(String[] args)
    throws InterruptedException, ExecutionException {

        Callable<Integer> task = new MyCallableClass();

        FutureTask<Integer> futureTask = new FutureTask<>(task);

        Thread t1 = new Thread(futureTask);

        t1.start();

        Integer result = futureTask.get();

        System.out.println("Result: " + result);
    }
}


// import java.util.concurrent.CompletableFuture;

// public class CodeTest {

//     public static void main(String[] args) {

//         CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//             System.out.println("Task running: " + Thread.currentThread().getName());

//             try {
//                 Thread.sleep(2000);
//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//             }

//             System.out.println("Task completed");
//         });

//         System.out.println("Main thread continues...");

//         future.join();

//         System.out.println("Main thread completed");
//     }
// }



// import java.util.concurrent.CompletableFuture;

// public class CodeTest {

//     public static void main(String[] args) {

//         performCompletableTask(10, 20);

//     }

//     public static void performCompletableTask(int a, int b) {

//         // 1. supplyAsync() - Returns a value asynchronously
//         CompletableFuture<Integer> future1 =
//                 CompletableFuture.supplyAsync(() -> {
//                     System.out.println("Task 1: Sum");
//                     return a + b;
//                 });

//         // 2. thenApply() - Transform the result
//         CompletableFuture<Integer> future2 =
//                 future1.thenApply(sum -> sum * 2);

//         // 3. thenAccept() - Consume the result
//         CompletableFuture<Void> future3 =
//                 future2.thenAccept(result ->
//                         System.out.println("Doubled Sum: " + result)
//                 );

//         // 4. thenRun() - Execute after previous task
//         CompletableFuture<Void> future4 =
//                 future3.thenRun(() ->
//                         System.out.println("Task 4 completed")
//                 );

//         // 5. thenCompose() - Chain another CompletableFuture
//         CompletableFuture<Integer> future5 =
//                 future1.thenCompose(sum ->
//                         CompletableFuture.supplyAsync(() -> sum + 100)
//                 );

//         // 6. thenCombine() - Combine two independent futures
//         CompletableFuture<Integer> future6 =
//                 future1.thenCombine(
//                         CompletableFuture.supplyAsync(() -> a * b),
//                         (sum, product) -> sum + product
//                 );

//         // 7. runAsync() - Execute without returning a value
//         CompletableFuture<Void> future7 =
//                 CompletableFuture.runAsync(() ->
//                         System.out.println("Independent async task")
//                 );

//         // 8. exceptionally() - Handle exceptions
//         CompletableFuture<Integer> future8 =
//                 CompletableFuture.supplyAsync(() -> {
//                     if (b == 0) {
//                         throw new RuntimeException("Invalid input");
//                     }
//                     return a / b;
//                 }).exceptionally(ex -> {
//                     System.out.println("Exception: " + ex.getMessage());
//                     return 0;
//                 });

//         // 9. handle() - Handle success and failure
//         CompletableFuture<Integer> future9 =
//                 future1.handle((result, ex) -> {
//                     if (ex != null) {
//                         return 0;
//                     }
//                     return result;
//                 });

//         // 10. anyOf() - Wait for any one future
//         CompletableFuture<Object> future10 =
//                 CompletableFuture.anyOf(future1, future5, future6);

//         // 11. allOf() - Wait for all futures
//         CompletableFuture<Void> allTasks =
//                 CompletableFuture.allOf(
//                         future4,
//                         future5,
//                         future6,
//                         future7,
//                         future8,
//                         future9
//                 );

//         // 12. join() - Wait and retrieve results
//         allTasks.join();

//         System.out.println("Sum: " + future1.join());
//         System.out.println("Compose Result: " + future5.join());
//         System.out.println("Combine Result: " + future6.join());
//         System.out.println("Division Result: " + future8.join());
//         System.out.println("Handle Result: " + future9.join());
//         System.out.println("AnyOf Result: " + future10.join());

//         System.out.println("All tasks completed!");
//     }
// }