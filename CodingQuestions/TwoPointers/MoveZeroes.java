import java.util.Arrays;

public class MoveZeroes {

    public static void moveZeroes(int[] nums) {

        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {

                nums[index] = nums[i];

                index++;
            }
        }

        // Fill remaining positions with zero
        while (index < nums.length) {
            nums[index] = 0;
            index++;
        }
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};

        moveZeroes(nums);

        System.out.println(Arrays.toString(nums));
    }
}


// import java.util.*;

// public class MoveZeroes {

//     public static void moveZeroes(ArrayList<Integer> list) {

//         int index = 0;

//         // Move non-zero elements to the front
//         for (int i = 0; i < list.size(); i++) {

//             if (list.get(i) != 0) {
//                 list.set(index, list.get(i));
//                 index++;
//             }
//         }

//         // Fill remaining positions with zero
//         while (index < list.size()) {
//             list.set(index, 0);
//             index++;
//         }
//     }

//     public static void main(String[] args) {

//         ArrayList<Integer> list =
//                 new ArrayList<>(Arrays.asList(0, 1, 0, 3, 12));

//         moveZeroes(list);

//         System.out.println(list);
//     }
// }