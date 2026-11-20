import java.util.*;

public class ShortestSubarray {

    public int shortestSubarray(int[] requests, int threshold) {

        int n = requests.length;

        long[] prefixSum = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + requests[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();

        int minLength = n + 1;

        for (int i = 0; i <= n; i++) {

            // Check whether current prefix sum
            // satisfies the threshold
            while (!deque.isEmpty() &&
                   prefixSum[i] - prefixSum[deque.peekFirst()] >= threshold) {

                minLength = Math.min(
                    minLength,
                    i - deque.pollFirst()
                );
            }

            // Maintain increasing prefix sums
            while (!deque.isEmpty() &&
                   prefixSum[i] <= prefixSum[deque.peekLast()]) {

                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return minLength == n + 1 ? 0 : minLength;
    }

    public static void main(String[] args) {

        ShortestSubarray obj = new ShortestSubarray();

        int[] requests = {2, 3, 1, -2, 4, 3};

        int threshold = 7;

        System.out.println(
            obj.shortestSubarray(requests, threshold)
        );
    }
}



