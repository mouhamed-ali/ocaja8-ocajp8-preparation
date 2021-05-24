package com.certification.ocp.stream.examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class StreamMethodReference {

    public static void main(String[] args) {

        Stream<String> letters = getStream();

        // This is a reference to a static method (as valueOf is a static method in String that accepts an Object and return a String)
        // public static String valueOf(Object obj)
        letters.forEach(String::valueOf);

        // reference to an instance method of an arbitrary object, toUpperCase is an instance method from the String class
        // public boolean isEmpty()
        // difference from the last example is that the method here acts on the current object but in the last example it acts on the object
        // passed into parameter but there is not difference in term of method reference declaration
        letters = getStream();
        letters.filter(String::isEmpty)
                .forEach(System.out::println);  // reference to a static method

        letters = getStream();
        letters.sorted(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return new LengthSorter().sort(str1,str2);
            }
        });
        // doing the same using lambda
        letters = getStream();
        LengthSorter sorter = new LengthSorter();
        letters.sorted(sorter::sort) // same as letters.sorted((a,b) -> sorter.sort(a,b));
                .forEach(System.out::println);
        // reference to an instance method of a particular object

        // reference to a constructor
        Stream.generate(() -> new String()).limit(1).forEach(System.out::println);
        // same as
        Stream.generate(String::new).limit(1).forEach(System.out::println);

    }

    private static Stream<String> getStream() {
        return Arrays.asList("a", "b", "c", "d", "e", "f").stream();
    }

    public static class LengthSorter{
        public int sort(String str1, String str2){
            return str1.length() - str2.length();
        }
    }
}
