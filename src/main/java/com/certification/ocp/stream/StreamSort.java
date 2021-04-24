package com.certification.ocp.stream;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class StreamSort {

    public static void main(String[] args) {

        // Java Stream interface provides two methods to sort a stream
        // Stream<T> sorted(); uses the natural order sort of elements
        Stream<String> stringStream = Stream.of("B", "C", "A");
        String str = stringStream.sorted().reduce(String::concat).get();
        System.out.println(str);

        // Stream<T> sorted(Comparator<? super T> comparator);
        Stream<Integer> integerStream = Stream.of(8,1,7);
        integerStream.sorted((i,j) -> i.compareTo(j)).forEach(System.out::println);

        // primitive stream interfaces does not have an overloaded methods. example with IntStream, we have only :
        // IntStream sorted();

        Stream<Person> personStream = Stream.of(new Person("aa"), new Person("bb"));

        personStream.sorted((i,j) -> i.compareTo(j)).forEach(System.out::println);
        // this will work even if the Person does not implement Comparable

        personStream.sorted().forEach(System.out::println);
        // This will generate a runtime exception because Person does not implement Comparable and the sorted will try
        // to cast the Person to a Comparable and generate a class cast exception
    }

    @AllArgsConstructor
    @ToString
    public static class Person {
        private String name;
        public int compareTo(Person o) {
            return name.compareTo(o.name);
        }
    }
}
