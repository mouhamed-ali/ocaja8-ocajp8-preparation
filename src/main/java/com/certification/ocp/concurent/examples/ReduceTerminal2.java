package com.certification.ocp.concurent.examples;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class ReduceTerminal2 {

    public static void main(String... args) {

        /*
         * Third reduce method signature : <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
         */
        BiFunction<Integer,Integer,Integer> accumulator = (a, b) -> {

            System.out.printf("Accumulator was called to accumulate %d and %d %n",a,b);
            return a + b;
        };
        // This will accumulate each part of the array with the identity : 1+10=11, 2+10=12, 3+10=13, 4+10=14, 6+10=16

        BinaryOperator<Integer> combiner = (a,b) -> {

            System.out.printf("Combiner was called to combine %d and %d which equals to %d %n",a,b,a-b);
            return a - b;
        };
        // This will combine the results from the last operation.

        Integer result = Arrays.asList(1,2,3,4,6)
                .parallelStream()
                .reduce(10,accumulator,combiner);
        System.out.printf("result : %s %n",result);
    }
}
