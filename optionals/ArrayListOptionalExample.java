import java.util.ArrayList;
import java.util.Optional;

public class ArrayListOptionalExample {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(null);
        numbers.add(40);
        numbers.add(null);
        numbers.add(50);

        for (Integer number : numbers) {

            Optional.ofNullable(number)
                    .ifPresent(value ->
                            System.out.println(value * 2)
                    );
        }



        int sum = 0;

        for (Integer number : numbers) {

            sum += Optional.ofNullable(number)
                    .orElse(0);
        }

        System.out.println("Sum: " + sum);


        int sum1 = numbers.stream()
        .filter(number -> Optional.ofNullable(number).isPresent())
        .mapToInt(Integer::intValue)
        .sum();

System.out.println("Sum: " + sum1);
    }
}