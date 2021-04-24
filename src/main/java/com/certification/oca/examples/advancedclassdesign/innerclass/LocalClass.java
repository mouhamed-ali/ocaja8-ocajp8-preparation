package com.certification.oca.examples.advancedclassdesign.innerclass;

public class LocalClass {

    public static void main(String[] args) {

        final String str;
        str = "This is a string";
        int length = 0;
        int length1 = 1;
        class LocalClassNested{

            // the class scope is this method and as the non static inner class only constants are permitted as static fields
            // local classes can access final or effectively final variables only length1 and str in or case

            @Override
            public String toString() {
                return length + " " + str;
            }
        }
        length1 = 2;
        LocalClassNested localClassNested = new LocalClassNested();
    }
}
