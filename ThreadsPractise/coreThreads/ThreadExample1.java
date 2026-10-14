import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}
class MyTask implements Runnable {

    @Override
    public void run() {
        ArrayList<Integer> array =
                new ArrayList<>( IntStream.range(0, 11)
                        .boxed()
                        .toList());
        System.out.println ( array);
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                    Thread.currentThread().getName() + " : " + i
            );
        }
    }
}

public class    ThreadExample1 {

    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();



        MyTask task = new MyTask();

        Thread t11 = new Thread(task);
        Thread t22 = new Thread(task);

        t11.start();
        t22.start();




        Runnable task2 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(
                        Thread.currentThread().getName() + " : " + i
                );
            }
        };

        Thread t111 = new Thread(task2, "Worker-1");
        Thread t222 = new Thread(task2, "Worker-2");

        t111.start();
        t222.start();
    }
}