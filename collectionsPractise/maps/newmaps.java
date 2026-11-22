
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
// toMap
toMap(keyMapper, valueMapper)
toMap(
    Function.identity(),
    c -> 1
)
toMap(keyMapper, valueMapper, mergeFunction)
toMap(
    Function.identity(),
    c -> 1,
    Integer::sum
)
toMap(keyMapper, valueMapper, mergeFunction, mapSupplier)
toMap(
    Function.identity(),
    c -> 1,
    Integer::sum,
    HashMap::new
)

// groupingBy
groupingBy(classifier)
groupingBy(
    Function.identity()
)
groupingBy(classifier, downstream)
groupingBy(
    Function.identity(),
    Collectors.counting()
)
groupingBy(classifier, mapFactory, downstream)
groupingBy(
    Function.identity(),
    HashMap::new,
    Collectors.counting()
)
*/
public class newmaps {
    public static void main(String[] args) {
        HashMap<Character,Integer> map = new HashMap<>(Map.of('c',10,'a',2,'w',2,'b',3,'d',1));
        // HashMap<Character,Integer> map1 = new HashMap<>(map);
        System.out.println(map);
        // System.out.println(map1);
        HashMap<Integer, ArrayList<Character>> newMap= new HashMap<>();
        
        for(Map.Entry<Character,Integer> data: map.entrySet()){
            ArrayList<Character> values = newMap.getOrDefault(data.getValue(),new ArrayList<>());
            values.add(data.getKey());
            newMap.put(data.getValue(), values);
        }
        System.out.println(newMap);
        // HashMap<Character, Integer> map1 = map.entrySet()
        // .stream().
        // collect(Collectors.toMap(
        //     Map.Entry::getKey,
        //     Map.Entry::getValue,
        //     (o,n)->o+n,
        //     HashMap::new
        //  ));
        //  System.out.println(map1);

        // HashMap<Character, Integer> map1 = map.entrySet().stream().collect(Collectors.toMap(
        //     entry-> entry.getKey(),
        //     entry -> entry.getValue()+1,
        //     (o,n) -> n,
        //     HashMap::new
        // ));
        // System.out.println(map1);

        // HashMap<Character, String> map1 = map.entrySet().stream().collect(Collectors.toMap(
        //     entry-> entry.getKey(), 
        //     entry-> String.valueOf( entry.getKey()).repeat(entry.getValue())  ,
        //     (o,n)-> n,
        //     () -> new HashMap<>()
        // ));
        // System.out.println(map1);
        
        // String s = "Jagadeesh";
        // LinkedHashMap<String, Integer> map1  = Arrays.stream(s.split("")).collect(Collectors
        //     .toMap(
        //         c->c,
        //         c ->1,
        //         (o,n)->o+n,
        //         LinkedHashMap::new
        //     ));
        // System.out.println(map1);
        // ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map1.entrySet());
        // Collections.sort(list,(e1,e2)-> e2.getValue()-e1.getValue());
        // System.out.println(list);
        // int previous = -1;
        // int rank = 0;
        // int n=2;
        // for (Map.Entry<String, Integer> entry : list) {

        //     if (entry.getValue() != previous) {
        //         rank++;
        //         previous = entry.getValue();
        //     }

        //     if (rank == n) {
        //         System.out.println("Character: " + entry.getKey());
        //         System.out.println("Frequency: " + entry.getValue());
        //         break;
        //     }
        // }

        HashMap<Character,Integer> map11 = new HashMap<>(Map.of('c',10,'a',2,'w',2,'b',3,'d',1));
        

    }
    
}
