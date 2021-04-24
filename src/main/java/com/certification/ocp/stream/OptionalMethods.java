package com.certification.ocp.stream;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

public class OptionalMethods {

    public static void main(String[] args) {

        // empty : returns empty optional instance (optional with no value)
        Optional<String> empty = Optional.empty();

        // of : returns optional instance with the given value if it is non null, otherwise NullPointerException is thrown
        empty = Optional.of("This is a course");
        empty = Optional.of(null); // this throws NullPointerException

        // ofNullable : returns optional instance with the given value if it is non null, otherwise returns an empty optional
        empty = Optional.ofNullable("This is a course");
        empty = Optional.ofNullable(null);  // returns empty Optional

        // get : returns the contained value if it is present, otherwise a NoSuchElementException
        empty.get(); // if the optional is empty (which is our case) a NoSuchElementException will be thrown

        // orElse : returns the contained value if it is present, otherwise returns the given argument
        Optional<String> optional = Optional.of("React");
        System.out.println(optional.orElse("Java")); // prints React
        optional = Optional.empty();
        System.out.println(optional.orElse("Java")); // prints Java

        // orElseGet : returns the value if it is present, otherwise invokes the Supplier argument and returns the invocation result
        optional = Optional.empty();
        String value = optional.orElseGet(() -> "Java"); // returns Java

        // orElseThrow : returns the value if it is present, otherwise throws an exception created by the supplier argument
        optional = Optional.empty();
        try {
            value = optional.orElseThrow(() -> new IOException("A generated io exception")); // throws io exception
        } catch (IOException e) {
            e.printStackTrace();
        }

        // isPresent : returns true if there is a value present, otherwise false
        System.out.println(optional.isPresent() ? optional.get() : "Not Found");

        // ifPresent : invokes the consumer if the value is present, otherwise does nothing
        optional.ifPresent(System.out::println);

        // filter : returns an optional object if the value is present and matches the predicate, otherwise returns an empty optional
        Optional<Integer> numberOptional = Optional.of(1);
        numberOptional.filter(i -> i%2==0).isPresent(); // returns false

        // map : applies the mapping function to the the value, otherwise returns an empty optional
        System.out.println(
                Optional.of("Java").map(String::length).get()
        );// prints 4

        // flatMap : applies the optional bearing mapping function to the value, otherwise returns an empty optional
        // flatMap is usually used instead of the map method if the return type of the given function is optional
        System.out.println(
                Optional.of("Java").flatMap(s -> Optional.of(s.toUpperCase())).get()
        );// prints JAVA
    }
}
