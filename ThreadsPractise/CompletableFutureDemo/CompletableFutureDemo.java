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




// import java.util.*;
// import java.util.concurrent.*;

// public class Main {

//     private static final int CHUNK_SIZE = 100;

//     private static final ExecutorService executor =
//             Executors.newFixedThreadPool(4);

//     private static long lastProcessedId = 0;

//     private static final List<Employee> employees =
//             new ArrayList<>();

//     public static void main(String[] args) {

//         // Simulate 1000 database records
//         for (int i = 1; i <= 1000; i++) {
//             employees.add(new Employee(i, "Employee-" + i));
//         }

//         processRecords();

//         executor.shutdown();
//     }

//     public static void processRecords() {

//         while (true) {

//             // 1. Fetch records using lastProcessedId
//             List<Employee> records =
//                     fetchRecords(lastProcessedId, CHUNK_SIZE);

//             if (records.isEmpty()) {
//                 break;
//             }

//             // 2. Split records into chunks
//             List<List<Employee>> chunks =
//                     partition(records, 25);

//             // 3. Execute chunks asynchronously
//             List<CompletableFuture<Void>> futures =
//                     new ArrayList<>();

//             for (List<Employee> chunk : chunks) {

//                 CompletableFuture<Void> future =
//                         CompletableFuture.runAsync(() -> {

//                             processChunk(chunk);

//                         }, executor);

//                 futures.add(future);
//             }

//             // 4. Wait for all chunks
//             CompletableFuture.allOf(
//                     futures.toArray(new CompletableFuture[0])
//             ).join();

//             // 5. Update processing marker
//             lastProcessedId =
//                     records.get(records.size() - 1).getId();

//             System.out.println(
//                     "Updated marker: " + lastProcessedId
//             );
//         }

//         System.out.println("All records processed!");
//     }

//     private static List<Employee> fetchRecords(
//             long lastId, int limit) {

//         return employees.stream()
//                 .filter(e -> e.getId() > lastId)
//                 .limit(limit)
//                 .toList();
//     }

//     private static void processChunk(List<Employee> chunk) {

//         System.out.println(
//                 "Processing " + chunk.size()
//                 + " records using "
//                 + Thread.currentThread().getName()
//         );

//         for (Employee employee : chunk) {

//             // Simulate business processing
//             employee.setStatus("COMPLETED");
//         }
//     }

//     private static List<List<Employee>> partition(
//             List<Employee> records, int chunkSize) {

//         List<List<Employee>> result = new ArrayList<>();

//         for (int i = 0; i < records.size(); i += chunkSize) {

//             result.add(
//                     records.subList(
//                             i,
//                             Math.min(i + chunkSize, records.size())
//                     )
//             );
//         }

//         return result;
//     }
// }





// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.List;
// import java.util.concurrent.CompletableFuture;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

// public class LogFileProcessor {

//     public static void main(String[] args) {

//         List<Employee> employees = List.of(
//                 new Employee(1, "Jagadeesh"),
//                 new Employee(2, "Ravi"),
//                 new Employee(3, "Kumar"),
//                 new Employee(4, "Suresh"),
//                 new Employee(5, "Prasad"),
//                 new Employee(6, "Arun"),
//                 new Employee(7, "Sai"),
//                 new Employee(8, "Manoj"),
//                 new Employee(9, "Vijay"),
//                 new Employee(10, "Krishna")
//         );

//         ExecutorService executor = Executors.newFixedThreadPool(4);

//         List<CompletableFuture<Void>> futures = employees.stream()
//                 .map(employee -> CompletableFuture.runAsync(
//                         () -> createLogFile(employee), executor))
//                 .toList();

//         CompletableFuture.allOf(
//                 futures.toArray(new CompletableFuture[0])
//         ).thenRun(() -> {
//             System.out.println("All 10 log files created successfully!");
//             executor.shutdown();
//         }).exceptionally(ex -> {
//             System.err.println("Log file creation failed: " + ex.getMessage());
//             executor.shutdown();
//             return null;
//         });

//     }

//     private static void createLogFile(Employee employee) {

//         String fileName = employee.getId() + "_" + employee.getName() + ".log";

//         try {
//             Path path = Path.of("logs", fileName);

//             Files.createDirectories(path.getParent());

//             Files.writeString(path,
//                     "Employee ID: " + employee.getId()
//                             + "\nEmployee Name: " + employee.getName());

//             System.out.println("Created: " + fileName);

//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }
//     }
// }

// class Employee {

//     private final int id;
//     private final String name;

//     public Employee(int id, String name) {
//         this.id = id;
//         this.name = name;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }
// }