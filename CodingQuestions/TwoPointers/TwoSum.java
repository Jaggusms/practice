import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // LeetCode #1 - Two Sum
    // Approach: HashMap
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public ArrayList<Integer> twoSum(ArrayList<Integer> nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.size(); i++) {

            int complement = target - nums.get(i);

            if (map.containsKey(complement)) {

                ArrayList<Integer> result = new ArrayList<>();

                result.add(map.get(complement));
                result.add(i);

                return result;
            }

            map.put(nums.get(i), i);
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {

        TwoSum obj = new TwoSum();

        ArrayList<Integer> nums =
                new ArrayList<>(Arrays.asList(2, 7, 11, 15));

        int target = 9;

        ArrayList<Integer> result = obj.twoSum(nums, target);

        System.out.println(result);
    }
}