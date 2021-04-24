package com.certification.ocp.maps;

import java.util.Hashtable;
import java.util.Map;

public class HashTableDemo {

    public static void main(String... args){

        Map<Person,Integer> persons = new Hashtable<>();

        persons.put(new HashTableDemo.Person("Paul"), 23);
        persons.put(new HashTableDemo.Person("Paul"), 99);
        //persons.put(null, 99);    // throws NullPointerException

        persons.entrySet().forEach(entry -> System.out.printf("Key : %s , value %d %n", entry.getKey(), entry.getValue()));

        // HashTable is a legacy class which is used to insert key/values pairs. In which you can store non null keys
        // it implements a table and it is thread safe. all methods uses synchronized which impacts performance
        // the keys are different here as we didn't implements the equals and hashcode in Person
    }

    public static class Person{
        String name;
        public Person(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person name is " + name;
        }
    }
}
