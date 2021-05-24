package com.certification.ocp.generics.compare;

import lombok.*;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
@Data
public class Person implements Comparable<Person> {
    // if you don't use the Person parameter (like Person implements Comparable) you have to provide an implementation of the abstract method
    // public int compareTo(Object o) {}

    private String name;
    private Integer age;

    @Override
    public int compareTo(Person o) {
        // sort by name asc and age desc
        int result = name.compareTo(o.getName());
        return result != 0 ? result : o.age - age;
    }
    // you have to implement the equals method also so the comparison can be consistent and to avoid unexpected behavior

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("Daniel", 23),
                new Person("David", 53),
                new Person("Zhuang ", 12),
                new Person("Abel ", 18),
                new Person("Daniel", 38)
        );
        // when using tree set, if you don't implement the comparable (and even if the compareTo exists) you'll have an exception
        // java.lang.ClassCastException ; Person cannot be cast to java.lang.Comparable
        Set<Person> personsSet = new TreeSet<>(persons);
        personsSet.forEach(System.out::println);
        // be careful, the comparators in the tree set will not be only used to sort but in all other operations. for example, if you try to add an
        // existing element it will be ignored

        System.out.println("----------------------------------------");

        // using comparator; you can use a lambda instead of the anonymous class
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // sort by name asc and age desc
                int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : o2.age - o1.age;
            }
        });
        persons.forEach(System.out::println);

        System.out.println("----------------------------------------");
        Stream.of(1,5,3,2).sorted((i,j) -> i - j).forEach(i -> System.out.printf("%d , ", i));
        // if we start with the first parameter (i) the order will be asc otherwise it will be desc
        System.out.printf("%n ---------------------------------------- %n");
        Stream.of(1,5,3,2).sorted((i,j) -> j - i).forEach(i -> System.out.printf("%d , ", i));

        System.out.printf("%n ---------------------------------------- %n");
        Stream.of("A", "D", "B", "C").sorted((i,j) -> i.compareTo(j)).forEach(i -> System.out.printf("%s , ", i));
        System.out.printf("%n ---------------------------------------- %n");
        Stream.of("A", "D", "B", "C").sorted((i,j) -> j.compareTo(i)).forEach(i -> System.out.printf("%s , ", i));
    }
}
