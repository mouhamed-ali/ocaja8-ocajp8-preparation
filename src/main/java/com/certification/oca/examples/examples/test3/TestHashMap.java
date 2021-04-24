package com.certification.oca.examples.examples.test3;

import java.util.HashMap;

public class TestHashMap {

    public static void main(String[] a){

        HashMap<Person,String> map = new HashMap<>();

        Person p1 = new Person(1,"ONE");
        Person p2 = new Person(1,"TWO");
        Person p3 = new Person(1,"THREE");

        map.put(p1,"un");
        map.put(p3,"trois");
        map.put(p2,"deux");

        System.out.println("------------------------------------------------------");

        System.out.println(map.get(p3));
        System.out.println(map.get(p2));
        System.out.println(map.get(p1));
    }
}
