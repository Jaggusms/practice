import java.util.*;

public class KClosestNumbers {

    // K Closest Numbers to Target
    // Approach: Max Heap
    // Time Complexity: O(n log k)
    // Space Complexity: O(k)

    public ArrayList<Integer> findKClosest(
            ArrayList<Integer> nums, int target, int k) {

        // Max Heap based on absolute difference
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(
                        Math.abs(b - target),
                        Math.abs(a - target)
                )
        );

        for (int num : nums) {

            pq.add(num);

            // Maintain only K closest elements
            if (pq.size() > k) {
                pq.poll();
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }

    public static void main(String[] args) {

        KClosestNumbers obj = new KClosestNumbers();

        ArrayList<Integer> nums = new ArrayList<>(
                Arrays.asList(6, 12, 20, 10, 14, 7)
        );

        int target = 9;
        int k = 3;

        ArrayList<Integer> result =
                obj.findKClosest(nums, target, k);

        System.out.println(result);
    }
}