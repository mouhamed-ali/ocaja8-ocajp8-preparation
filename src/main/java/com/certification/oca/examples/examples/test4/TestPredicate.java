package com.certification.oca.examples.examples.test4;

import java.util.function.Predicate;

class TestPredicate {

    public static final void main(String[] args){

        int[][] a = new int[1][2];
        System.out.println(a.length);

        a[0] = new int[5];

        Predicate<Integer> myCustomTrueNumber1 = number -> number > 32 ;
        Predicate<Integer> myCustomTrueNumber2 = number -> number == 32 ;
        Predicate<Integer> myCustomTrueNumber3 = number -> number < 32 ;
        Predicate<Integer> myCustomTrueNumber4 = number -> true ;

        isTrueNumber(myCustomTrueNumber1);
        isTrueNumber(myCustomTrueNumber2);
        isTrueNumber(myCustomTrueNumber3);
        isTrueNumber(myCustomTrueNumber4);
    }

    static void isTrueNumber(Predicate<Integer> integerPredicate){

        if(integerPredicate.test(32)){
            System.out.println("True Number");
        }else {
            System.out.println("Not a true Number");
        }

        switch ("str"){

            case "a" : {
                System.out.println();
                break;
            }
            case "b": {
                break;
            }
        }
    }
}


class TestIF{}
