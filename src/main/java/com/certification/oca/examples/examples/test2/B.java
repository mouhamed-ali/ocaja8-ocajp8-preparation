package com.certification.oca.examples.examples.test2;


import com.certification.oca.examples.examples.test.A;

public class B extends A {

    public static void main(String... args){

        int a = 10;
        int b = 0;

        int result = a++ + a + a++ + ++a;
        int result2 = ++b + b;

        System.out.println(result);
        System.out.println(result2);

        System.out.println("###################");

        System.out.println(a);
        System.out.println(b);

        Long z = Long.valueOf(null);
        System.out.println(z);
    }
}
