import javax.security.auth.callback.CallbackHandler;
import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StringPractise {
    public static void main(String[] args) {
        String name = "    123  ,  jagadeesh ";
        System.out.println("substring practise "+name.substring(0,name.length()));
        // Output: substring practise     123  ,  jagadeesh

        if (!name.contains("jag")){
            System.out.println("yes jag present in name");
        }
        else{
            System.out.println("it is not there");
            // Output: it is not there
        }
        System.out.println(name.strip());
        // Output: 123  ,  jagadeesh

        System.out.println(Arrays.asList(name.split("    ")) );
        // Output: [    123  ,  jagadeesh ]

        System.out.println(Arrays.asList(name.split(",")) );
        // Output: [    123  ,   jagadeesh ]

        System.out.println(Arrays.toString(name.split(",")) );
        // Output: [    123  ,   jagadeesh ]

        System.out.println("the type of the name is "+ name.getClass().getName());
        // Output: the type of the name is java.lang.String

        if (name instanceof String){
            System.out.println("This is string");
            // Output: This is string
        }
        System.out.println(String.join("-", name.split(",")));
        // Output:     123  -  jagadeesh

        String inputValues = "1,2,3,4,5,6";
        System.out.println(Arrays.asList(inputValues.split(",")).getClass() );
        // Output: class java.util.Arrays$ArrayList

        ArrayList values =new ArrayList(Arrays.asList(inputValues.split(",")));
        System.out.println(values);
        // Output: [1, 2, 3, 4, 5, 6]

//        String name1 = new Scanner(System.in).nextLine();
//        String newName = "";
//        for (Character chr: name1.toCharArray()){
//            newName = chr+newName;
//        }
//        if (name1.equals(newName)){
//            System.out.println("This is a palidrome");
//        }
//
//        String name11 = new Scanner(System.in).nextLine();
//
//        boolean palindrome = true;
//
//        for (int i = 0; i < name11.length() / 2; i++) {
//            if (name11.charAt(i) != name11.charAt(name11.length() - 1 - i)) {
//                palindrome = false;
//                break;
//            }
//        }
//
//        if (palindrome) {
//            System.out.println("This is a palindrome");
//        }

        char i = '1';
        char b = '1';
        if (i==b){
            System.out.println("hi");
            // Output: hi
        }
        String ii = "1";
        char bb = '1';
        if (ii.equals(bb)){
            System.out.println("hi");
            // (No output because String and char types do not match)
        }

        System.out.println(new StringBuffer("madam").reverse().toString());
        // Output: madam

//        String a = "hi this is jagadeesh and welcome to my world.";
//
//        String[] words = a.split(" ");
//        for (String word: words){
//            if (word.length()%2==0){
//                System.out.println(word);
//            }
//        }

        String  a = "GeekGeeks";
        int index= 4;
        String subStringToReplace = "for";
        System.out.println(a.substring(0,index+1)+subStringToReplace+a.substring(index, a.length()));
        // Output: GeekforGeeks


        // swaping..
        String newstringResvers = "";
        for (int letter=0; letter< a.length(); letter +=2){
            String subString = a.substring(letter,Math.min(letter+2,a.length()));
//            newstringResvers += new StringBuffer(subString).reverse().toString();
            newstringResvers += reverse(subString);
        }
        System.out.println(a);
        // Output: GeekGeeks

        System.out.println(newstringResvers);
        // Output: eGkeGeeks

        int[] values1 = {1,2,3,4};
        System.out.println(Arrays.toString(values1));
        // Output: [1, 2, 3, 4]

        String xx= "";
        for (int x: values1){
            xx += String.valueOf(x);
        }
        System.out.println(Arrays.toString(xx.toCharArray()));
        // Output: ['1', '2', '3', '4']
    }
    public static String reverse(String s){
        StringBuilder reversed = new StringBuilder();
        for (char ch: s.toCharArray()){
            reversed.insert(0, ch);
        }
        return reversed.toString();

    }


}