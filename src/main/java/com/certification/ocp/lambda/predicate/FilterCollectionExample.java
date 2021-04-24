package com.certification.ocp.lambda.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterCollectionExample {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(-1, 2, -5, 7));
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 0;
            }
        });    // remove if takes a predicate of integer so we can pass an anonymous class
        list.forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        list = new ArrayList<>(Arrays.asList(-1, 2, -5, 7));
        list.removeIf(new FilterOnEvenNumbers());
        // remove if takes a predicate of integer so we can FilterOnEvenNumbers as it is a predicate
        list.forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        // we can do the same using lambda
        list = new ArrayList<>(Arrays.asList(-1, 2, -5, 7));
        list.removeIf(i -> i<0);
        list.forEach(System.out::println);
    }
}

class FilterOnEvenNumbers implements Predicate<Integer>{
    @Override
    public boolean test(Integer integer) {
        return integer < 0;
    }
}
