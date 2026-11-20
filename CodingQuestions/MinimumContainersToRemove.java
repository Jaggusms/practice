import java.util.*;

public class MinimumContainersToRemove {

    public int minOperations(ArrayList<Integer> containers, int target) {

        int totalSum = 0;

        for (int num : containers) {
            totalSum += num;
        }

        int remainingSum = totalSum - target;

        if (remainingSum < 0) {
            return -1;
        }

        if (remainingSum == 0) {
            return containers.size();
        }

        int left = 0;
        int currentSum = 0;

        int maxLength = -1;

        for (int right = 0; right < containers.size(); right++) {

            currentSum += containers.get(right);

            while (currentSum > remainingSum) {

                currentSum -= containers.get(left);

                left++;
            }

            if (currentSum == remainingSum) {

                maxLength = Math.max(
                    maxLength,
                    right - left + 1
                );
            }
        }

        return maxLength == -1
                ? -1
                : containers.size() - maxLength;
    }
}