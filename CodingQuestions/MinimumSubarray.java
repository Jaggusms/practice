// import java.util.ArrayList;
// import java.util.Arrays;

// @FunctionalInterface 
// /**
//  * InnerminWindowtargetsum
//  */
// public interface InnerminWindowtargetsum {

//     public void run();
// }

// public class minWindowtargetsum {
//     public static void main(String[] args) {
//         ArrayList<Integer> requestList = new ArrayList<>(Arrays.asList(2, 3, -1, 2, 4, 3));
//         // ArrayList<Integer> a =requestList;
//         // System.out.println(a);
//         // ArrayList<Integer> requestList = new ArrayList<>(Arrays.asList(1,1,1,1,1,1,1));
//         // System.out.println(requestList);
//         int targeSum =7;
//         int currentSum = 0;
//         // ArrayList<Integer> minwindow = new ArrayList<>();
//         int minWindowSize = requestList.size()+1;
//         int maxWindowSize = 0;
//         int left =0;
//         for (int right =0;right<requestList.size();right++){
//             currentSum +=requestList.get(right);

//             while (currentSum>=targeSum){
//                 minWindowSize = Math.min(right-left+1,minWindowSize);

//                 maxWindowSize = Math.max(right-left+1,maxWindowSize);
//                 currentSum -=requestList.get(left);
//                 left++;
//             }
//         }
//         System.out.println((minWindowSize==requestList.size()+1?0:minWindowSize));
//         System.out.println((maxWindowSize==0?0:maxWindowSize));


//         // //Lambda practise
//         // InnerminWindowtargetsum abc = ()-> System.out.println("lambda executing");
//         // abc.run();
//         // System.out.println(1);
//     }
// }


// import java.util.ArrayList;
// import java.util.Arrays;

// public class MinWindowTargetSum {

//     public static void main(String[] args) {

//         ArrayList<Integer> requestList =
//                 new ArrayList<>(Arrays.asList(-2,1,-3,4,-1,2,1,-5,4));
//                 // new ArrayList<>(Arrays.asList(2, 3, -1, 2, 4, 3));

//         int targetSum = 0;
//         int currentSum = 0;

//         int minWindowSize = Integer.MAX_VALUE;
//         int maxWindowSize = 0;

//         int minStart = -1, minEnd = -1;
//         int maxStart = -1, maxEnd = -1;

//         int left = 0;

//         for (int right = 0; right < requestList.size(); right++) {

//             currentSum += requestList.get(right);

//             while (currentSum == targetSum) {

//                 int windowSize = right - left + 1;

//                 // Minimum window
//                 if (windowSize < minWindowSize) {
//                     minWindowSize = windowSize;
//                     minStart = left;
//                     minEnd = right;
//                 }

//                 // Maximum window
//                 if (windowSize > maxWindowSize) {
//                     maxWindowSize = windowSize;
//                     maxStart = left;
//                     maxEnd = right;
//                 }
//                 if (requestList.get(left)>0){
//                     currentSum -= requestList.get(left);
//                 }
//                 else
//                     {
//                         currentSum += requestList.get(left);
//                     }
                
//                 left++;
//             }
//         }

//         System.out.println("Minimum Window Size: " +
//                 (minStart == -1 ? 0 : minWindowSize));

//         System.out.println("Minimum Window: " +
//                 (minStart == -1 ? "[]" :
//                         requestList.subList(minStart, minEnd + 1)));

//         System.out.println("Maximum Window Size: " + maxWindowSize);

//         System.out.println("Maximum Window: " +
//                 (maxStart == -1 ? "[]" :
//                         requestList.subList(maxStart, maxEnd + 1)));
//     }
// }






import java.util.*;

public class MinimumSubarray {

    public static void main(String[] args) {

        int[] nums = {2, -1, 2, 4, -2};
        int threshold = 5;

        int n = nums.length;

        long[] prefixSum = new long[n + 1];

        // Step 1: Calculate prefix sums
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();

        int minLength = Integer.MAX_VALUE;

        // Step 2: Process prefix sums
        for (int i = 0; i <= n; i++) {

            // Find valid subarrays
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

        System.out.println(
                minLength == Integer.MAX_VALUE ? 0 : minLength
        );
    }
}