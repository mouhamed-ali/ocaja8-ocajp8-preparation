package com.certification.ocp.stream;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CalculationMethods {

    public static void main(String[] args) {

        // count, min and max are implement in the Stream interface and its primitive type

        // long count();
        System.out.printf("Stream number of elements is %d%n",getAlphabeticStream().count());
        // Optional<T> min(Comparator<? super T> comparator);
        // in min and max methods from the generic stream interface, you have to provide a comparator
        // using primitive interface you can't do the same because elements will be ordered using the natural ordering
        System.out.printf("Min of elements is %s%n",getAlphabeticStream().min((i,j) -> i.compareTo(j)).get());
        System.out.printf("Max of elements is %s%n",getAlphabeticStream().max((i,j) -> i.compareTo(j)).get());

        System.out.printf(" ---------------------------------- %n");

        // OptionalInt min(); OptionalInt max();
        System.out.printf("Min of elements is %s%n",getNumericStream().min().getAsInt());
        System.out.printf("Max of elements is %s%n",getNumericStream().max().getAsInt());

        System.out.printf(" ---------------------------------- %n");

        // reduce method will perform a reduce operation on the elements of the stream. Stream has 3 methods to perform the reduce
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        // performing a reduction of the stream using an associative accumulation function and returns an Optional describing the reduced value
        System.out.printf("Sum of elements is %d%n",getIntegerStream().reduce((i,j) -> i+j).get());

        // T reduce(T identity, BinaryOperator<T> accumulator);
        // performing a reduction of the stream using the identity value and an associative accumulation function and returns the reduced value
        System.out.printf("Mul of elements is %d%n",getIntegerStream().reduce(1,(i,j) -> i*j));

        // The average and the sum methods exists only in primitive streams
        // OptionalDouble average();
        OptionalDouble average = getNumericStream().average();
        System.out.printf("Average of elements is %d%n",average.getAsDouble());

        // int sum(); the return type is the same as the stream type
        int sum = getNumericStream().sum();
        System.out.printf("Sum of elements is %d%n",sum);
    }

    private static Stream<String> getAlphabeticStream() {
        return Stream.of("A", "C", "B");
    }
    private static IntStream getNumericStream() {
        return IntStream.of(3, 1, 4, 5 ,2);
    }
    private static Stream<Integer> getIntegerStream() {
        return Stream.of(2, 4, 5, 3);
    }
}
