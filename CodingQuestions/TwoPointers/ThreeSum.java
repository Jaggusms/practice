import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    // LeetCode #15 - 3Sum
    // Approach: Sorting + Two Pointers
    // Time Complexity: O(n²)
    // Auxiliary Space: O(1) excluding sorting and output

    public List<List<Integer>> threeSum(ArrayList<Integer> nums) {

        List<List<Integer>> result = new ArrayList<>();

        // Sort the input
        Collections.sort(nums);

        for (int i = 0; i < nums.size() - 2; i++) {

            // Skip duplicate elements for first number
            if (i > 0 && nums.get(i).equals(nums.get(i - 1))) {
                continue;
            }

            int left = i + 1;
            int right = nums.size() - 1;

            while (left < right) {

                int sum = nums.get(i) + nums.get(left) + nums.get(right);

                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums.get(i),
                            nums.get(left),
                            nums.get(right)
                    ));

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right &&
                            nums.get(left).equals(nums.get(left - 1))) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                            nums.get(right).equals(nums.get(right + 1))) {
                        right--;
                    }

                } else if (sum < 0) {

                    left++;

                } else {

                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        ThreeSum obj = new ThreeSum();

        ArrayList<Integer> nums = new ArrayList<>(
                Arrays.asList(-1, 0, 1, 2, -1, -4)
        );

        List<List<Integer>> result = obj.threeSum(nums);

        System.out.println(result);
    }
}