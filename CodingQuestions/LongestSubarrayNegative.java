public class LongestSubarrayNegative {

    public int longestSubarray(int[] arr, int target) {

        int n = arr.length;

        long prefixSum = 0;
        long minPrefixSum = 0;

        int maxLength = 0;

        for (int i = 0; i < n; i++) {

            prefixSum += arr[i];

            if (prefixSum >= target) {

                maxLength = i + 1;
            }

            if (prefixSum - minPrefixSum >= target) {

                maxLength = Math.max(
                    maxLength,
                    i + 1
                );
            }

            minPrefixSum = Math.min(
                minPrefixSum,
                prefixSum
            );
        }

        return maxLength;
    }

    public static void main(String[] args) {

        LongestSubarrayNegative obj =
                new LongestSubarrayNegative();

        int[] arr = {2, 3, 1, -2, 4, 3};

        int target = 7;

        System.out.println(obj.longestSubarray(arr, target));
    }
}