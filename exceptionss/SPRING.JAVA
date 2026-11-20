/* 
https://chatgpt.com/g/g-p-699c8a431a4c819199016f9f11204927-java/shared/c/6b00647e-4e6c-83ee-8e54-baee97480f69?owner_user_id=user-Je95gNBk0NNWliDe57zZlKrr

Yes! Exactly. Even though it is a checked exception, you don't need to use try-catch inside your controller if you use @RestControllerAdvice.


package com.example.demo.exception;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}



package com.example.demo.exception;

public class InvalidEmployeeException extends RuntimeException {

    public InvalidEmployeeException(String message) {
        super(message);
    }
}



package com.example.demo.exception;

public class EmployeeSystemError extends Error {

    public EmployeeSystemError(String message) {
        super(message);
    }
}



package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.exception.EmployeeSystemError;

@Service
public class EmployeeService {

    public String getEmployee(int id) throws EmployeeNotFoundException {

        // Checked Exception
        if (id == 0) {
            throw new EmployeeNotFoundException(
                    "Employee not found with ID: " + id);
        }

        // Runtime Exception
        if (id < 0) {
            throw new InvalidEmployeeException(
                    "Employee ID cannot be negative");
        }

        // Error - demonstration only
        if (id == 999) {
            throw new EmployeeSystemError(
                    "Critical employee system error");
        }

        return "Employee found successfully. ID: " + id;
    }
}








================================================

@Service
public class EmployeeService {

    public String getEmployee(int id) throws MyException {

        if (id == 0) {
            throw new MyException("Employee not found");
        }

        return "Employee found";
    }
}

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public String getEmployee(@PathVariable int id)
            throws MyException {

        return employeeService.getEmployee(id);
    }
}


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> handleException(MyException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
*/