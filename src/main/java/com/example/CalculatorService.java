package com.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public interface CalculatorService {
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);
}

@Service
class SimpleCalculatorService implements CalculatorService {

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }
}

@Service
class ScientificCalculatorService implements CalculatorService {

    @Override
    public double add(double a, double b) {
        return Math.fma(a, 1, b); // Fused multiply-add for precision
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}

@Service
class HistoryService {
    private final List<String> history = new ArrayList<>();

    public void logCalculation(String operation, double a, double b, double result) {
        history.add(operation + ": " + a + ", " + b + " = " + result);
    }

    public List<String> getHistory() {
        return history;
    }
}

@RestController
@RequestMapping("/calculator")
class CalculatorController {

    private final CalculatorService calculatorService;
    private final HistoryService historyService;

    public CalculatorController(@Qualifier("simpleCalculatorService") CalculatorService calculatorService, HistoryService historyService) {
        this.calculatorService = calculatorService;
        this.historyService = historyService;
    }

    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.add(a, b);
        historyService.logCalculation("Addition", a, b, result);
        return result;
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return historyService.getHistory();
    }
}

@SpringBootTest
class CalculatorServiceTest {

    private SimpleCalculatorService calculatorService;

    @Test
    public void testAddition() {
        assertEquals(5.0, calculatorService.add(2, 3));
    }

    @Test
    public void testSubtraction() {
        assertEquals(1.0, calculatorService.subtract(5, 4));
    }

    @Test
    public void testMultiplication() {
        assertEquals(12.0, calculatorService.multiply(3, 4));
    }

    @Test
    public void testDivision() {
        assertEquals(2.0, calculatorService.divide(10, 5));
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(10, 0));
    }
}