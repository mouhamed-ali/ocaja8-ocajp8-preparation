package com.certification.ocp.generics;

import java.util.ArrayList;
import java.util.List;

// Bounded type parameters example
public class Person<S, T extends Number> {
    // extends here means any type that extends or implements the second type which is Number (like Long, Integer, Float ...)
    // you can use and subtype of Number or the Number itself

    private S name;
    private T age;

    Person(S name, T age){
        this.name = name;
        this.age = age;
    }

    public S getName() {
        return name;
    }

    public int getAge() {
        // here is the benefit of bounded type parameters, here we can use methods from the Number class
        return age.intValue();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }

    public static final void main(String[] a){

        Person<String, Integer> person = new Person<>("David", 25);
        System.out.println(person);

        // Using generics prevents you from using casting and class cast exceptions at runtime and it is recommended but
        // nothing stops you from not taking advantage of generics so all the declarations below can be compiled
        Person person1 = new Person("David", 25);
        // Person expects a subclass of Number when instantiating so you can't pass a String for example as a second example
        Person person2 = new Person<String, Long>("David", 25l);
        Person person3 = new Person<>(99, 25l);

        // here is the type safety problem
        String name = (String) person3.getName();
    }
}
