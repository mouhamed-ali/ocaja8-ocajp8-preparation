package com.certification.oca.examples.examples.test;

public class Course {

    public void enroll(char c){
       System.out.println("char");
    }

    public void enroll(short c){
        System.out.println("short");
    }

    public void enroll(int c){
        System.out.println("int");
    }

    public void enroll(long c){
        System.out.println("long");
    }

    public void enroll(Byte c){
        System.out.println("Byte");
    }

    public void enroll(Character c){
        System.out.println("Character");
    }

    public void enroll(Short c){
        System.out.println("Short");
    }

    public void enroll(Integer c){
        System.out.println("Integer");
    }

    public void enroll(Long c){
        System.out.println("Long");
    }


    public void enroll(String c){
        System.out.println("String");
    }

    public void enroll(Object c){
        System.out.println("Object");
    }

    public static void main(String[] args){

        byte a = 5;
        new Course().enroll(a);
    }
}
