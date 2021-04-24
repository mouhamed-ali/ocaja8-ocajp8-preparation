package com.certification.oca.examples.advancedclassdesign.innerclass;

public class StaticInnerClass {

    static class StaticInnerClassNested{

        private static Object object;
        private static final String NAME = "NAME";
        private int length = 0;
        // you can define any type of fields in a static inner class
    }

    public static void main(String[] args) {

        // from outside the class
        StaticInnerClass.StaticInnerClassNested instance = new StaticInnerClass.StaticInnerClassNested();
        // or from inside the class
        StaticInnerClassNested instance2 = new StaticInnerClassNested();
    }
}
