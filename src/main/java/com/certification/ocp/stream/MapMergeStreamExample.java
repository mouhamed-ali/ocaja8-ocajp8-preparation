package com.certification.ocp.stream;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapMergeStreamExample {

    public static void main(String[] args){

        Stream<String> s = Stream.of("speak", "bark", "meow", "growl");
        BinaryOperator<String> merge = (a, b) -> a;
        Map<Integer, String> map = s.collect(Collectors.toMap(String::length, k  -> k, merge));
        map.forEach((key,value) -> System.out.printf("key : %d , value : %-5s%n", key, value));

        System.out.println(" --------------------------------------");

        merge = (a, b) -> b;
        map = Stream.of("speak", "bark", "meow", "growl").collect(Collectors.toMap(String::length, k  -> k, merge));
        map.forEach((key,value) -> System.out.printf("key : %d , value : %-5s%n", key, value));

        assert true : 3;
    }
}
