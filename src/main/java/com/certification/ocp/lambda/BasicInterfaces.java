package com.certification.ocp.lambda;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BasicInterfaces {

    public static void main(String[] args) {

        // Predicate interface methods
        Predicate<String> hasLength = str -> str.length() >= 4 ;
        Predicate<String> startsWith = str -> str.startsWith("P");
        filterNames("Paul", hasLength.and(startsWith));
        filterNames("Frederic", hasLength.or(startsWith));
        filterNames("Neo", hasLength.negate());

        System.out.printf("%n ---------------------------------------- %n%n");

        // Consumer interface methods
        Consumer<StringBuilder> comma = str -> {
            if(!str.toString().isEmpty())
                str.append(" , ");
        };
        Consumer<StringBuilder> show = System.out::println;
        consumeNames(new StringBuilder("This is a comma"), comma.andThen(show));

        System.out.printf("%n ---------------------------------------- %n%n");

        // Function interface methods
        Function<Integer, Integer> addition = nb -> nb + 2;
        Function<Integer, String> transform = nb -> nb.toString();
        transformNumber(9, addition.andThen(transform));
        transformNumber(9, transform.compose(addition)); // addition will be called first and then transform
        // to chain functions it is important that the result type of the first function matches the entry of the second (here the type is Integer)

        System.out.printf("%n ---------------------------------------- %n%n");

        // Supplier interface does not have extra methods
        Supplier<String> supplier = () -> "This is a supplier";
        String suppName = supplier.get();
        System.out.printf("The supplier name is %s", suppName);

        System.out.printf("%n ---------------------------------------- %n%n");
    }

    private static void transformNumber(int nb, Function<Integer, String> function) {
        String result = function.apply(nb);
        System.out.printf("The function result is %s%n", result);
    }

    private static void consumeNames(StringBuilder paragraph, Consumer<StringBuilder> printer) {
        printer.accept(paragraph);
    }

    private static void filterNames(String name, Predicate<String> filter) {
        if(filter.test(name)){
            System.out.printf("The name %s is accepted%n", name);
        }else {
            System.out.printf("The name %s is not accepted%n", name);
        }
    }
}
