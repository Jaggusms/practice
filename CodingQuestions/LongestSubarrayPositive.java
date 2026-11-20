public class LongestSubarrayPositive {

    public int longestSubarray(int[] arr, int target) {

        int left = 0;
        int currentSum = 0;

        int maxLength = 0;

        for (int right = 0; right < arr.length; right++) {

            currentSum += arr[right];

            while (currentSum >= target) {

                maxLength = Math.max(
                    maxLength,
                    right - left + 1
                );

                currentSum -= arr[left];

                left++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        LongestSubarrayPositive obj =
                new LongestSubarrayPositive();

        int[] arr = {2, 3, 1, 4, 3};

        int target = 7;

        System.out.println(obj.longestSubarray(arr, target));
    }
}