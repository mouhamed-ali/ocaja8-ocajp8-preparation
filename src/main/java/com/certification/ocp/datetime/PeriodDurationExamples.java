package com.certification.ocp.datetime;

import java.time.*;

public class PeriodDurationExamples {

    public static void main(String[] args) {

        // A Period of one day is conceptual day, maintaining local time and ignoring daylight saving changes
        // A Duration of one day is always exactly 24 hours
        ZoneId newYork = ZoneId.of("America/New_York");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2018, 3, 10, 12, 0),
                newYork); // on March 11, the daylight will be applied in new york

        System.out.printf("After one day, the period will be \t: %s%n", zonedDateTime.plus(Period.ofDays(1)));
        System.out.printf("After one day, the duration will be : %s%n", zonedDateTime.plus(Duration.ofDays(1)));
        // as you can see the duration is exactly 24 hours (considering the daylight added hour)
        // the period returns the local date time after one day without taking into account the daylight added hour
    }
}
