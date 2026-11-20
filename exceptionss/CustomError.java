class MyCustomError extends Error {

    MyCustomError(String message) {
        super(message);
    }
}

public class CustomError {

    public static void main(String[] args) {

        throw new MyCustomError("Something seriously went wrong");
    }
}


// o\p Exception in thread "main" MyCustomError:
// Something seriously went wrong
// Notice that the output says Exception in thread, but our class extends Error.

// This is just the JVM's standard uncaught throwable message format.