package com.certification.ocp.concurent.examples;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class ReduceTerminal {

    public static void main(String... args) {

       /*
        * First reduce method signature : Optional<T> reduce(BinaryOperator <T> accumulator)
        */
        BinaryOperator<Integer> sumBinary = (a,b) -> a + b;
        Optional<Integer> result = Arrays.asList(1,2,3,4)
                .stream()
                .reduce(sumBinary);
        System.out.printf("result : %s %n", result);

        IntBinaryOperator intSumBinary = (a,b) -> a + b;
        OptionalInt intResult = IntStream.range(1,4)
                .reduce(intSumBinary);
        System.out.printf("intResult : %s (The range is not closed in this example) %n",intResult);

        /*
         * Second reduce method signature : T reduce(T identity, BinaryOperator <T> accumulator)
         */
        Integer newResult = IntStream.rangeClosed(1,4)
                .reduce(10,intSumBinary);
        System.out.printf("newRsult : %s (We are using the identity this time) %n",newResult);

    }
}
