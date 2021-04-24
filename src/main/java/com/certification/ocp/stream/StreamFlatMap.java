package com.certification.ocp.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFlatMap {

    public static void main(String[] args) {

        Author author1 = new Author(1, "David");
        Author author2 = new Author(2, "Michel");
        Book book = new Book("Aladin", Arrays.asList(author1, author2));

        // the flatMap returns a stream consisting of the results of replacing each element of the original stream with the contents
        // of a mapped stream produced by applying the provided mapping function to that element
        Stream.of(book).flatMap(b -> b.getAuthors().stream()).forEach(System.out::println);
        System.out.printf(" ---------------------------------- %n");

        // primitive streams has the same
        // flatMapToInt
        // flatMapToLong
        // flatMapToDouble
        IntStream intStream = Stream.of(book).flatMapToInt(b -> b.getAuthors().stream().mapToInt(Author::getId));
        intStream.forEach(System.out::println);
        // the flatMapToInt returns an IntStream and accepts a stream of int as parameter that why we've the mapToInt method
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Book{
        String name;
        List<Author> authors;
    }
    @AllArgsConstructor
    @Getter
    @ToString
    public static class Author {
        private int id;
        private String name;
    }
}
