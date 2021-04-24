package com.certification.ocp.examples;

import java.util.stream.IntStream;

public class TestLists {

    public static void main(String[] a){

        IntStream.range(0, 10).forEach(i -> System.out.printf(", %d", i));
        System.out.println();
        IntStream.rangeClosed(0, 10).forEach(i -> System.out.printf(", %d", i));
        System.out.println();
        IntStream.iterate(0, i -> i + 2).limit(5).forEach(i -> System.out.printf(", %d", i));

    }
}
