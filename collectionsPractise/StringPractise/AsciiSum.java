public class AsciiSum {
    public static void main(String[] args) {
        String str = "abc";
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            // Java automatically promotes the char to an int when adding
            sum += str.charAt(i);
        }

        System.out.println("Sum of ASCII values: " + sum);
        // Output: Sum of ASCII values: 294
    }
}



//char ch = 'A';
//
//// Method 1: Explicit casting (Most common & explicit)
//int asciiValue1 = (int) ch;
//
//// Method 2: Implicit casting (Java automatically promotes char to int)
//int asciiValue2 = ch;
//
//System.out.println("The ASCII value of " + ch + " is: " + asciiValue1);
//// Output: The ASCII value of A is: 65





//int asciiNum = 66;
//
//// Explicitly cast the integer to a character
//char ch = (char) asciiNum;
//
//System.out.println("The character for ASCII value " + asciiNum + " is: " + ch);
//// Output: The character for ASCII value 66 is: B

class AsciiSumStream {
    public static void main(String[] args) {
        String str = "abc";

        int sum = str.chars().sum();

        System.out.println("Sum of ASCII values: " + sum);
        // Output: Sum of ASCII values: 294
    }
}