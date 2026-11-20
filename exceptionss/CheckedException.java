/*
There are two ways to create a custom exception:
1. Checked Exception – Extend Exception.
2. Unchecked Exception – Extend RuntimeException.

*/

// Custom exception
class AgeException extends Exception {

    AgeException(String message) {
        super(message);
    }
}

public class CheckedException {

    public static void main(String[] args) {

        int age = 15;

        try {
            if (age < 18) {
                throw new AgeException("Age must be 18 or above");
            }

            System.out.println("Eligible to vote");

        } catch (AgeException e) {
            System.out.println(e.getMessage());
        }
    }
}



// class MyException extends Exception {

//     MyException(String msg) {
//         super(msg);
//     }
// }

// public class Main {

//     static void display() throws MyException {
//         throw new MyException("Something went wrong");
//     }

//     public static void main(String[] args) {

//         try {
//             display();
//         } catch (MyException e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }


/*
belolow exception will rais the compailation error why it required throws or try catch


same code can works if we extend with runtime exception why it doen't required throws or try catch
 */
// class MyException extends Exception {

//     MyException(String msg) {
//         super(msg);
//     }
// }

// public class Main {

//     static void display() {
//         throw new MyException("Something went wrong");
//     }

//     public static void main(String[] args) {
//         display();
//     }
// }



