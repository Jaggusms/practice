public class Calculator {

    public int sum(int a, int b) {
         System.out.println("Actual sum() method executed");
        return a + b;
    }

    public int subtract(int a, int b) {
        System.out.println("Actual subtract() method executed");
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        return a / b;
    }
}s