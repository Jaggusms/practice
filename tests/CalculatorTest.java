package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // 1. Test normal addition
    @Test
    @Order(1)
    void testSumPositiveNumbers() {
        assertEquals(30, calculator.sum(10, 20));
    }

    // 2. Test addition with negative numbers
    @Test
    @Order(2)
    void testSumNegativeNumbers() {
        assertEquals(-10, calculator.sum(-5, -5));
    }

    // 3. Test addition with zero
    @Test
    @Order(3)
    void testSumWithZero() {
        assertEquals(10, calculator.sum(10, 0));
    }

    // 4. Test addition of positive and negative numbers
    @Test
    @Order(4)
    void testSumPositiveAndNegativeNumbers() {
        assertEquals(5, calculator.sum(10, -5));
    }

    // 5. Test subtraction
    @Test
    @Order(5)
    void testSubtract() {
        assertEquals(5, calculator.subtract(10, 5));
    }

    // 6. Test multiplication
    @Test
    @Order(6)
    void testMultiply() {
        assertEquals(50, calculator.multiply(10, 5));
    }

    // 7. Test normal division
    @Test
    @Order(7)
    void testDivide() {
        assertEquals(5, calculator.divide(10, 2));
    }

    // 8. Test division by zero
    @Test
    @Order(8)
    void testDivideByZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(10, 0)
        );
    }

    // 9. Test multiple conditions together
    @Test
    @Order(9)
    void testMultipleOperations() {
        assertAll(
                () -> assertEquals(15, calculator.sum(10, 5)),
                () -> assertEquals(5, calculator.subtract(10, 5)),
                () -> assertEquals(50, calculator.multiply(10, 5)),
                () -> assertEquals(2, calculator.divide(10, 5))
        );
    }
}