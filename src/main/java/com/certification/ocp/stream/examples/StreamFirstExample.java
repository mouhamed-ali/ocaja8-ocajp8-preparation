package com.certification.ocp.stream.examples;

import java.util.Collection;
import java.util.stream.Stream;

public class StreamFirstExample {

    public static void main(String[] args) {

        // The stream interface represents a sequence of elements and supports aggregate operations
        Stream<String> names = Stream.of("John", "Jane");
        // A stream pipeline is comprised of a source, zero or more intermediate operations (or lazy operations) and a single terminal operation
        names.filter(name -> {
            System.out.println(name);
            return name.equals("John");
        })
        // After the terminal operation is performed, the stream pipeline is considered as consumed
        // If there is no terminal operation the pipeline will not be processed
        .forEach(System.out::println);

        // you can't do names.forEach(------) because the stream will be closed just after the filter method
        // https://www.baeldung.com/java-stream-operated-upon-or-closed-exception

        // if you check the result you'll see that the element John has been processed through up the pipeline before processing the Jane element
    }
}
