package com.example;

public class CalculatorService {
    private final Calculator calculator;
    private final CalculatorLogger logger;
 
    public CalculatorService(Calculator calculator, CalculatorLogger logger) {
        this.calculator = calculator;
        this.logger = logger;
    }
 
    public int add(int a, int b) {
        int result = calculator.add(a, b);
        logger.log("ADD", a, b, result);
        return result;
    }
 
    public int subtract(int a, int b) {
        int result = calculator.subtract(a, b);
        logger.log("SUBTRACT", a, b, result);
        return result;
    }
}
