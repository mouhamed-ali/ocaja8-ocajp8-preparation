package com.certification.ocp.generics.lists;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String[] args) {

        // Elements of a tree map are ordered using the natural ordering of their key, or by a comparator provided at creation time
        // depending on which constructor is used
        // tree map constructors accepts no argument, SortedMap, Map or a comparator

        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(9, "Nine"); hashMap.put(32,"Thirty-two"); hashMap.put(5, "Five"); hashMap.put(73, "Seventy-three"); hashMap.put(3,"Three"); hashMap.put(10,"Ten");
        print(hashMap);

        TreeMap<Integer, String> treeMap = new TreeMap<>(hashMap);
        System.out.printf("The tree comparator is '%s' because we did not specify one so we will use the natural comparator of keys (of type Integer)%n", treeMap.comparator());
        print(treeMap);

        treeMap.replace(3, "THREE");    // replaces the key 3 with the new value
        treeMap.replace(5, "FIVE", "five"); // replaces the key 5 if its current value is FIVE
        treeMap.remove(32); // removes the key 32
        treeMap.remove(73, "SEVENTY-THREE");    // removes 73 only if its current value is SEVENTY-THREE
        print(treeMap);

        System.out.printf("%nTree set contains key 3 : %s and contains the value NINE : %s%n", treeMap.containsKey(3), treeMap.containsValue("NINE"));

        // entry set ; key set and values
        treeMap.entrySet().forEach((entry)-> System.out.printf("%d -> %s , ", entry.getKey() , entry.getValue()));  // a set of Map.Entry
        System.out.println();
        treeMap.keySet().forEach((key)-> System.out.printf("%d , ", key));  // a set of integer
        System.out.println();
        treeMap.values().forEach((value)-> System.out.printf("%s , ", value));  // a collection of string

        System.out.printf("%n\t---------------------------------------- %n");

        print(treeMap.ceilingEntry(5)); // shows 5 entry
        print(treeMap.higherEntry(5)); // strictly greater shows 9 entry

        print(treeMap.floorEntry(73)); // shows 73 entry
        print(treeMap.lowerEntry(73)); // strictly lower shows 10 entry

        print(treeMap.descendingMap());
    }

    private static void print(Map.Entry<Integer, String> entry) {
        System.out.println(entry.getKey() + " > " + entry.getValue());
    }

    private static void print(Map<? extends Number, ? extends CharSequence> map) {
        System.out.printf("\t---------------------------------------- %n");
        map.forEach(
                (k,v) -> System.out.printf("Key : %2s , Value:  %-9s %n", k,v)
        );
        System.out.printf("\t---------------------------------------- %n");
    }
}
