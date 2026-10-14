import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallableClass implements Callable<Integer> {

    @Override
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