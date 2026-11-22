/*
There are two ways to create a custom exception:
1. Checked Exception – Extend Exception.
2. Unchecked Exception – Extend RuntimeException.



4. Important: Difference between throw and throws
These are frequently asked in Java interviews.

throw: Used to explicitly throw an exception.
throw new AgeException("Invalid age");

throws: Used in a method declaration to indicate that the method may throw an exception.
void checkAge(int age) throws AgeException {

    if (age < 18) {
        throw new AgeException("Invalid age");
    }
}

Remember:
- throw → Actually throws the exception.
- throws → Declares the possibility of an exception.

For checked exceptions, Java allows you to do either of these:
1. Handle the exception using try-catch.
2. Declare the exception using throws and let the caller handle it.

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


/*
A good custom exception generally provides these constructors:


| Constructor | Purpose |
|---|---|
| `InvalidAgeException()` | Creates exception without a custom message |
| `InvalidAgeException(String message)` | Provides a meaningful error message |
| `InvalidAgeException(Throwable cause)` | Preserves the original exception |
| `InvalidAgeException(String message, Throwable cause)` | Provides both message and original exception |

Let's understand when to use each one.

Constructor 1:
throw new InvalidAgeException();

Use when you don't need to provide a specific message or original cause.

Constructor 2:
throw new InvalidAgeException("Age must be greater than 18");

Use when you want to communicate a business validation failure.

Constructor 3:
catch (SQLException e) {
    throw new InvalidAgeException(e);
}

Use when you want to wrap an existing exception without providing a separate message.

Constructor 4:
catch (SQLException e) {
    throw new InvalidAgeException("Unable to validate user age", e);
}

Use when you want to provide your own message while preserving the original exception.
Important interview point
These constructors are not mandatory in every custom exception.

You should provide constructors according to how the exception will be used.

public class InvalidAgeException extends Exception {

    public InvalidAgeException() {
        super();
    }

    public InvalidAgeException(String message) {
        super(message);
    }

    public InvalidAgeException(Throwable cause) {
        super(cause);
    }

    public InvalidAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}



              java.io.Serializable
                  (Interface)
                       |
          +------------+-------------+
          |            |             |
       Object       Exception     Collection
       classes         |             |
          |         Throwable     +--+----------+
          |            |          |             |
      Employee      IOException  List          Set
      Student       RuntimeException
      Product       SQLException
                       |
                 Custom Exceptions
 


                 
                     java.lang.Object
                           |
                           |
                    java.lang.Throwable
                           |
              +------------+------------+
              |                         |
           Error                    Exception
                                         |
                              +----------+----------+
                              |                     |
                       IOException          RuntimeException
                              |
                         SQLException
                 */


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



