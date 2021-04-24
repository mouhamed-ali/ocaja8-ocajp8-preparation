package com.certification.ocp.stream;

import java.util.stream.Stream;

public class SearchMethods {

    public static void main(String[] args) {

        // Optional<T> find*() ; returns an Optional object describing an element from the stream
        // boolean *Match(Predicate<? super T> predicate) ; returns whether an element of the stream matches the given predicate

        // findFirst returns an optional describing the first element of the stream or an empty optional if the stream is empty
        // findAny returns an optional describing an arbitrary element of the stream or an empty optional if the stream is empty
        // the findAny allows for maximum performance in parallel operations
        System.out.printf("Find first returns %s%n",getStream().findFirst().get());
        System.out.printf("Find any returns %s%n -------------------- %n",getStream().findAny().get());

        // anyMatch returns whether any element of the stream match the given predicate, if the stream is empty then false is returned and the predicate is not evaluated
        // allMatch returns whether all elements of the stream matches the given predicate, if the stream is empty then true is returned and the predicate is not evaluated
        // noneMatch returns whether no element of the stream match the given predicate, if the stream is empty then true is returned and the predicate is not evaluated
        System.out.printf("anyMatch ; %s%n", getStream().anyMatch(s -> s.length()>5));
        System.out.printf("allMatch ; %s%n", getStream().allMatch(s -> s.length()>5));
        System.out.printf("noneMatch ; %s%n", getStream().noneMatch(s -> s.length()>5));
    }

    private static Stream<String> getStream() {
        return Stream.of("First", "Second");
    }
}
