package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

class CalculatorTest {

    // 1. NORMAL EXECUTION
    @Test
    void testNormalExecution() {

        Calculator calculator = new Calculator();

        int result = calculator.sum(10, 20);

        assertEquals(30, result);
    }


    // 2. MOCKING + STUBBING
    @Test
    void testMockingAndStubbing() {

        Calculator calculator = mock(Calculator.class);

        // Configure the mock's response
        when(calculator.sum(10, 20)).thenReturn(100);

        // Call the mocked method
        int result = calculator.sum(10, 20);

        assertEquals(100, result);

        // Verify the method was called
        verify(calculator).sum(10, 20);
    }


    // 3. MOCK WITHOUT STUBBING
    @Test
    void testMockWithoutStubbing() {

        Calculator calculator = mock(Calculator.class);

        int result = calculator.sum(10, 20);

        // Default int return value for an unstubbed mock method
        assertEquals(0, result);
    }


    // 4. SPY + STUBBING
    @Test
    void testSpyAndStubbing() {

        Calculator calculator = spy(new Calculator());

        // The real sum() method executes during this setup
        when(calculator.sum(10, 20)).thenReturn(100);

        // The configured response is returned on this call
        int result = calculator.sum(10, 20);

        assertEquals(100, result);
    }


    // 5. SPY + doReturn()
    @Test
    void testSpyUsingDoReturn() {

        Calculator calculator = spy(new Calculator());

        // Does not invoke the real sum() method during setup
        doReturn(100).when(calculator).sum(10, 20);

        int result = calculator.sum(10, 20);

        assertEquals(100, result);
    }


    // 6. SPY WITHOUT STUBBING
    @Test
    void testSpyWithoutStubbing() {

        Calculator calculator = spy(new Calculator());

        int result = calculator.sum(10, 20);

        // The real implementation executes
        assertEquals(30, result);
    }
}