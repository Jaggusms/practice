import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public  class CodeTest {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        ArrayList<ArrayList<Integer>> resultArray = new ArrayList<>();

        for (int allCOmbinations=0; allCOmbinations<arr.size()+1; allCOmbinations++){
            generateCombinations(arr, allCOmbinations, 0, new ArrayList<>(), resultArray);
        }
        resultArray.sort(Comparator.comparingInt(e->e.size()).reversed());
        System.out.println(resultArray);
        

        int sum = 0;
        for (ArrayList<Integer> combination: resultArray){
            int summation = combination.stream().mapToInt(Integer::intValue).sum();
            sum +=summation;
            System.out.println(combination+" "+ summation);

        }
        System.out.println(sum);

    }

    public static void  generateCombinations(
        ArrayList<Integer> inputArray,
        int sizeOfCombination,
        int start,
        ArrayList<Integer> cuurenArray,
        ArrayList<ArrayList<Integer>> resultArray
    ){

        if (cuurenArray.size()==sizeOfCombination){
            resultArray.add(new ArrayList<>(cuurenArray));
            return ;
        }
        for (int currentIndex =start; currentIndex<inputArray.size(); currentIndex++){
            cuurenArray.add(inputArray.get(currentIndex));
            generateCombinations(inputArray, sizeOfCombination, currentIndex+1, cuurenArray, resultArray);
            cuurenArray.remove(cuurenArray.size()-1);
        }

    }
}