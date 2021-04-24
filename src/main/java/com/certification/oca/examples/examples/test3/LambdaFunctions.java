package com.certification.oca.examples.examples.test3;

import java.util.function.Predicate;

public class LambdaFunctions {

    public static void main(String[] a){

        boolean b = false;
        go(x -> b==true);
        go(x -> 4 >= 6);

        final LambdaFunctions m1 = new LambdaFunctions();
        final LambdaFunctions m2 = new LambdaFunctions();
       // go(x -> m1 = m2);
    }

    static void go(Predicate<LambdaFunctions> u){

        LambdaFunctions u1 = new LambdaFunctions();
        if(u.test(u1)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }
}

