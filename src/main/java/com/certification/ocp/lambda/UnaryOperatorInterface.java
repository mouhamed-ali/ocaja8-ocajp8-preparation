package com.certification.ocp.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
import java.util.function.UnaryOperator;

public class UnaryOperatorInterface {

    public static void main(String[] args) {

        // The unary operator is a specialization of BiFunction representing an operation upon a single operand
        // and producing a result of the same type as the operand
        // UnaryOperator is a sub interface of Function, it inherits all members of the Function interface including abstract
        // and default methods
        // Abstract method : T apply(T t)

        UnaryOperator<Person> unaryOperator = person -> new Person(person.name.toUpperCase(), person.age + 50);
        Person person = new Person("jeff", 17);
        System.out.printf("The person %n%s%n transformation is %n%s%n", person, unaryOperator.apply(person));
    }

    @Data
    @AllArgsConstructor
    public static class Person{
        private String name;
        private Integer age;
    }
}
