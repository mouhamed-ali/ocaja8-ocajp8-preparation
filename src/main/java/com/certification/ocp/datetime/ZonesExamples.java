package com.certification.ocp.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;

public class ZonesExamples {

    public static void main(String[] args) {

        // A Zone id represents the id of a time zone, used to convert between instant and local date time
        ZoneId fixedOffset = ZoneId.of("UTC-06:00"); fixedOffset = ZoneId.of("UT-06:00"); fixedOffset = ZoneId.of("GMT-06:00");
        // the last three declarations are equals
        ZoneId geoRegion = ZoneId.of("America/Chicago"); // you can also use a geographical zone
        ZoneId.getAvailableZoneIds().forEach(System.out::println);

        System.out.printf(" ---------------------------------- %n");

        // ZoneOffset is a subclass of ZoneId; an instance of this class represents the amount of time that a time zone differs from UTC
        ZoneOffset zoneOffset = ZoneOffset.of("+6"); ZoneOffset.of("+06:00"); ZoneOffset.ofHours(6);
        // the three declarations are the same, the last one accepts an int as hours

        // zones offsets must starts with + or minus, if the offset is zero you have to represent is with the letter z and not 0 otherwise
        // you'll have a runtime exception
        ZoneId.of("Z"); // valid
        ZoneId.of("+0");// valid
        ZoneId.of("-0");// valid
        ZoneId.of("0"); // throws an exception
        ZoneId.of("z"); // throws an exception
    }
}
