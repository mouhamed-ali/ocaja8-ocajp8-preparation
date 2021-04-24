package com.certification.ocp.datetime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TemporalUnitsExamples {

    public static void main(String[] args) {

        /*
            TemporalUnit interface represents units of time. Java api provides implementations like ChronoUnit
            Noticeable methods :
            <R extends Temporal> R addTo(R temporal, long amount)
            long between(Temporal temporalInclusive, Temporal temporalExclusive)
            boolean isDateBased()
            boolean isTimeBased()
         */
        LocalDateTime localDateTime = LocalDateTime.parse("2000-06-30T12:00")
                .plus(2, ChronoUnit.DECADES)    // 2020
                // plus, minus, with ... are inherited from Temporal interface. base interface of local date, local date time ...
                .plus(5, ChronoUnit.YEARS)      // 2025
                .plus(12, ChronoUnit.HOURS)
                .plus(30, ChronoUnit.SECONDS)
                .plus(1, ChronoUnit.MILLENNIA)  // 3025
                .plus(100, ChronoUnit.MILLIS)
                .plus(4700, ChronoUnit.MICROS);
        System.out.printf("%s%n",localDateTime);

        System.out.printf(" ---------------------------------- %n");

        LocalTime temporal1 = LocalTime.of(12,0);
        LocalTime temporal2 = ChronoUnit.MINUTES.addTo(temporal1, 30);
        System.out.printf("The amount of hours between temporal1 and temporal2 is %s%n",
                ChronoUnit.HOURS.between(temporal1,temporal2));
        System.out.printf("The amount of minutes between temporal1 and temporal2 is %s%n",
                ChronoUnit.MINUTES.between(temporal1,temporal2));
    }
}
