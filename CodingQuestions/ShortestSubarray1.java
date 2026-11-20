import java.util.Arrays;

public class ShortestSubarray1 {

    public int minSubArrayLen(int threshold, int[] requests) {

        int left = 0;
        int currentSum = 0;

        int minLength = Integer.MAX_VALUE;
        int leftWindow = 0;
        int rightWindow = 0;

        for (int right = 0; right < requests.length; right++) {

            currentSum += requests[right];

            while (currentSum >= threshold) {
                leftWindow = left;
                rightWindow = right;
                System.out.println(Arrays.toString(Arrays.copyOfRange(requests, leftWindow, rightWindow+1)));
                minLength = Math.min(
                    minLength,
                    right - left + 1
                );

                currentSum -= requests[left];

                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {

        ShortestSubarray1 obj = new ShortestSubarray1();

        int[] requests = {2, 3, 1, 4, 3};

        int threshold = 7;

        System.out.println(
            obj.minSubArrayLen(threshold, requests)
        );
    }
}