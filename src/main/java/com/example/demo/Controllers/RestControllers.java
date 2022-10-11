package com.example.demo.Controllers;

import com.example.demo.Services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class RestControllers {
    @Autowired
    private CalculatorService calculatorService;
    @GetMapping("/calculate")
    public ResponseEntity<BigDecimal> calculate(@RequestParam BigDecimal salary, @RequestParam(required = false, defaultValue = "0") Integer days, @RequestParam(required = false)String beginDate, @RequestParam(required = false)String endDate)
    {
        if (beginDate !=null && endDate!=null){
            LocalDate begin=LocalDate.parse(beginDate);
            LocalDate end=LocalDate.parse(endDate);
            return ResponseEntity.ok(calculatorService.calculate(salary, begin,end));
        }else if (beginDate !=null)  return ResponseEntity.ok(BigDecimal.valueOf(-1));
        else if (endDate!=null) return ResponseEntity.ok(BigDecimal.valueOf(-1));
        return ResponseEntity.ok(calculatorService.calculate(salary, days));
}


}
