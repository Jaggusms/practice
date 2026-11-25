
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class firstNonRepeatingchar {
    public static void main(String[] args) {
        String s = "AABEEECABCDEFS";
        LinkedHashMap<String, Long> counter = Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(
            c->c,
            LinkedHashMap::new,
            Collectors.counting()
        ));
        System.out.println(counter);

        counter.entrySet().stream().sorted(Map.Entry.comparingByValue())
        .filter((Map.Entry<String, Long> e)->e.getValue()<2).findFirst().ifPresent(System.out::println);

        counter.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        // .filter((Map.Entry<String, Long> e)->e.getValue()<2)
        .findFirst().ifPresent(System.out::println);

        counter.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        // .filter((Map.Entry<String, Long> e) -> e.getValue() < 2)
        .findFirst()
        .ifPresent(System.out::println);


        counter.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .filter(
            (e)-> e.getValue() < counter.values().stream()
                                    .max((e1,e2)->e1.compareTo(e2))
                                    .orElse(0L))
        .findFirst()
        .ifPresent(System.out::println);
    
    }
}
