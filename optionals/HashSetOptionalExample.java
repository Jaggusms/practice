import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class HashSetOptionalExample {

    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(null);
        numbers.add(40);
        numbers.add(50);

        for (Integer number : numbers) {

            Optional.ofNullable(number)
                    .ifPresent(value ->
                            System.out.println(value * 2)
                    );
        }
    }
}