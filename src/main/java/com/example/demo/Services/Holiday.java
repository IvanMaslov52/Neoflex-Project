package com.example.demo.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class Holiday {
    private enum Holidays {
        FIRST_WINTER_DAY(LocalDate.of(0, 1, 1)),
        SECOND_WINTER_DAY(LocalDate.of(0, 1, 2)),
        THIRD_WINTER_DAY(LocalDate.of(0, 1, 3)),
        FOURTH_WINTER_DAY(LocalDate.of(0, 1, 4)),
        FIFTH_WINTER_DAY(LocalDate.of(0, 1, 5)),
        SIXTH_WINTER_DAY(LocalDate.of(0, 1, 6)),
        SEVENTH_WINTER_DAY(LocalDate.of(0, 1, 7)),
        EIGHT_WINTER_DAY(LocalDate.of(0, 1, 8)),
        DEFENDERS_DAY(LocalDate.of(0, 2, 23)),
        WOMANS_DAY(LocalDate.of(0, 3, 8)),
        FIRST_MAY_DAY(LocalDate.of(0, 5, 1)),
        VICTORY_DAY(LocalDate.of(0, 5, 9)),
        RUSSIA_DAY(LocalDate.of(0, 6, 12)),
        UNITY_DAY(LocalDate.of(0, 11, 4));


        private final LocalDate localDate;

        Holidays(LocalDate localDate) {
            this.localDate = localDate;
        }
    }
    public Set<LocalDate> getHolidays(int month, int year) {
        Set<LocalDate> dates=new HashSet<>();
        for (Holidays holiday:Holidays.values()){
            LocalDate localDate=LocalDate.of(year, holiday.localDate.getMonth(), holiday.localDate.getDayOfMonth());
            if (localDate.getMonth().getValue()==month){
                while (localDate.getDayOfWeek().getValue()==6 || localDate.getDayOfWeek().getValue()==7 || dates.contains(localDate)){
                    localDate=localDate.plusDays(1);
                }
                dates.add(localDate);
            }
        }
        return dates;
    }
}
