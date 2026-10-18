import java.util.*;
public class MergeSortedArrays {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 3, 6, 8));
        List<Integer> result = new ArrayList<>();
        int i = 0; // Pointer for list1
        int j = 0; // Pointer for list2
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }
        // Add remaining elements from list1
        while (i < list1.size()) {
            result.add(list1.get(i));
            i++;
        }
        // Add remaining elements from list2
        while (j < list2.size()) {
            result.add(list2.get(j));
            j++;
        }
        System.out.println(result);
    }
}