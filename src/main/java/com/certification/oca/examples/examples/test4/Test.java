package com.certification.oca.examples.examples.test4;

import java.io.FileInputStream;
import java.io.IOException;


public class Test {

    final static Integer x1;
    static {
        x1 = 2;
    }
    final static Integer x2 = 1;

    static char date;

    public static void main(String[] args) {

        //LocalDate date1 = LocalDate.parse("1991-09-09");
        //LocalDate date2 = LocalDate.parse("1991-09-09");
        //System.out.println((date1==date2) + " " + date1.equals(date2) + " " + date1.isEqual(date2));


        //LocalDate date1 = LocalDate.of(1991,9,9);
        //LocalDate date2 = LocalDate.of(1991,9,9);
        //System.out.println((date1==date2) + " " + date1.equals(date2) + " " + date1.isEqual(date2));

        int[] names1 = {1,2,30,5};
        int[] names2 = {1,2,30};
        names2=names1;

        Person p = new Child();

        try{
            new FileInputStream("");
        }catch (IOException e){}
        catch (RuntimeException e){}

    }

    private static void m(String[] a, String... i) {

        System.out.println(i);
    }
}

abstract class Person{

    private String getName(){
        return "Person";
    }
}

class Child extends Person {

    public String getName(){
        return "Child";
    }
}