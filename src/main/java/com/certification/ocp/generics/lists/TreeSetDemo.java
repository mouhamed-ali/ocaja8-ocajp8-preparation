package com.certification.ocp.generics.lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {

        Comparator<Integer> comparator = (i,j) -> i - j;
        TreeSet<Integer> treeSet = new TreeSet<>(comparator); // tree set can accepts comparator as argument
        // tree set can accept a collection of elements also as a constructor, the comparator will be the natural comparator of the list of elements

        // Methods from Set interface
        treeSet.add(250); treeSet.add(35); treeSet.add(99); treeSet.add(79); treeSet.add(12); treeSet.add(-9);
        treeSet.remove(2);  // this will remove the Object 2 from tree set and not Object at index 2 there is no remove(int index) in the tree set

        print(treeSet);
        System.out.printf("%4s%n",treeSet.ceiling(99)); // ceiling : plafond
        // returns the least element greater than or equal to the specified element ; null if there no element
        System.out.printf("%4s%n",treeSet.higher(99));  // strictly greater

        System.out.printf("%4s%n",treeSet.floor(79));   // floor : étage
        // returns the greatest element less than or equal to the specified element ; null if there no element
        System.out.printf("%4s%n",treeSet.lower(79));  // strictly less

        print(treeSet.headSet(35)); // portion of set whose elements are less than the specified element
        print(treeSet.tailSet(79)); // portion of set whose elements are greater than the specified element

        print(treeSet.descendingSet()); // reverse the order
    }

    private static void print(Collection<Integer> treeSet) {
        System.out.printf("\t---------------------------------------- %n");
        treeSet.forEach(
                s -> System.out.printf("%4s , ", s)
        );
        System.out.printf("%n\t---------------------------------------- %n");
    }
}
