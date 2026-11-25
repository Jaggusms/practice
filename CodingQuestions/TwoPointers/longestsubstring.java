
import java.util.HashSet;

public class longestsubstring {
    public static void main(String[] args) {
        String s = "swpqweh";
        
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        int left = 0;
        int right = 0;
        while(right<s.length()){

            char ch = s.charAt(right);

            while(set.contains(ch)){
                
                set.remove(s.charAt(left));
                System.out.println(set);
                left++;
            }
            set.add(ch);
            max = Math.max(max, right-left+1);
            right++;
            System.out.println(set);
        }

        System.out.println(max);
    }
}
