package com.certification.ocp.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

public class LocalExamples {

    public static void main(String[] args) {
        // LocalDate, LocalTime, LocalDateTime represents date-time without a time zone
        LocalDate localDate = LocalDate.of(2018, Month.JANUARY,0007); // months starts from 1 and not 0; january == 1 and not 0 like in calendar
        // dayOfMonth is valid, you can declare this for example ; int a = 0000001; but it's not a decimal be careful it's octal. Literals with a leading zero are octal literals.
        System.out.printf("The local date is %s%n", localDate);

        System.out.printf("With month June \t: %s%n",localDate.withMonth(6)); // Most of date time api classes are immutable so the localDate will not be changed
        System.out.printf("With month July \t: %s%n", localDate.with(ChronoField.MONTH_OF_YEAR, 7));
        System.out.printf("Is a leap year \t\t: %s%n", localDate.isLeapYear());
        System.out.printf("Day of Month \t\t: %s%n", localDate.getDayOfMonth());
        System.out.printf("Last day of the Month \t\t: %s%n", localDate.with(TemporalAdjusters.lastDayOfMonth()));
        // with method can take a TemporalField or a TemporalAdjuster

        System.out.printf(" ---------------------------------- %n");

        LocalTime localTime = LocalTime.parse("20:30:40");
        System.out.printf("The local time is : %s%n",localTime);
        System.out.printf("With minute change \t: %s%n",localTime.with(ChronoField.MINUTE_OF_HOUR,10));
        System.out.printf("Is after now \t: %s%n",localTime.isAfter(LocalTime.now()));
        System.out.printf("Get seconds \t: %s%n",localTime.get(ChronoField.SECOND_OF_MINUTE));

        System.out.printf(" ---------------------------------- %n");

        LocalDateTime localDateTime = localDate.atTime(localTime); // or localTime.atDate(localDate)
        System.out.printf("The local date time is : %s%n",localDateTime);
        System.out.printf("The local date is %s%n", LocalDate.from(localDateTime));
        System.out.printf("The local time is : %s%n",LocalTime.from(localDateTime));
    }
}
