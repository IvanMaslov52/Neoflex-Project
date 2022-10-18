package com.example.demo;

import com.example.demo.Services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private CalculatorService calculatorService;
	@Test
	void testingCalculate(){
		Assertions.assertEquals(new BigDecimal("10000.00"), calculatorService.calculate(BigDecimal.valueOf(29300),10));
	}

	@Test
	void testingCalculateWithDates(){
		Assertions.assertEquals(new BigDecimal("5000.00"), calculatorService.calculate(BigDecimal.valueOf(29300), LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 16)));
	}

}
