package com.certification.ocp.generics;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Bounded type parameters example
@ToString
@AllArgsConstructor
@Setter
public class Person<S, T extends Number> {

    // extends here means any type that extends or implements the second type which is Number (like Long, Integer, Float ...) you can use and subtype of Number or the Number itself
    private S name;
    private T age;

    public S getName() {
        return name;
    }

    public void setName(S name) {
        this.name = name;
    }

    public int getAge() {
        // here is the benefit of bounded type parameters, here we can use methods from the Number class
        return age.intValue();
    }

    public T getAgeType() {
        return age;
    }

    public static final void main(String[] a) {

        Person<String, Integer> person = new Person<>("David", 25);
        System.out.println(person);

        // Using generics prevents you from using casting and class cast exceptions at runtime and it is recommended but
        // nothing stops you from not taking advantage of generics so all the declarations below can be compiled
        Person person1 = new Person("David", 25);
        // name will be treated as Object and not string in this case. age will be a Number in our case. Try to call getName and getAgeType to check the type

        // Person expects a subclass of Number when instantiating so you can't pass a String for example as a second example
        // the second diamond operator is optional as the compiler can determine types from the passed parameters (to the constructor)
        Person person2 = new Person<String, Long>("David", 25l);
        String name = (String) person2.getName();
        Number age = person2.getAgeType();
        // Person person2 = new Person<String, Long>(99, 25l);  // this results in compilation error because 99 is not a string

        // here is the type safety problem
        Person person3 = new Person<>(99, 25l);
        try {
            name = (String) person3.getName();   // 99 is not a string
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        Person<String, Integer> person4 = new Person(25, 25);   // raw type
        try {
            String name4 = person4.getName();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        Integer age4 = person4.getAgeType();
        //Person<String, Integer> person4 = new Person<>(25, 26);   // this results in compilation error because 25 is not a string
    }
}
