package com.certification.ocp.lambda;

import java.util.function.*;

public class BinaryInterfaces {

    public static void main(String[] args) {

        // BiPredicate is the only binary specialization of the Predicate Interface
        // BiPredicate<T,U> : boolean test(T t, U u)
        // It defines three default  methods and , or and negate like in Predicate
        BiPredicate<Integer, Double> biPredicate = (i,d) -> {
            System.out.printf("%n %d > %f is equal to %s", i , d, i > d);
            return i > d ;
        };
        biPredicate.test(1, 99.0);
        System.out.printf("%n ------------ %n");

        // Consumer has four binary specializations including ; BiConsumer , ObjIntConsumer, ObjLongConsumer and ObjDoubleConsumer
        // BiConsumer<T,U> : void accept(T t, U u)
        // ObjXConsumer<T> : void accept(T t, x value)
        // BiConsumer defines a default method andThen like in the Consumer interface
        BiConsumer<String, String> biConsumer = (str1, str2) -> System.out.println(str1 + " ; " + str2);
        biConsumer.accept("Java", "8");

        ObjIntConsumer<String> objIntConsumer = (str,i) -> System.out.println(str.length() - i);
        objIntConsumer.accept("Java",8);
        System.out.printf(" ------------ %n");

        // Function has 4 binary specializations ; BiFunction , ToIntBiFunction, ToLongBiFunction and ToDoubleBiFunction
        // BiFunction<T,U,R> : R apply(T t, U u)
        // ToXBiFunction<T,U> : x applyAsX(T t, U u)
        // BiFunction defines a default method andThen like in the Consumer interface, it does not define the compose method
        BiFunction<Integer, Integer, String> biFunction = (i,j) -> String.valueOf(i) + String.valueOf(j);
        System.out.printf("Concatenate string %d and %d gives you %s%n", 2, 5, biFunction.apply(2,5));

        ToDoubleBiFunction<Integer, Integer> toDoubleBiFunction = (i,j) -> i/j;
        System.out.printf("Divide %d and %d gives you %s%n", 5, 2, toDoubleBiFunction.applyAsDouble(5,2));

    }
}
