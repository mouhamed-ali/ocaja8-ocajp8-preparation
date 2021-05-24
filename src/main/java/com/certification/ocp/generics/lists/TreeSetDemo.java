package com.certification.ocp.generics.lists;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {

    @AllArgsConstructor
    static class Person {
        String name;
    }

    public static void main(String[] args) {

        try {
            // an exception will be thrown at the tree set creation due to the comparable interface
            TreeSet<Person> personsSet = new TreeSet<>(Arrays.asList(new Person("abel")));
        } catch (ClassCastException e) {
            System.err.printf("Person class does not implement the comparable interface. %s%n", e.toString());
        }

        Comparator<Integer> comparator = (i, j) -> i - j;
        TreeSet<Integer> treeSet = new TreeSet<>(comparator); // tree set can accepts comparator as argument
        // tree set can accept a collection of elements also as a constructor, the comparator will be the natural comparator of the list of elements
        // Methods from Set interface
        treeSet.add(250);
        treeSet.add(35);
        treeSet.add(99);
        treeSet.add(79);
        treeSet.add(12);
        treeSet.add(-9);
        treeSet.remove(2);  // this will remove the Object 2 from tree set and not Object at index 2 there is no remove(int index) in the tree set

        print(treeSet);
        System.out.printf("%n%-8s%4s%n", "ceiling", treeSet.ceiling(99)); // ceiling : plafond
        // returns the least element greater than or equal to the specified element ; null if there no element
        System.out.printf("%-8s%4s%n", "higher", treeSet.higher(99));  // strictly greater

        System.out.printf("%-8s%4s%n", "floor", treeSet.floor(79));   // floor : étage
        // returns the greatest element less than or equal to the specified element ; null if there no element
        System.out.printf("%-8s%4s%n", "lower", treeSet.lower(79));  // strictly less

        print(treeSet.headSet(35)); // portion of set whose elements are less than the specified element
        print(treeSet.tailSet(79)); // portion of set whose elements are greater than the specified element

        print(treeSet.descendingSet()); // reverse the order
    }

    private static void print(Collection<Integer> treeSet) {
        System.out.printf("%n---------------------------------------- %n");
        treeSet.forEach(
                s -> System.out.printf("%4s , ", s)
        );
    }
}
