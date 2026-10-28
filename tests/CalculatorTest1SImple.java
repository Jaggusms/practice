import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest1SImple {

    @Test
    void testSum() {
        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int result = calculator.sum(10, 20);

        // Assert
        assertEquals(30, result);
    }
}