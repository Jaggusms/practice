package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorWithoutOrder {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    // Test 1: Addition of positive numbers
    @Test
    void testSumPositiveNumbers() {
        assertEquals(30, calculator.sum(10, 20));
    }

    // Test 2: Addition of negative numbers
    @Test
    void testSumNegativeNumbers() {
        assertEquals(-10, calculator.sum(-5, -5));
    }

    // Test 3: Addition with zero
    @Test
    void testSumWithZero() {
        assertEquals(10, calculator.sum(10, 0));
    }

    // Test 4: Addition of positive and negative numbers
    @Test
    void testSumPositiveAndNegativeNumbers() {
        assertEquals(5, calculator.sum(10, -5));
    }

    // Test 5: Subtraction
    @Test
    void testSubtract() {
        assertEquals(5, calculator.subtract(10, 5));
    }

    // Test 6: Multiplication
    @Test
    void testMultiply() {
        assertEquals(50, calculator.multiply(10, 5));
    }

    // Test 7: Division
    @Test
    void testDivide() {
        assertEquals(5, calculator.divide(10, 2));
    }

    // Test 8: Division with negative numbers
    @Test
    void testDivideNegativeNumbers() {
        assertEquals(-5, calculator.divide(-10, 2));
    }

    // Test 9: Division by zero
    @Test
    void testDivideByZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(10, 0)
        );
    }

    // Test 10: Verify multiple operations together
    @Test
    void testMultipleOperations() {
        assertAll(
                () -> assertEquals(15, calculator.sum(10, 5)),
                () -> assertEquals(5, calculator.subtract(10, 5)),
                () -> assertEquals(50, calculator.multiply(10, 5)),
                () -> assertEquals(2, calculator.divide(10, 5))
        );
    }
}