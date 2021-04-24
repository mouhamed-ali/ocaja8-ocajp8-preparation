package com.certification.ocp.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectors {

    public static void main(String[] args) {

        List<String> list = getAlphabeticStream().collect(Collectors.toList()); // this returns an ArrayList
        System.out.println(list);
        Set<String> set = getAlphabeticStream().collect(Collectors.toSet());    // this returns a hashset
        System.out.println(set);
        System.out.printf(" ---------------------------------- %n");

        // the toCollection method of Collectors class returns a collector that accumulates input elements into a new Collection
        // toCollection accepts a supplier as a first parameter
        ArrayDeque<String> arrayDeque = getAlphabeticStream().collect(Collectors.toCollection(ArrayDeque::new));
        System.out.printf("The array deque \t: %s%n",arrayDeque);
        LinkedList<String> linkedList = getAlphabeticStream().collect(Collectors.toCollection(LinkedList::new));
        System.out.printf("The linked list \t: %s%n",linkedList);
        System.out.printf(" ---------------------------------- %n");

        Map<Integer,String> mapPersons = getPersonStream().collect(Collectors.toMap(Person::getAge,Person::getName));
        System.out.printf("The persons map \t: %s%n",mapPersons);
        Map<Gender, List<Person>> mapGender = getPersonStream().collect(Collectors.groupingBy(Person::getGender));
        System.out.printf("The map genders \t: %s%n",mapGender);
        // the grouping by has another method which accepts a reduction operation as second parameter
        // second parameter is of type Collector
        Map<Gender, Double> mapAverageAges = getPersonStream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.averagingDouble(Person::getAge)));
        System.out.printf("The map average ages \t: %s%n",mapAverageAges);
        System.out.printf(" ---------------------------------- %n");

        Map<Boolean, List<Person>> booleanPersons = getPersonStream().collect(Collectors.partitioningBy(p -> p.getAge() > 20));
        System.out.printf("The map boolean persons \t: %s%n",booleanPersons);
        // as for grouping by, the partitioningBy method has a method that accepts a reduce operation as a second parameter
        // second parameter is of type Collector
        Map<Boolean, Double> booleanAges = getPersonStream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 20, Collectors.averagingDouble(Person::getAge)));
        System.out.printf("The map boolean ages \t: %s%n",booleanAges);
        System.out.printf(" ---------------------------------- %n");

        // the toSet method of collectors is a collector ( reduce method)
        Map<Boolean, Set<Person>> booleanSetPersons = getPersonStream().collect(Collectors.partitioningBy(p -> p.getAge() > 55, Collectors.toSet()));
        System.out.printf("The map boolean set persons \t: %s%n",booleanSetPersons);
    }

    private static Stream<String> getAlphabeticStream() {
        return Stream.of("A", "C", "B", "A", "C");
    }

    private static Stream<Person> getPersonStream() {
        return Stream.of(
                new Person(45,"David", Gender.MALE),
                new Person(75,"Laura", Gender.FEMALE),
                new Person(15,"Grace", Gender.FEMALE),
                new Person(30, "Steve", Gender.MALE)
        );
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Person {
        private int age;
        private String name;
        private Gender gender;
    }

    public enum Gender {MALE, FEMALE}
}
