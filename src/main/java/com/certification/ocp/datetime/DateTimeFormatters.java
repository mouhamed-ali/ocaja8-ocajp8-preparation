package com.certification.ocp.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatters {

    public static void main(String[] args) {

        ZoneId tunis = ZoneId.of("Africa/Tunis"); // Case sensitive, if you use Africa/tunis you'll have a runtime exception
        // DateTimeFormatter uses these two method to convert data time objects
        // public static DateTimeFormatter ofPattern(String pattern)
        // public static DateTimeFormatter ofLocalizedDate(FormatStyle dateStyle)

        // data time provides methods to format date time objects
        // public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter)
        // public static LocalDateTime parse(CharSequence text)
        // public String format(DateTimeFormatter formatter)

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H'h'm 'on' dd MM y");
        LocalDateTime localDateTime = LocalDateTime.parse("8h15 on 05 07 2018", formatter);
        System.out.printf("The date time is : %s%n", localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime,tunis);
        System.out.printf("The zoned date time is : %s%n", zonedDateTime);

        String firstFormat = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.printf("Format 1 : The zoned date time is : %s%n", firstFormat);

        String secondFormat = zonedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.printf("Format 2 : The zoned date time is : %s%n", secondFormat);
        // Format Style enum contains 4 constants
        // FULL
        // LONG
        // MEDIUM
        //SHORT

        System.out.printf(" ---------------------------------- %n");
        System.out.printf("FULL format : %s%n", zonedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.printf("LONG format : %s%n", zonedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.printf("MEDIUM format : %s%n", zonedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.printf("SHORT format : %s%n", zonedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

    }
}
