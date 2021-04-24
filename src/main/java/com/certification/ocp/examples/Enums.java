package com.certification.ocp.examples;

public class Enums {

    public static void main(String[] args){

        String female = GENDER.FEMALE.name();
        System.out.println(female);

        GENDER male = GENDER.valueOf("MALE");
        System.out.println(male);

        System.out.println(GENDER.MALE.ordinal() + "" + GENDER.FEMALE.ordinal());

        male = GENDER.valueOf("male");
        System.out.println(male);
    }


    static public class Test {}
    public static class TestA {}
    abstract public class TestB {}
    public abstract class TestC {}
    static abstract public class TestV {}
}

enum GENDER{
    MALE, FEMALE
}


