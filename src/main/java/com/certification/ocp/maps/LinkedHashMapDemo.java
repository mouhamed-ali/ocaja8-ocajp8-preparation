package com.certification.ocp.maps;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    public static void main(String... args){

        Map<String, Integer> persons = new LinkedHashMap<>();

        persons.put("CLARK", 99);
        persons.put("SMITH", 75);
        persons.put("KING", 115);

        persons.entrySet().forEach(entry -> System.out.printf("Key : %-8s , value %d %n", entry.getKey(), entry.getValue()));

        // LinkedHashMap extends a HashMap
        // the difference is that the linked hash map preserves the order of insertion of the keys
    }
}
