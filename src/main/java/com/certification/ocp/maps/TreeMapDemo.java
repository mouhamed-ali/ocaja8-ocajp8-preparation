package com.certification.ocp.maps;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String... args){

        Map<Person, Integer> persons = new TreeMap<>();

        persons.put(new TreeMapDemo.Person("Paul"), 99);
        persons.put(new TreeMapDemo.Person("Fabrice"), 75);
        persons.put(new TreeMapDemo.Person("Xio"), 115);

        persons.entrySet().forEach(entry -> System.out.printf("Key : %-8s , value %d %n", entry.getKey(), entry.getValue()));

        // TreeMapDemo implements Comparable and NavigableMap which provides a lot of methods to manipulate ordered maps
        // keys will be ordered in theirs natural order ( in our case the person that has a little size name at first to the biggest size)

        System.out.println("---------------------------------------------------------------------");
        NavigableMap<Person, Integer> navigablePersons = (NavigableMap<Person, Integer>) persons;
        navigablePersons.descendingKeySet().forEach(entry -> System.out.printf("entry : %-8s%n", entry));
    }

    public static class Person implements Comparable<Person>{
        String name;
        public Person(String name){
            this.name = name;
        }
        @Override
        public String toString() {
            return "Person name is " + name;
        }
        @Override
        public int compareTo(Person o) {
            if(o == null) return -1;
            return this.name.length() - o.name.length();
        }
    }
}
