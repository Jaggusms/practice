
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;






public class test {
    public static void main(String[] args) {

        /* 
        2 sum
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(2,7,11,15));
        int target = 9;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int index=0; index< nums.size();index++){
            int current = nums.get(index);
            int remaining = Math.abs(current-target);

            if (map.containsKey(remaining)){
                System.out.println(map.get(remaining)+" "+index);
                break;
            }
            map.put(current, index);

        }
            */
        /*
        best time to but stock

        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(7,1,5,3,6,4));

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int index=0; index< nums.size();index++){
            min = Math.min(min, nums.get(index));
            int cuurentprofit = nums.get(index) - min;
            max = Math.max(max, cuurentprofit);

        }
        System.out.println(max);
                */


        /* can join all meetings 
        int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        Arrays.sort(
            intervals,(a,b)->a[0]-b[0]
        );
        int currentEnd =intervals[0][0];
        boolean canJOin = true;
        for (int index=1; index< intervals.length-1;index++){
            int currentStart = intervals[index][0];
            if (currentStart<currentEnd){
                canJOin =false;
                break;
            }
            else{
                currentEnd =  intervals[index][1];
            }
        }
        System.out.println(false);

        */

        /*  howmany call he can join
        int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        Arrays.sort(
            intervals,(a,b)->a[0]-b[0]
        );
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] meeting: intervals){
            int start = meeting[0];
            int end = meeting[1];

            if (!pq.isEmpty() && start>=pq.peek()){
                pq.poll();
            }
            pq.offer(end);
        }
        System.out.println(pq.size());
        */

        String s = "jagadeesh";
        LinkedHashMap<String,Integer> counter = Arrays.stream(s.split(""))
                                                .collect(Collectors.toMap(
                                                    c->c,
                                                    c->1,
                                                    (o,n)->o+n,
                                                    LinkedHashMap::new));
        System.out.println(counter);
        List<Map.Entry<String,Integer>> data = new ArrayList<>(counter.entrySet());
        data.sort((a,b)->b.getValue()-a.getValue());
        System.out.println(data);

        int rank =0;
        int max = Integer.MAX_VALUE;
        for (Map.Entry<String,Integer> entry:data) {
            
            int currentcounter = entry.getValue();
            if (currentcounter<max){
                rank++;
                max = currentcounter;
            }

            if (rank==2){
                System.out.println(entry.getKey());
                break;
            }
        }
    }   
}
