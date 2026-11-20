import java.util.Arrays;

public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int index = 1;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != nums[i - 1]) {

                nums[index] = nums[i];

                index++;
            }
        }

        return index;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 2, 2, 3, 4, 4};

        int k = removeDuplicates(nums);

        System.out.println("Unique count: " + k);

        System.out.println(
            "Unique elements: " +
            Arrays.toString(Arrays.copyOf(nums, k))
        );
    }
}