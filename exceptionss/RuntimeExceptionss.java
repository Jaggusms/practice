
/*
There are two ways to create a custom exception:
1. Checked Exception – Extend Exception.
2. Unchecked Exception – Extend RuntimeException.

*/


class AgeException extends RuntimeException {

    AgeException(String message) {
        super(message);
    }
}

public class RuntimeExceptionss {

    public static void main(String[] args) {

        int age = 15;

        if (age < 18) {
            throw new AgeException("Age must be 18 or above");
        }

        System.out.println("Eligible to vote");
    }
}