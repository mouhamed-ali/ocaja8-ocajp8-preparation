package com.certification.ocp.generics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StaticMethodExample<S extends Number, T extends  Number> {

    private S first;
    private T second;

    public int sum(){

        return first.intValue() + second.intValue();
    }

    public static <U extends Number, V extends Number> int add(U first, V second){
        // using static methods you have declare generic parameters before using them
        // there is no relation between static methods parameters and the Object parameters (first and second in this case)
        return first.intValue() + second.intValue();
    }

    public static void main(String[] args) {

        StaticMethodExample<Double, Long> instance = new StaticMethodExample<>(3.0, 2l);
        System.out.printf("The sum of %f and %d is %d%n%n", instance.first, instance.second, instance.sum());

        Float first = 3.0f;
        Integer second = 2;
        System.out.printf("The sum of %f and %d is %d%n%n", first, second, add(first, second));
    }
}
