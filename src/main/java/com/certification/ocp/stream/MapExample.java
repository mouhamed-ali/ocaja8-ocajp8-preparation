package com.certification.ocp.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

public class MapExample {

    public static void main(String[] args) {

        // peek dec : Stream<T> peek(Consumer<? super T> action)
        // map dec : Stream<R> map(Function(? super T, ? extends R> mapper)
        // these methods are intermediate operations

        Stream<Person> persons = initStream();
        Stream<Person> personsPeek = persons.peek(System.out::println);
        Stream<String> personsNames = personsPeek.map(person -> person.getName());
        // personsPeek and personsNames will not perform any action on the stream until we define a terminate operation
        personsNames.forEach(System.out::println);

        // Primitive specializations of the map
        // IntStream mapToInt(ToIntFunction<? super T> mapper)
        // LongStream mapToLong(ToLongFunction<? super T> mapper)
        // DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
        persons = initStream();
        persons.mapToInt(p -> p.getAge()).forEach(System.out::println);
    }

    private static Stream<Person> initStream() {
        return Stream.of(new Person("Alice", 30), new Person("Mano", 45));
    }

    @AllArgsConstructor
    @ToString
    @Getter
    public static class Person{
        private String name;
        private Integer age;
    }
}
