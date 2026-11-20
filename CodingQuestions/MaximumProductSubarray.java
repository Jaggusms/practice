public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {

        int maxProduct = nums[0];
        int minProduct = nums[0];

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int current = nums[i];

            // Negative number swaps max and min roles
            if (current < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(current, maxProduct * current);

            minProduct = Math.min(current, minProduct * current);

            result = Math.max(result, maxProduct);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, -2, 4};

        System.out.println(maxProduct(nums));
    }
}