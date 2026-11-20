package swap;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RotateList {
    public static void main(String[] args) {
        Random random = new Random();
        // System.out.println(random.nextInt(0,100));
        ArrayList<Integer> numbers = IntStream.range(0, 10)
                                    .map(n -> random.nextInt(0,100))
                                    .boxed()
                                    .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> numbers1 = new ArrayList<>(numbers);
        System.out.println(numbers);
        RotateList obj = new RotateList();
        
        obj.rotateLeft(numbers,2);
        obj.rotateRight(numbers1, 3);
        System.out.println(numbers);
        System.out.println(numbers1);

    }
    public  void  swap(ArrayList<Integer> list,int start, int end){
        while (start<end) {
            int temp = list.get(start);
            list.set(start,list.get(end));
            list.set(end,temp);
            start++;
            end--;
            
        }
    }
    public void rotateLeft(ArrayList<Integer> list, int times){
        int size = list.size();
        swap(list,0,times-1);
        swap(list,times,size-1);
        swap(list,0, size-1);

    }
    public void rotateRight(ArrayList<Integer> list, int times){
        int size = list.size();
        swap(list,0, size-1);
        swap(list,0,times-1);
        swap(list,times,size-1);
    
    }
}
