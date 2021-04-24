package com.certification.ocp.datetime;

import java.time.Instant;
import java.time.temporal.ChronoField;

public class InstantExample {

    public static void main(String[] args){

        // an instant object represents an instantaneous point on the time-line
        Instant now = Instant.now();
        System.out.printf("The instant now is : %s%n",now); // date time in GMT

        System.out.printf("The epic time line is : %s%n",Instant.ofEpochSecond(0)); // 01 january 1970

        System.out.printf("The next day of epic time line is : %s%n",Instant.ofEpochSecond(60*60*24));

        System.out.printf("Now is after epic date : %s%n",now.isAfter(Instant.ofEpochSecond(60*60*24*365)));

        now.with(ChronoField.YEAR, 2020);
        // instant object represents an instantaneous point on the time-line
        // it is not meant to used with any time unit other than second, so the last statement throws UnsupportedTemporalTypeException
    }
}
