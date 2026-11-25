import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class secondHighestRepeatingcharector {
    public static void main(String[] args) {
        String s = "Jagadeesh";
        LinkedHashMap<String, ArrayList<String>> group = Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(
                c->c,
                LinkedHashMap::new,
                Collectors.toCollection(ArrayList::new)
             ));
        Map<String, List<String>> group1 =
            Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy(c -> c));

        System.out.println(group);
        // LinkedHashMap<String, Integer> counter = Arrays.stream(s.split(""))
        // .collect(Collectors.toMap(
        //     (entry)-> entry,
        //     (entry)-> 1,
        //     (o,n) ->o+n,
        //     LinkedHashMap::new
        // ));
        // System.out.println(counter);
        // counter.entrySet().stream()
        // .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
        // counter.entrySet().stream()
        // .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        // .skip(1)
        // .findFirst()
        // .ifPresent((e)->System.out.println(e.getKey()));

        // counter.entrySet().stream()
        // .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        // .filter((e)-> e.getValue()< counter.values().stream().max((e1,e2)-> e1-e2).orElse(0))
        // .skip(1)
        // .findFirst()
        // .ifPresent((e)->System.out.println(e.getKey()));




        // LinkedHashMap<Character, Integer> counter = new LinkedHashMap<>();
        // for(char str:s.toCharArray()){
        //     counter.put(str, counter.getOrDefault(str, 0)+1);
        // }
        // System.out.println(counter);
        // List<Map.Entry<Character, Integer>> data = new ArrayList<>(counter.entrySet());
        // Collections.sort(data, (e1,e2)-> e2.getValue()-e1.getValue());
        // System.out.println(data);
        // int n =1;
        // int rank = 0;
        // int cuurentHighest = Integer.MAX_VALUE;
        // for(Map.Entry<Character, Integer> entry:data){
        //     // System.out.println(entry.getKey());
        //     int counterValue = entry.getValue() ;
        //     if (counterValue<cuurentHighest){
        //         rank++;
        //         cuurentHighest = counterValue;
        //     }
        //     if (rank==n){
        //         System.out.println(entry.getKey());
        //         break;
        //     }
        // }
        
    }
}
