package com.certification.oca.examples.examples.test3;

import java.io.FileNotFoundException;

interface A {

    void get();
}


public class GererExceptions {

    public static void main(String[] arg) throws Exception {

        System.out.println(new GererExceptions().get());

    }


    public int get() {

        try {

            throw new RuntimeException();
        }catch (Exception e){

            return 99;
        }finally {

            throw new NullPointerException();
        }
    }
}

abstract class Ping{

    public static final String MAX_VALUE = "MAX_VALUE";
    abstract Ping getString() throws Exception;

}

class Animal extends GererExceptions{


    public Animal getString()  {
        return null;
    }
}

interface Mixte extends Parent, Child{}

interface Parent{

    static void calcParent() {
        System.out.println("Parent");
    }
}

interface Child {

    static void calcParent() {
        System.out.println("Child");
    }
}

class Utils {

    public Utils() {
        super();
    }

    public Utils( int a) {
    }

    //@Override
    String getString() throws NumberFormatException, FileNotFoundException{
        return "";
    }
}
