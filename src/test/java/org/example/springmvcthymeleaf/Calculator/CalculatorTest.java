package org.example.springmvcthymeleaf.Calculator;

import org.example.springmvcthymeleaf.Calculator.example.CalculatorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testSum() {
        CalculatorCode calculatorCode = new CalculatorCode();
        int actual = calculatorCode.sum(2, 3);
        int expected = 5;

        Assertions.assertEquals(expected, actual);
    }
}