import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapOptionalExample {

    public static void main(String[] args) {

        Map<Integer, Integer> numbers = new HashMap<>();

        numbers.put(1, 10);
        numbers.put(2, 20);
        numbers.put(3, null);
        numbers.put(4, 40);
        numbers.put(5, null);
        numbers.put(6, 50);

        for (Integer number : numbers.values()) {

            Optional.ofNullable(number)
                    .ifPresent(value ->
                            System.out.println(value * 2)
                    );
        }


        for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {

            Optional.ofNullable(entry.getValue())
                    .ifPresent(value ->
                            System.out.println(
                                    "Key: " + entry.getKey()
                                            + ", Doubled value: " + (value * 2)
                            )
                    );
        }
    }
}