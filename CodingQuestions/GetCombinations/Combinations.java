import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static void backtrack(int[] numbers, int size, int start,
                                 List<Integer> current, List<List<Integer>> result) {

        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            current.add(numbers[i]);
            backtrack(numbers, size, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static List<List<Integer>> getCombinations(int[] numbers, int size) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(numbers, size, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getCombinations(new int[]{1, 2, 3, 4}, 2));
    }
}



