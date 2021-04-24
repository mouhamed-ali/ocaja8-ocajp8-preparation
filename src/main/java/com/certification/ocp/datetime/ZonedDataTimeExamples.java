package com.certification.ocp.datetime;

import java.time.*;

public class ZonedDataTimeExamples {

    public static void main(String[] args) {

        // OffsetTime is a simple class handling time across time zones
        OffsetTime offsetTime = OffsetTime.of(LocalTime.of(11,20), ZoneOffset.of("-1"))
                .plusHours(1).plusMinutes(10); // 12:30 in GMT-1 like in sweden

        OffsetTime offsetTime2 = offsetTime.withOffsetSameInstant(ZoneOffset.of("+01:00")); // Greenwich +1 like in tunis
        System.out.printf("Time in Tunis is : %s%n",offsetTime2);

        System.out.printf(" ---------------------------------- %n");

        OffsetDateTime offsetDateTime = OffsetDateTime.of(
                LocalDateTime.of(2020, Month.JANUARY, 15, 20, 30), ZoneOffset.ofHours(-2));
        OffsetDateTime offsetDateTime2 = OffsetDateTime.of(
                LocalDateTime.of(2020, Month.JANUARY, 16, 01, 30), ZoneOffset.ofHours(3));
        System.out.printf("Is date times equals : %s%n",offsetDateTime.isEqual(offsetDateTime2));

        System.out.printf(" ---------------------------------- %n");

        // ZonedDateTime indicates the a date time with a time zone, it the same as OffsetDateTime
        // OffsetDateTime adds an offset from UTC to the Instant
        // ZonesDateTime must be used when a region base time zone is needed

        ZoneId losAngeles = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(LocalDateTime.of(2018,02,20,15,0), losAngeles);
        System.out.printf("At this point of time Los Angeles offset is : %s%n",zonedDateTime1.getOffset());
        zonedDateTime1 = zonedDateTime1.withMonth(3).withDayOfMonth(15).withHour(12);  // at march 15, los angeles is in daylight savings time
        System.out.printf("At this point of time Los Angeles offset is : %s%n",zonedDateTime1.getOffset());

        System.out.printf(" ---------------------------------- %n");

        ZoneId paris = ZoneId.of("Europe/Paris");
        ZonedDateTime forwardDST = ZonedDateTime.of(LocalDateTime.of(2018,3,25,2,30), paris); // March 25, day light savings will be applied
        System.out.printf("%s%n",forwardDST); // program prints 03:30 because this hour does not exist on that day
        /*
        From oracle :
            For Overlaps, the general strategy is that if the local date-time falls in the middle of an Overlap, then the previous offset will be retained. If there is no previous offset,
            or the previous offset is invalid, then the earlier offset is used, typically "summer" time..
            Two additional methods, withEarlierOffsetAtOverlap() and withLaterOffsetAtOverlap(), help manage the case of an overlap.
         */
        ZonedDateTime backwardDST = ZonedDateTime.of(LocalDateTime.of(2018,10,28,2,30), paris); // October 28, day light savings will be removed
        System.out.printf("At the overlap : %s%n",backwardDST); // program prints 02:30 because this hour exists that day
        System.out.printf("After one hour : %s%n",backwardDST.plusHours(1));    // time zone will be correct here
        System.out.printf("Earlier offset at overlap \t: %s%n",backwardDST.withEarlierOffsetAtOverlap());
        // Returns a copy of this date-time changing the zone offset to the earlier of the two valid offsets at a local time-line overlap.
        System.out.printf("Later offset at overlap \t: %s%n",backwardDST.withLaterOffsetAtOverlap());
        // Returns a copy of this date-time changing the zone offset to the later of the two valid offsets at a local time-line overlap.

        // for Paris
        System.out.printf(" ---------------------------------- %n");
        System.out.printf("At the overlap : %s%n",forwardDST);
        System.out.printf("After one hour : %s%n",forwardDST.plusHours(1));
        System.out.printf("Minus one hour : %s%n",forwardDST.minusHours(1));
        System.out.printf("Earlier offset at overlap \t: %s%n",forwardDST.withEarlierOffsetAtOverlap());
        System.out.printf("Later offset at overlap \t: %s%n",forwardDST.withLaterOffsetAtOverlap());
    }
}
