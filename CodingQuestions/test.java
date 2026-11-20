
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String s = "}])";
        ArrayList<String> list = new ArrayList<>(Arrays.asList("}","]",")"));
        if (s.contains("}")){
            System.out.println("1");
        }
        if (list.contains("}")){
            System.out.println("1");
        }
    }
}
