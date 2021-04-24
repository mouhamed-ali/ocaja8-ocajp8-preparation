package com.certification.oca.examples.advancedclassdesign.innerclass;

public class NonStaticInnerClass {

    class NonStaticInnerClassNested{
        //private static Object object;
        //private static final Object OBJECT_2 = new Object();
        //private static final Long LENGTH_3 = 0;
        // for static fields you can only define compile time constants which are Strings and primitive variables
        private static final String NAME = "NAME";
        private static final long LENGTH_2 = 0;
        private int length = 0;
    }

    void process(){
        // don't need a super class object as this is and object method
        NonStaticInnerClassNested instance = new NonStaticInnerClassNested();
    }

    public static void main(String[] args) {

        NonStaticInnerClass superInstance = new NonStaticInnerClass();
        NonStaticInnerClassNested instance = superInstance. new NonStaticInnerClassNested();
        // nested instance cannot exist without the super class
    }
}
