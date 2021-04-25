package com.certification.ocp.concurent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamsExample {

    public static void main(String[] args) {

        /*
            The stream api facilitates parallel execution by breaks a given stream into multiple sub-streams, process those sub-streams then combines the result
            The stream pipeline should produce the same result regardless of whether it is executed sequentially or in parallel. You should not then do stateful operations
            You can create a parallel stream using one of these two methods
         */
        Stream<String> stream = Arrays.asList("a", "b", "c", "d", "e", "f").parallelStream();
        stream = Arrays
                .asList("j", "a", "v", "a", "8", "-", "c", "e", "r", "t", "i", "f", "i", "c", "a", "t", "i", "o", "n")
                .stream().parallel();   // this can produce two sub-streams for example
        System.out.printf("The stream is parallel ? , the response is %b%n", stream.isParallel());

        /*
            The stream interface defines three overloaded methods of the reduce method, the third on is :
            <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
            The reduce method will part elements of parallel stream into multiple sub-streams, then processing each sub-stream in separate thread and finally combining the result

         */
        BiFunction<String, String, String> accumulator = (s1,s2) -> s1.concat(s2); // joins string elements in a single thread
        BinaryOperator<String> combiner = (s1, s2) -> s1.concat(s2);    // concatenates the result from all thread processing the stream
        String result = stream.reduce("", accumulator, combiner);   // the result will be a single string
        System.out.print(result);

        System.out.printf("%n ----------------------------- %n");

        /*
            collect method of the stream interface has an overloaded form that processes elements of parallel stream in separate threads, then combines their result
            <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
         */
        stream = Arrays.asList("This is a simple test of the string ocajp8".split("")).parallelStream();
        StringBuilder builder = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        // please be careful here we are using two overloaded methods of the StringBuilder. The accumulator accepts string arguments while the combiner takes another StringBuilder as input
        System.out.printf("The builder result is '%s'%n", builder);

        System.out.printf("%n ----------------------------- %n");

        /*
            You can also use a concurrent collector when working on parallel streams for better performance
            <R, A> R collect(Collector<? super T, A, R> collector);
            for example the Collectors::groupingByConcurrent
         */
        Stream<Integer> integers = Stream.of(0, 1, 2, 3, 4, 5, 6).parallel();
        ConcurrentMap<Boolean, List<Integer>> resultMap = integers.collect(Collectors.groupingByConcurrent(i -> i % 2 == 0));
        System.out.println(resultMap);

        // you can also use the toConcurrentMap which is like the toMap method returning a concurrent collection that accumulates into a concurrent map
        stream = Arrays.asList("java", "vue", "react", "angular").parallelStream();
        ConcurrentMap<String, Integer> concurrentMap = stream.collect(Collectors.toConcurrentMap(String::toUpperCase, String::length));
        System.out.println(concurrentMap);
        // Notice that when concurrent collectors are used for immutable reductions on parallel streams they produce the same result as non concurrent collectors
        // but with better performance

        System.out.printf("%n ----------------------------- %n");

        /*
            Example 1 :
            void forEachOrdered(Consumer<? super T> action);
            Performs an action for each element of this stream, in the encounter order of the stream if the stream has a defined encounter order.
         */
        Stream.of(1,2,3,4,5,6,7,8,9,0).forEachOrdered(System.out::print);
        System.out.println();
        Arrays.asList(1,2,3,4,5,6,7,8,9,0).parallelStream().forEachOrdered(System.out::print);

        System.out.printf("%n ----------------------------- %n");

        /*
            Example 2 :
            As specified in the java API documentation the accumulation function adn the combination function of a reduced method must be compatible with each other
            in this situation the accumulator is a multiplication while the combiner is an addition operator. those operators do not match leading to an unpredictable result
         */
        System.out.printf("The result is : %d", Arrays.asList(1,2,3,4).parallelStream().reduce(1,
                (i1,i2) -> i1*i2, (i1,i2) -> i1+i2));
    }
}
