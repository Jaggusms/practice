import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ProcessPoolDemo demonstrates how to simulate a Process Pool in Java.
 * It uses a standard ExecutorService thread pool where each worker thread
 * launches and manages a separate OS process via ProcessBuilder.
 */
public class ProcessPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Define a list of commands/tasks to run in separate processes
        // (Using platform-independent commands, e.g., printing Java version via java -version)
        List<String[]> commands = new ArrayList<>();
        commands.add(new String[]{"java", "-version"});
        commands.add(new String[]{"java", "-version"});
        commands.add(new String[]{"java", "-version"});

        // Create a thread pool to manage concurrent process execution
        ExecutorService processPool = Executors.newFixedThreadPool(3);
        List<Future<String>> futures = new ArrayList<>();

        System.out.println("=== Starting Multi-Process Execution Pool ===");

        for (String[] command : commands) {
            Callable<String> worker = () -> {
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                // Redirect error stream to output stream so we can capture stderr (where java -version prints)
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

                // Read the output from the spawned process
                StringBuilder output = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                }

                int exitCode = process.waitFor();
                return "Process PID/ExitCode [" + exitCode + "] Output:\n" + output.toString().trim();
            };

            futures.add(processPool.submit(worker));
        }

        // Collect and print results from all processes
        for (int i = 0; i < futures.size(); i++) {
            System.out.println("\n--- Result from Process Task " + (i + 1) + " ---");
            System.out.println(futures.get(i).get());
        }

        processPool.shutdown();
        System.out.println("\n=== Process Pool Completed ===");
    }
}