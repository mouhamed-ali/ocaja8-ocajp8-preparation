package com.certification.oca.examples.advancedclassdesign;

public class LambdaExample {

    public static void main(String[] args) {

        Comparison comp1 = (i,j) -> i<j;
        Comparison comp2 = (Integer i, Integer j) -> i>j;

        // The line below does not compile
        // the parameters of a lambda expression must be exactly the same as the type of the interface methods
        //Comparison comp3 = (int i, int j) -> i > j;
    }
}

interface Comparison{
    boolean compare(Integer a, Integer b);
}
