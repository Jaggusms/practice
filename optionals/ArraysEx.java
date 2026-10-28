import java.util.Optional;

public class ArraysEx {
    public static void main(String[] args) {

        Integer[] numbers = {10, 20, null, 40, null, 50};

        for (Integer number : numbers) {

            Optional.ofNullable(number)
                    .ifPresent(value ->
                            System.out.println(value * 2)
                    );
        }

        for (Integer number : numbers) {

            Optional.ofNullable(number)
                    .ifPresentOrElse(
                            value -> System.out.println("Value: " + value * 2),
                            () -> System.out.println("Value is null")
                    );
        }
    }
}