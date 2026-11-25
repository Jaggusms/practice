
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class longestvalidparenthesisSubstring {
    public static void main(String[] args) {
        String s = "()(){()[]{}";
        Stack<Integer> stack = new Stack<>();
        // HashMap<String, Integer> uniqueKeys =
        //             new HashMap<>(Map.of("(", 0, "{", 0, "[", 0));
        HashMap<Character, Integer> uniqueKeys =
                    new HashMap<>(Map.of('(', 0, '{', 0, '[', 0));

        stack.add(-1);
        int index= 0;
        int maxValue = 0;
        while(index< s.length()){
            
            // if (uniqueKeys.containsKey(String.valueOf(s.charAt(index)))){
            if (uniqueKeys.containsKey(s.charAt(index))){
                stack.push(index);
            }
            else{
                stack.pop();
                if (stack.empty()){
                    stack.push(index);
                }
                else{
                    maxValue = Math.max(maxValue, index-stack.peek());
                }

            }
            index++;
            System.out.println(stack);
        }
        System.out.println(maxValue);
        
    }
}
