import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.plaf.TreeUI;

public class stringspractise{
    public static void main ( String[] args ) {
        // Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine (); //a,b
//        String[] inputs = input.split ( "," );
//        System.out.println ( Arrays.toString ( inputs ));

//        String s = "abc";
//        for(char ss: s.toCharArray ()){
//            System.out.println (ss );
//        }
        // String name = "madam";
        // System.out.println(name.length ()+" "+name.length ()/2);
        // System.out.println(name.length ()+" "+5.0/2);
        // if (((Object) (5.0 / 2)).getClass().getName() instanceof Double){  getname return string
        //     System.out.println("true");
        // }
        // if (((Object) (5.0 / 2)).getClass() instanceof Double){  
        //     System.out.println("true");
        // }

        // if (((Object) (5.0 / 2)) instanceof Double) {
        //     System.out.println("true");
        // }

        // boolean isItPalindrome=true;
        // for (int i=0; i<name.length ()/2;i++){
        //     if (name.charAt(i) != name.charAt(name.length()-1-i)){
        //         isItPalindrome=false;
        //         break;
        //     }
        //     // System.out.println(name.charAt(i));
        // }
        // System.out.println(isItPalindrome?1:0);
        
        // System.out.println("name@gmail.com".endsWith("com"));
        // String products = "apple, 12.5, in-stock";
        
        // System.out.println(Arrays.stream("apple, 12.5, in-stock".split(","))
        // .map(product -> product.strip())
        // .map(product -> String.valueOf(product.charAt(0)).toUpperCase()+product.substring(1))
        // .collect(Collectors.joining("|"))
        // );

        // String[] finalValues = Arrays.stream("apple, 12.5, in-stock".split(","))
        // .map(product -> product.strip())
        // .map(product -> String.valueOf(product.charAt(0)).toUpperCase()+product.substring(1))
        // .toArray(String[]::new);
        
        // System.out.println(String.join("|", finalValues));
        // for (String product: products.split(",")){

        //     product.strip().replace(String.valueOf(product.charAt(0)), 
        //     String.valueOf(product.charAt(0)).toUpperCase());

        // }

        // Input: strs = ["eat","tea","tan","ate","nat","bat"]
        // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        // String[] strs = {"eat","tea","tan","ate","nat","bat"};
        // HashMap<String, ArrayList<String>> result = new HashMap<>();
        // for(String str: strs){
        //     char[] sortedChars = str.toCharArray();
        //     Arrays.sort(sortedChars);
        //     String key = new String(sortedChars);
        //     ArrayList<String> value = result.getOrDefault(key, new ArrayList<>());
        //     value.add(str);
        //     result.put(key, value);
        // }
        // result.sort(Comparator.comparingInt(map -> map.getValue().size()));  won't work
        // result.sort(Comparator.comparingInt((Map.Entry<String, ArrayList<String>> map) -> map.getValue().size()));
        // won't work


        // List<ArrayList<String>> finalResult = new ArrayList<>(result.values());
        // finalResult.sort(Comparator.comparingInt((values)-> values.size()));
        // System.out.println(finalResult);
        
        // 2. Extract map entries into a List so we can sort them
        // List>> entryList = new ArrayList<>(result.entrySet());

        // // 3. Sort the list by the size of the ArrayList value (Ascending order)
        // entryList.sort(Comparator.comparingInt(entry -> entry.getValue().size()));

        // // (Optional) Sort in Descending order (largest groups first):
        // // entryList.sort(Comparator.comparingInt((Map.Entry<String, ArrayList<String>> entry) -> entry.getValue().size()).reversed());

        // // 4. Put the sorted entries back into a LinkedHashMap to preserve the order
        // Map<String, ArrayList<String>> sortedMap = new LinkedHashMap<>();
        // for (Map.Entry<String, ArrayList<String>> entry : entryList) {
        //     sortedMap.put(entry.getKey(), entry.getValue());
        // }

        // // 5. Print the sorted map
        // System.out.println(sortedMap);


        // String[] sortedStrs = Arrays.stream(strs).map(str->Arrays.sort(str.toCharArray()))
        // .map(array-> new String[array]).toArray(String[]:: new);

        // String[] sortedStrs = Arrays.stream(strs)
        // .map(str -> {
        //     char[] array = str.toCharArray();
        //     Arrays.sort(array);
        //     return new String(array);
        // })
        // .toArray(String[]::new);

        // HashMap<String,Integer> counter = Arrays.stream(strs).map(str -> {
        //     char[] array = str.toCharArray();
        //     Arrays.sort(array);
        //     return new String(array);
        // })
        // .collect(Collectors.toMap(
        //     str-> str,
        //     str -> 1,
        //     (o,n)-> o+n,
        //     HashMap::new
        // ));
        // System.out.println(counter);

        // Map<String, Long> counter = Arrays.stream(strs)
        // .map(str -> {
        //     char[] array = str.toCharArray();
        //     Arrays.sort(array);
        //     return new String(array);
        // })
        // .collect(Collectors.groupingBy(
        //         str -> str,
        //         Collectors.counting()
        // ));

        //  Map<String, List<String>> result = Arrays.stream(strs)
        //         .collect(Collectors.groupingBy(str -> {
        //             char[] chars = str.toCharArray();
        //             Arrays.sort(chars);
        //             return new String(chars);
        //         }));

        // HashMap<String, List<String>> result = Arrays.stream(strs)
        //         .collect(Collectors.groupingBy(
        //             str -> {
        //                 char[] chars = str.toCharArray();
        //                 Arrays.sort(chars);
        //                 return new String(chars);
        //             },
        //             HashMap::new,             // Forces the result to be a HashMap
        //             Collectors.toList()       // Explicitly collect into a List
        //         ));


        // Map<String, Set<String>> result = Arrays.stream(strs)
        //         .collect(Collectors.groupingBy(
        //             str -> {
        //                 char[] chars = str.toCharArray();
        //                 Arrays.sort(chars);
        //                 return new String(chars);
        //             },
        //             Collectors.toSet() // Collects into a Set of unique strings per group
        //         ));

        // String s = "abbcde";
        // System.out.println(s.indexOf("b"));
        // System.out.println(s.indexOf("b",s.indexOf("b")+1 ));

        // String s = "my name is jagadeesh, ag is 25 and 25M and pincode is 524101, and mobile is 8500060896";
        // ArrayList<Long> numbers =new ArrayList<>();

        // Matcher match = Pattern.compile("\\d+").matcher(s);
        // while (match.find()) {
        //     numbers.add(Long.valueOf(match.group()) );
        // }
        // System.out.println(numbers);

        // ArrayList<Long> numbers1 =new ArrayList<>();
        // Matcher match1 = Pattern.compile("\\b\\d+\\b").matcher(s);
        // while (match1.find()) {
        //     numbers1.add(Long.valueOf(match1.group()) );
        // }
        // System.out.println(numbers1);

        // String s1 = "my name is jagadeesh, ag is (25) and (25M) and pincode is (524101), and mobile is (8500060896)";
        // ArrayList<Long> numbers2 =new ArrayList<>();
        // Matcher match2 = Pattern.compile("\\((\\d+)\\)").matcher(s1);
        // while (match2.find()) {
        //     numbers2.add(Long.valueOf(match2.group(1)) );
        // }
        // System.out.println(numbers2);

    }
}