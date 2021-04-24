package com.certification.ocp.maps;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetDemo {

    public static void main(String[] args) {

        // PN : the default value of the hashcode method is the object memory address
        Set<Person> persons = new HashSet<>();
        persons.add(new Person("David", 32));
        persons.add(new Person("David", 32));
        // formatting with printf : https://www.baeldung.com/java-printstream-printf
        System.out.printf("\t First Example : Set content is %s%n%n", persons);
        // persons should have only one object why two ? hashset does not allow duplications
        // well because we did not implement the equals and hashcode, uncomment the lombok annotation and it will work

        Set<Book> books = new HashSet<>();
        books.add(new Book("The Gambit Game", 15248));
        books.add(new Book("The Gambit Game", 15248));
        System.out.printf("\t Second Example : Set content is %s%n%n", books);
        // books seems ok, we've implement the equals and hashcode why it does not work ?
        // well, hashset uses a hashmap to check duplicates and here the hashcode method generates a new number
        // for each object so all objects will be added
        // uncomment the code to make it work, have a look at constructor and add(E e) methods, it uses the map.put method which
        // returns null if the object does not exist otherwise it will put the new and returns the last value of the object
    }

    @AllArgsConstructor
    @ToString
    //@EqualsAndHashCode
    public static class Person{
        public String name;
        public Integer age;
    }

    @ToString
    public static class Book{
        public static Integer BOOK_NUMBER = 0;
        public String author;
        public Integer iban;

        public Book(String author, Integer iban) {
            BOOK_NUMBER++;
            this.author = author;
            this.iban = iban;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return Objects.equals(author, book.author) && Objects.equals(iban, book.iban);
        }

        @Override
        public int hashCode() {

            return BOOK_NUMBER;
            //return Objects.hash(author, iban);
        }
    }
}
