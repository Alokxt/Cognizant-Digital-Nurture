
package com.example;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
 
@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
 
    @Mock
    private Calculator calculator;
 
    @Mock
    private CalculatorLogger logger;
 
    private CalculatorService calculatorService;
 
    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService(calculator, logger);
    }
 
    @Test
    public void testMockingAndStubbing() {
        when(calculator.add(2, 3)).thenReturn(5);
 
        int result = calculatorService.add(2, 3);
 
        assertEquals(5, result);
    }
 
    @Test
    public void testVerifyingInteractions() {
        when(calculator.add(2, 3)).thenReturn(5);
 
        calculatorService.add(2, 3);
 
        verify(calculator, times(1)).add(2, 3);
        verify(logger, times(1)).log("ADD", 2, 3, 5);
        verifyNoMoreInteractions(calculator, logger);
    }
 
    @Test
    public void testArgumentMatching() {
        when(calculator.add(anyInt(), anyInt())).thenReturn(100);
 
        int result = calculatorService.add(10, 20);
 
        assertEquals(100, result);
        verify(logger).log(eq("ADD"), anyInt(), anyInt(), eq(100));
    }
}