import java.util.*;

public class TrappingRainWater {

    // LeetCode #42 - Trapping Rain Water
    // Approach: Two Pointers
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public int trap(ArrayList<Integer> height) {

        int left = 0;
        int right = height.size() - 1;

        int leftMax = 0;
        int rightMax = 0;

        int totalWater = 0;

        while (left < right) {

            if (height.get(left) < height.get(right)) {

                if (height.get(left) >= leftMax) {

                    leftMax = height.get(left);

                } else {

                    totalWater += leftMax - height.get(left);
                }

                left++;

            } else {

                if (height.get(right) >= rightMax) {

                    rightMax = height.get(right);

                } else {

                    totalWater += rightMax - height.get(right);
                }

                right--;
            }
        }

        return totalWater;
    }

    public static void main(String[] args) {

        TrappingRainWater obj = new TrappingRainWater();

        ArrayList<Integer> height = new ArrayList<>(
                Arrays.asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
        );

        int result = obj.trap(height);

        System.out.println("Trapped Water: " + result);
    }
}