
import java.util.ArrayList;
import java.util.Arrays;

public class waterTrap {
    public static void main(String[] args) {
    // ArrayList<Integer> waterWalls = new ArrayList<>(Arrays.asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));
    ArrayList<Integer> waterWalls = new ArrayList<>(Arrays.asList(0, 1, 0, 0,0));
    int left =0;
    int right = waterWalls.size()-1;

    int leftMaxWallHeight =0;
    int rightMaxWallHeight = 0;

    int totalWaterTrapped = 0;

    while (left<right){
        if (waterWalls.get(left)< waterWalls.get(right)){
            if (waterWalls.get(left)>=leftMaxWallHeight){
                leftMaxWallHeight = waterWalls.get(left);
            }
            else{
                totalWaterTrapped += Math.abs(leftMaxWallHeight - waterWalls.get(left) );
            }
            left++;
        }
        else{
            if (waterWalls.get(right)>=rightMaxWallHeight){
                rightMaxWallHeight = waterWalls.get(right);
            }
            else{
                totalWaterTrapped += Math.abs(rightMaxWallHeight - waterWalls.get(right) );
            }
            right--;

        }
    
    }

    System.out.println(totalWaterTrapped);
    }
    
    
}
