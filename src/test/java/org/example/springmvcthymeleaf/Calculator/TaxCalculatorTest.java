package org.example.springmvcthymeleaf.Calculator;

import org.example.springmvcthymeleaf.Calculator.example.TaxCalculator;
import org.example.springmvcthymeleaf.Calculator.example.TaxResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {

    @Mock
    TaxResolver mock;

    @Test
    void testGetPriceWithTax(){
        when(mock.getCurrentTax()).thenReturn(0.5);
        TaxCalculator taxCalculator = new TaxCalculator(mock);

        Assertions.assertEquals(150.0,taxCalculator.getPriceWithTax(100.0),0.000009);
    }
}