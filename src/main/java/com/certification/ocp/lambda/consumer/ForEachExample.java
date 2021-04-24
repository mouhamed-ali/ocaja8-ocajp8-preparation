package com.certification.ocp.lambda.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ForEachExample {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("j","a","v","a"));
        list.forEach(s -> {
            s = s.toUpperCase();
            System.out.print(s);
        });
        System.out.printf("%n ------------ %n");
        // the forEach method here is inherited from the Iterable interface and it is deterministic which means letters will shown
        // in theirs order in the list

        Stream<String> letters = Stream.of("j","a","v","a");
        letters.forEach(s -> {
            s = s.toUpperCase();
            System.out.print(s);
        });
        // here the foreach method is inherited from the Stream interface and it is non deterministic which means that elements are treated
        // randomly and it is not guaranteed to have the same order as the input order of elements so here the program can show something different
        // from java too

        System.out.printf("%n ************************************** %n");
        list.forEach(System.out::print);
        System.out.println();
        letters = Stream.of("j","a","v","a");
        letters.forEach(System.out::print);
    }
}
