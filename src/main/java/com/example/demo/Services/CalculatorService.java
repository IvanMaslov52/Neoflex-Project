package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalculatorService {
    @Autowired
    private Holiday holiday;

    public BigDecimal calculate(BigDecimal salary, Integer days){
        if(days<0) return BigDecimal.valueOf(-1);
        if (days==0) return BigDecimal.valueOf(0);
        return salary.divide(BigDecimal.valueOf(29.3), 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(days));
    }
    public BigDecimal calculate(BigDecimal salary, LocalDate startDate, LocalDate endDate){
        if (startDate.isEqual(endDate)) return BigDecimal.valueOf(-1);
        else if (startDate.isAfter(endDate)) return BigDecimal.valueOf(0);
        return calculate(salary, calculateWorkDays(startDate,endDate));
    }
    public Integer calculateWorkDays(LocalDate start, LocalDate end) {
        end = end.plusDays(1L);
        int sum = 0;
        long result = ChronoUnit.DAYS.between(start, end);
        sum += 2 * (result / 7);
        if (start.getDayOfWeek().getValue() == 7) sum++;
        else if (end.getDayOfWeek().getValue() == 7) sum++;
        else if (start.getDayOfWeek().getValue() > end.getDayOfWeek().getValue()) sum += 2;
        if (start.getMonth().getValue()==end.getMonth().getValue()){
            for (LocalDate date: holiday.getHolidays(start.getMonth().getValue(), start.getYear())) {
                if ((date.isAfter(start) && date.isBefore(end)) || date.isEqual(start)) sum++;
            }
        }else {
            for (LocalDate date: holiday.getHolidays(start.getMonth().getValue(), start.getYear())) {
                if (date.isAfter(start) || date.isEqual(start)) sum++;
            }
            for (LocalDate date: holiday.getHolidays(end.getMonth().getValue(), end.getYear())){
                if (date.isBefore(end)) sum++;
            }
        }
        return (int) result - sum;

    }

}
