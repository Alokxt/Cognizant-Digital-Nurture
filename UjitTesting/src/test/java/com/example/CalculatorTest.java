package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
 
import java.util.concurrent.TimeUnit;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    @Order(1)
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @Order(2)
    public void testSubtract() {
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    @Order(3)
    public void testDivideByZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0));
    }

    @Test
    @Order(4)
    public void testMultiply() {
        assertEquals(12, calculator.multiply(4, 3));
    }

    @Test
    @Order(5)
    public void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    @Order(6)
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testMultiplyPerformance() {
        for (int i = 0; i < 1_000_000; i++) {
            calculator.multiply(i, i);
        }
    }

    @Test
    @Order(7)
    @Tag("fast")
    public void testIsEvenTrue() {
        assertTrue(calculator.isEven(4));
    }
 
    @Test
    @Order(8)
    @Tag("slow")
    public void testIsEvenFalse() {
        assertFalse(calculator.isEven(3));
    }
 
    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @Order(9)
    @Tag("fast")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "0, 0, 0",
            "-1, 1, 0",
            "10, 20, 30"
    })
    public void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    

    
}
