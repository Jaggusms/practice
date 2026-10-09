import java.sql.SQLOutput;
import java.time.format.SignStyle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapsTest {

    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        students.put(1,"a");
        students.putAll(Map.of(1,"2", 2,"b")); // java 9+
        System.out.println(students);

        System.out.println(students.keySet());
        System.out.println(students.values());
        students.clear();
        System.out.println("the dictory now is empty "+ students);
        students.putAll(Map.of(1,"2", 2,"b")); // java 9+
        System.out.println(students.containsKey(2));
        System.out.println(students.containsValue(2));

        Map.Entry<Integer, String> singlePair = Map.entry(101, "abc");
        System.out.println("the singlePair now is "+ singlePair);
        System.out.println("the single of object calling "+singlePair.getKey());

        Object singlePair1 = Map.entry(101, "abc");
        System.out.println("the singlePair now is "+ singlePair1);
//        System.out.println("the single of object calling "+singlePair1.getValue());
        System.out.println("STUDENTS DICT SIZE IS " + students.size());
//        System.out.println("sigle entry set is size is "+ singlePair.size());
        System.out.println("is students dict is empty? :"+ students.isEmpty());
        System.out.println("i am deletting 2 th key: " + students.remove(2));

        System.out.print("check s actuvally delted or not: ");
        if (students.containsKey(2)){
            System.out.println("it is not deleted");
        }
        else{
            System.out.println("it is deleted");
        }
        students.putAll(Map.of(3,"c",4,"d",5,"e"));

        // Looping via for loop
        for (Map.Entry<Integer, String > pair : students.entrySet()){
            System.out.println("key:"+ pair.getKey()+" value :"+ pair.getValue());
        }
        students.put(1,"A");
        System.out.println("key 1 is updated..");
        // Looping via for loop
        var iter = students.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<Integer,String> pair = iter.next();
            System.out.println("key:"+ pair.getKey()+" value :"+ pair.getValue());
        }
        Iterator<Map.Entry<Integer, String>> iter1 = students.entrySet().iterator();
        while (iter1.hasNext()){
            Map.Entry<Integer,String> pair = iter1.next();
            System.out.println("key:"+ pair.getKey()+" value :"+ pair.getValue());
        }

        // via streams
        System.out.println("Loop via streams");
        students.forEach((k,v)-> System.out.println("key:"+ k+" value :"+ v));
        System.out.println();
        students.entrySet().forEach( pair-> System.out.println("key:"+ pair.getKey()+" value :"+ pair.getValue()));


        // reverse keys to values
        HashMap<String, Integer> reversekeys = new HashMap<>();
        students.forEach((k,v)-> reversekeys.put(v,k));
        System.out.println("Reversed dict is "+reversekeys);


        //getFrequnces
        String name = "";
        HashMap<Character, Integer>  frequncyMap = new HashMap<>();
        int size = 0;
        while (size<name.length()){
            char letter = name.charAt(size);
            frequncyMap.put(letter,frequncyMap.getOrDefault(letter,0)+1);
            size +=1;
        }
        System.out.println(frequncyMap);

        // find first unique charector..

        String word = "jagadeesh";
        HashMap< Character, Integer > countsMap =  new HashMap<>();
        for (char s: word.toCharArray()){
            countsMap.put(s, countsMap.getOrDefault(s,0)+1);
        }
        int didWeFoundFirstUniqueCHar=0;
        for (char s: word.toCharArray()){
            if (countsMap.get(s)==1) {
                System.out.println(s);
                didWeFoundFirstUniqueCHar=1;
                break;
            }
        }
        if (didWeFoundFirstUniqueCHar ==0) {
            System.out.println(-1);
        }
    }
}