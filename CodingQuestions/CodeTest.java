// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Comparator;

// public  class CodeTest {
//     public static void main(String[] args) {
//         ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//         ArrayList<ArrayList<Integer>> resultArray = new ArrayList<>();

//         for (int allCOmbinations=0; allCOmbinations<arr.size()+1; allCOmbinations++){
//             generateCombinations(arr, allCOmbinations, 0, new ArrayList<>(), resultArray);
//         }
//         resultArray.sort(Comparator.comparingInt((ArrayList<Integer> e )-> e.size()).reversed());
//         System.out.println(resultArray);
        

//         int sum = 0;
//         for (ArrayList<Integer> combination: resultArray){
//             int summation = combination.stream().mapToInt(Integer::intValue).sum();
//             sum +=summation;
//             System.out.println(combination+" "+ summation);

//         }
//         System.out.println(sum);

//     }

//     public static void  generateCombinations(
//         ArrayList<Integer> inputArray,
//         int sizeOfCombination,
//         int start,
//         ArrayList<Integer> cuurenArray,
//         ArrayList<ArrayList<Integer>> resultArray
//     ){

//         if (cuurenArray.size()==sizeOfCombination){
//             resultArray.add(new ArrayList<>(cuurenArray));
//             return ;
//         }
//         for (int currentIndex =start; currentIndex<inputArray.size(); currentIndex++){
//             cuurenArray.add(inputArray.get(currentIndex));
//             generateCombinations(inputArray, sizeOfCombination, currentIndex+1, cuurenArray, resultArray);
//             cuurenArray.remove(cuurenArray.size()-1);
//         }

//     }
// }

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.List;
// import java.util.Scanner;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// public class CodeTest {
//     public static void main(String[] args) {
//         String originalString = "Hello".toLowerCase();
//         String secondString = new String("Hello").toLowerCase();

//         char[] characterArray = originalString.toCharArray(); // tocharrry will give us primitive chars..
//         String[] stringArray = originalString.split("");

//         System.out.println(Arrays.toString(stringArray));
//         System.out.println(Arrays.toString(characterArray));

//         // Collections.sort(characterArray);
//         // Collections.sort(stringArray);  collection sort won't work here..

//         Arrays.sort(stringArray);
//         Arrays.sort(characterArray);

//         System.out.println(String.join("-", stringArray));
//         // System.out.println(String.join("-", characterArray)); // it worn't work primitive
//         System.out.println(Arrays.toString(characterArray));

//         // Arrays.sort(stringArray, Comparator.comparing((e1, e2) -> e1 - e2)); //comparing accept only one
//         Arrays.sort(stringArray, (e1, e2) -> e2.compareTo(e1));
//         // Arrays.sort(characterArray, Comparator.reverseOrder());  // Comparator won't work with primitive works..

//         System.out.println(String.join("-", stringArray));
//         System.out.println(Arrays.toString(characterArray));

//         System.out.println(new StringBuffer("hellloa").reverse());


//         Scanner sc = new Scanner(System.in);
//         String names = sc.nextLine();
//         String ages = sc.nextLine();
//         ArrayList<String> namesList = new ArrayList<>();
//         for (String name:names.split(",")){
//             namesList.add(name);
//             System.out.println(name.length());
//         }
//         ArrayList<Integer> agesList = new ArrayList<>();
//         for (String age:ages.split(",")){
//             agesList.add(Integer.valueOf(age));
//         }
//         System.out.println(names);
//         System.out.println(ages);

//         System.out.println(namesList);
//         System.out.println(agesList);
//         ArrayList<String> namesList1 = new ArrayList<>(Arrays.asList(names.split(",")));
//         System.out.println(namesList1);
//         ArrayList<Integer> agesList1 = Arrays.stream(ages.split(",")).map(Integer::valueOf).collect(Collectors.toCollection(ArrayList::new ));
//         List<Integer> agesList2 = Arrays.stream(ages.split(",")).map(Integer::valueOf).collect(Collectors.toList());
//         List<Integer> agesList3 = Arrays.stream(ages.split(",")).map(Integer::valueOf).toList();

//         System.out.println(agesList1);
//         System.out.println(agesList2);
//         System.out.println(agesList3);

//         namesList.sort(Comparator.comparing((String n) -> n.length()));
//         ArrayList<String> newnamesList= namesList.stream().sorted(Comparator.comparing((String n)->n.length()).reversed()).collect(Collectors.toCollection(ArrayList::new));
//         System.out.println(namesList);
//         System.out.println(newnamesList);

//         String newS = Arrays.stream(originalString.split("")).collect(Collectors.joining("-"));
//         System.out.println(newS);
        
//         sc.close();

//     }
// }

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CodeTest{
    public static void main(String[] args) {
        HashMap<String,Integer> maps = new HashMap<>(Map.of("a",2,"as",3,"wer",1234,"dvc",321));
        System.out.println(maps);
        System.out.println(maps.values().stream().mapToInt(Integer::intValue).sum());
        // maps.keySet().stream().
        for(Map.Entry<String,Integer> item: maps.entrySet()){
            System.out.println("key "+ item.getKey()+" value "+ item.getValue());

        }

        HashMap<String, Integer> sortbykey = maps.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(
            Entry -> Entry.getKey(),
            Entry -> Entry.getValue(),
            // value -> value,
            (oldValue,newValue) -> oldValue,
            HashMap::new  
        ));
        System.out.println(sortbykey);

        HashMap<String, Integer> sortbykeylength = maps.entrySet().stream().sorted(Comparator.comparing((Map.Entry<String, Integer> e) -> e.getKey().length())).collect(Collectors.toMap(
            Entry -> Entry.getKey(),
            Entry -> Entry.getValue(),
            // value -> value,
            (oldValue,newValue) -> oldValue,
            HashMap::new  
        ));
        System.out.println(sortbykeylength);
        

        // Sort by key
maps.entrySet()
    .stream()
    .sorted(Map.Entry.comparingByKey())
    .forEach(System.out::println);

System.out.println("--------------");

// Sort by key length
maps.entrySet()
    .stream()
    .sorted(Comparator.comparingInt(
        (Map.Entry<String, Integer> e) -> e.getKey().length()
    ))
    .forEach(System.out::println);

    }
}