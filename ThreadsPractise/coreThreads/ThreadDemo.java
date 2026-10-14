//import java.util.concurrent.*;
//import java.util.List;
//import java.util.ArrayList;
//
///**
// * ThreadDemo demonstrates various ways of handling concurrency in Java:
// * 1. Normal Thread (Extending Thread)
// * 2. Runnable Interface
// * 3. Callable Interface (with Future)
// * 4. Simple Thread Pool (FixedThreadPool)
// * 5. Custom Thread Pool (ThreadPoolExecutor with core/max workers and queue size)
// * 6. Cached Thread Pool
// * 7. Virtual Threads (Project Loom)
// *
// * --- ARCHITECTURAL DIFFERENCES ---
// * - Normal Threads (Platform Threads):
// *   Mapped 1:1 to OS kernel threads. They are heavyweight, have a large stack size (~1MB),
// *   and creating/destroying them is expensive. Limited by OS resources (thousands max).
// *
// * - ExecutorService (ThreadPool):
// *   A high-level framework introduced in Java 1.5 (JDK 5) that reuses a fixed or dynamic pool
// *   of platform threads to execute submitted tasks, avoiding the overhead of thread creation
// *   per task. Best for CPU-bound tasks.
// *
// * - Virtual Threads:
// *   Lightweight threads introduced in Java 21. Managed by the JVM rather than the OS (M:N scheduling).
// *   Millions of virtual threads can run concurrently with negligible memory overhead.
// *   Ideal for I/O-bound tasks.
// *
// * --- OTHER AVAILABLE EXECUTORS (Java 1.5 / JDK 5) ---
// * - Executors.newSingleThreadExecutor(): Executes tasks sequentially using a single worker thread.
// * - Executors.newScheduledThreadPool(int corePoolSize): Supports delayed or periodic task execution.
// */
//public class ThreadDemo {
//
//    public static void main(String[] args) throws Exception {
//        System.out.println("=== 1. Normal Thread (Extending Thread) ===");
//        method1NormalThread();
//
//        System.out.println("\n=== 2. Runnable Interface ===");
//        method2Runnable();
//
//        System.out.println("\n=== 3. Callable Interface ===");
//        method3Callable();
//
//        System.out.println("\n=== 4. Simple Thread Pool ===");
//        method4SimpleThreadPool();
//
//        System.out.println("\n=== 4b. Custom Thread Pool (Min/Max Workers & Queue Size) ===");
//        method4CustomThreadPool();
//
//        System.out.println("\n=== 5. Cached Thread Pool ===");
//        method5CachedThreadPool();
//
//        System.out.println("\n=== 6. Virtual Threads ===");
//        method6VirtualThreads();
//    }
//
//    /**
//     * Method 1: Normal Thread by extending the java.lang.Thread class.
//     * Direct OS thread mapping; tightly couples task logic with thread management.
//     */
//    public static void method1NormalThread() throws InterruptedException {
//        class MyThread extends Thread {
//            @Override
//            public void run() {
//                System.out.println("Running inside Normal Thread: " + Thread.currentThread().getName());
//            }
//        }
//
//        MyThread t = new MyThread();
//        t.start();
//        t.join(); // Wait for thread to finish
//    }
//
//    /**
//     * Method 2: Using the Runnable functional interface.
//     * Separates task logic from the thread object, allowing better code reuse.
//     */
//    public static void method2Runnable() throws InterruptedException {
//        Runnable task = () -> {
//            System.out.println("Running inside Runnable Task: " + Thread.currentThread().getName());
//        };
//
//        Thread t = new Thread(task);
//        t.start();
//        t.join();
//    }
//
//    /**
//     * Method 3: Using the Callable interface.
//     * Similar to Runnable, but can return a result and throw checked exceptions via a Future object.
//     */
//    public static void method3Callable() throws Exception {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Callable<String> task = () -> {
//            return "Result from Callable task executed on " + Thread.currentThread().getName();
//        };
//
//        Future<String> future = executor.submit(task);
//        System.out.println(future.get()); // Blocks until result is available
//        executor.shutdown();
//    }
//
//    /**
//     * Method 4: Simple Thread Pool using Executors.newFixedThreadPool.
//     * Reuses a fixed number of threads operating off a shared unbounded queue.
//     */
//    public static void method4SimpleThreadPool() {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        for (int i = 1; i <= 3; i++) {
//            final int taskId = i;
//            executor.submit(() -> {
//                System.out.println("Task " + taskId + " running on " + Thread.currentThread().getName());
//            });
//        }
//        executor.shutdown();
//    }
//
//    /**
//     * Method 4 (Custom): ThreadPoolExecutor with explicit min (core) workers,
//     * max workers, and a bounded queue size to prevent memory exhaustion under heavy load.
//     */
//    public static void method4CustomThreadPool() {
//        ThreadPoolExecutor customExecutor = new ThreadPoolExecutor(
//                2,                                  // corePoolSize (min workers)
//                4,                                  // maximumPoolSize (max workers)
//                60L,                                // keepAliveTime
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(2)         // bounded queue size of 2
//        );
//
//        for (int i = 1; i <= 5; i++) {
//            final int taskId = i;
//            customExecutor.submit(() -> {
//                System.out.println("Custom Pool Task " + taskId + " running on " + Thread.currentThread().getName());
//                try { Thread.sleep(500); } catch (InterruptedException e) { }
//            });
//        }
//        customExecutor.shutdown();
//    }
//
//    /**
//     * Method 5: Cached Thread Pool (Executors.newCachedThreadPool).
//     * Creates new threads as needed, but will reuse previously constructed threads when they are available.
//     * Good for short-lived asynchronous tasks.
//     */
//    public static void method5CachedThreadPool() {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        for (int i = 1; i <= 3; i++) {
//            final int taskId = i;
//            executor.submit(() -> {
//                System.out.println("Cached Pool Task " + taskId + " running on " + Thread.currentThread().getName());
//            });
//        }
//        executor.shutdown();
//    }
//
//    /**
//     * Method 6: Virtual Threads (Introduced in Java 21 via Project Loom).
//     * Lightweight threads managed entirely by the JVM, enabling massive concurrency for I/O operations.
//     */
//    public static void method6VirtualThreads() throws Exception {
//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            executor.submit(() -> {
//                System.out.println("Running inside Virtual Thread: " + Thread.currentThread().getName());
//            });
//        } // Auto-closes and waits for tasks to complete
//    }
//}



/*
The story behind virtual threads—known inside Oracle as Project Loom—is one of the most fascinating engineering evolutions in Java's history. It took years of deep, architectural rethinking of the Java Virtual Machine (JVM) to finally land as a standard feature in Java 21.

Here is the story of why they were needed, why it took so long, and how they changed Java forever.
1. The Core Problem: The Thread-Per-Request Model vs. Scale

For decades, Java’s web and server applications relied on a "thread-per-request" model. When a user clicked a button or made an API call, a server thread would pick up the request, talk to a database, call an external service, and return the response.

    The Catch: Java threads were tightly bound to OS threads (platform threads), each carrying a hefty ~1 MB stack size.

    The Consequence: If your server had 4,000 concurrent users, you needed 4,000 OS threads. Trying to scale to hundreds of thousands or millions of concurrent users meant running completely out of memory or hitting OS thread limits.

2. The Era of Reactive & Asynchronous Code (The Pain)

To handle massive scale without running out of threads, developers were forced to abandon straightforward blocking code. Instead, they adopted asynchronous and reactive programming (using frameworks like RxJava, Project Reactor, Netty, or CompletableFuture).

    While this allowed applications to handle high traffic with fewer threads, it came at a massive cost: developer experience.

    Code became tangled in callbacks, making stack traces nearly impossible to read, debugging a nightmare, and turning simple business logic into complex state machines. Developers were effectively building their own manual schedulers because the JVM couldn't do it efficiently.

3. Enter Project Loom (Around 2017–2018)

Oracle engineers (led by Ron Pressler, Alan Bateman, and others) asked a bold question: What if we could keep the simple, readable, blocking style of writing code, but make threads so lightweight that you can have millions of them?

This initiative became Project Loom. The vision was to implement user-mode threads (managed entirely by the JVM, similar to Go's goroutines or Erlang's processes) so that blocking an operation wouldn't block an expensive OS thread.
4. Why Did It Take Until Java 21? (The Engineering Hurdles)

Bringing virtual threads to Java wasn't just a minor patch; it required touching foundational layers of the JVM. Several major hurdles kept it in the oven for years:

    Rewriting the Networking & I/O Stack: Every socket, file channel, and blocking mechanism in the JDK had to be updated. When a virtual thread tries to read from a socket and blocks, the JVM needs to automatically "unmount" it and let a real OS thread work on something else.

    The synchronized Block Pinning Issue: In older Java code, synchronized blocks would "pin" a virtual thread to its underlying OS carrier thread, preventing it from unmounting. Solving this required extensive updates to Java's monitor and synchronization mechanisms.

    Debugging and Tooling: Tools like thread dumps, profilers, and debuggers had to be completely overhauled. If an application has 2 million virtual threads, running a traditional thread dump would crash the system or generate gigabytes of useless text. The team had to introduce structured concurrency and lightweight thread dump tools.

5. The Preview Phases and Final Release

Rather than rushing it out, the Java team took a cautious, iterative approach:

    Java 19 (2022): First previewed as an incubator feature.

    Java 20 (Early 2023): Second preview to refine APIs based on developer feedback.

    Java 21 (September 2023): Permanently finalized as a core, production-ready feature (JEP 444).

Today, virtual threads allow modern Java applications to handle massive, web-scale I/O workloads with simple, traditional code—bridging the gap between extreme performance and developer sanity.
 */


import java.io.*;
import java.nio.file.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;

/**
 * ThreadDemo demonstrates various ways of handling concurrency in Java,
 * including independent file reading, sum calculation, and UTC timestamp logging.
 *
 * --- ARCHITECTURAL DIFFERENCES ---
 * - Normal Threads (Platform Threads):
 *   Mapped 1:1 to OS kernel threads. They are heavyweight, have a large stack size (~1MB),
 *   and creating/destroying them is expensive. Limited by OS resources (thousands max).
 *
 * - ExecutorService (ThreadPool):
 *   A high-level framework introduced in Java 1.5 (JDK 5) that reuses a fixed or dynamic pool
 *   of platform threads to execute submitted tasks, avoiding the overhead of thread creation
 *   per task. Best for CPU-bound tasks.
 *
 * - Virtual Threads:
 *   Lightweight threads introduced in Java 21. Managed by the JVM rather than the OS (M:N scheduling).
 *   Millions of virtual threads can run concurrently with negligible memory overhead.
 *   Ideal for I/O-bound tasks.
 *
 * --- OTHER AVAILABLE EXECUTORS (Java 1.5 / JDK 5) ---
 * - Executors.newSingleThreadExecutor(): Executes tasks sequentially using a single worker thread.
 * - Executors.newScheduledThreadPool(int corePoolSize): Supports delayed or periodic task execution.
 */
public class ThreadDemo {

    private static final DateTimeFormatter UTC_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z").withZone(ZoneId.of("UTC"));

    public static void main(String[] args) throws Exception {
        // Setup a sample input file with numbers 1 to 10 (one per line)
        String sampleFileName = "numbers.txt";
        createSampleFile(sampleFileName);

        System.out.println("=== 1. Normal Thread (File Reading & Sum) ===");
        method1NormalThread(sampleFileName);

        System.out.println("\n=== 2. Runnable Interface (File Reading & Sum) ===");
        method2Runnable(sampleFileName);

        System.out.println("\n=== 3. Callable Interface (File Reading & Sum) ===");
        method3Callable(sampleFileName);

        System.out.println("\n=== 4. Simple Thread Pool (File Reading & Sum) ===");
        method4SimpleThreadPool(sampleFileName);

        System.out.println("\n=== 4b. Custom Thread Pool (Min/Max Workers & Queue Size) ===");
        method4CustomThreadPool(sampleFileName);

        System.out.println("\n=== 5. Cached Thread Pool (File Reading & Sum) ===");
        method5CachedThreadPool(sampleFileName);

        System.out.println("\n=== 6. Virtual Threads (File Reading & Sum) ===");
        method6VirtualThreads(sampleFileName);
    }

    private static void createSampleFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            lines.add(String.valueOf(i));
        }
        Files.write(Paths.get(fileName), lines);
    }

    private static void processFileTask(String fileName) {
        int sum = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sum += Integer.parseInt(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        String utcTime = UTC_FORMATTER.format(Instant.now());
        System.out.println("Thread [" + Thread.currentThread().getName() +
                           "] completed reading file. Sum = " + sum +
                           " | Completed at (UTC): " + utcTime);
    }

    /**
     * Method 1: Normal Thread by extending the java.lang.Thread class.
     */
    public static void method1NormalThread(String fileName) throws InterruptedException {
        class FileReadThread extends Thread {
            @Override
            public void run() {
                processFileTask(fileName);
            }
        }

        FileReadThread t = new FileReadThread();
        t.start();
        t.join();
    }

    /**
     * Method 2: Using the Runnable functional interface.
     */
    public static void method2Runnable(String fileName) throws InterruptedException {
        Runnable task = () -> processFileTask(fileName);
        Thread t = new Thread(task);
        t.start();
        t.join();
    }

    /**
     * Method 3: Using the Callable interface.
     */
    public static void method3Callable(String fileName) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            int sum = 0;
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sum += Integer.parseInt(line.trim());
                }
            }
            String utcTime = UTC_FORMATTER.format(Instant.now());
            System.out.println("Callable Thread completed reading. Sum = " + sum + " | UTC: " + utcTime);
            return sum;
        };

        Future<Integer> future = executor.submit(task);
        future.get(); // Wait for result
        executor.shutdown();
    }

    /**
     * Method 4: Simple Thread Pool using Executors.newFixedThreadPool.
     */
    public static void method4SimpleThreadPool(String fileName) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 2; i++) {
            executor.submit(() -> processFileTask(fileName));
        }
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
    }

    /**
     * Method 4 (Custom): ThreadPoolExecutor with core/max workers and bounded queue size.
     */
    public static void method4CustomThreadPool(String fileName) throws InterruptedException {
        ThreadPoolExecutor customExecutor = new ThreadPoolExecutor(
                2, 4, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2)
        );

        for (int i = 0; i < 3; i++) {
            customExecutor.submit(() -> processFileTask(fileName));
        }
        customExecutor.shutdown();
        customExecutor.awaitTermination(2, TimeUnit.SECONDS);
    }

    /**
     * Method 5: Cached Thread Pool (Executors.newCachedThreadPool).
     */
    public static void method5CachedThreadPool(String fileName) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> processFileTask(fileName));
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
    }

    /**
     * Method 6: Virtual Threads (Introduced in Java 21).
     */
    public static void method6VirtualThreads(String fileName) throws Exception {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> processFileTask(fileName));
        }
    }
}