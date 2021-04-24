package com.certification.oca.examples.advancedclassdesign.enums;

import java.util.Arrays;

public enum Direction {

    // Enum classes extends from the java.lang.Enum implicitly so you can't extend another class
    // constructors are private scoped
    // you can use == to compare enums instead of equals
    // in a enum constructor you can't invoke the superclass constructor

    NORTH(0), EAST(90), SOUTH(180), WEST(270);

    private int angle;
    Direction(int angle){
        // the absence of the access modifier in an enum means that the scope is private
        this.angle = angle;
    }
    public void print(){
        System.out.printf("The direction is at %s degrees.", this.angle);
    }

    // to String is the only method that you can override from the Enum class
    // all other methods in the Enum class are final
    @Override
    public String toString() {
        return "Direction{" +
                "angle=" + angle +
                '}';
    }

    public static void main(String[] args) {
        System.out.printf("North %s %n",Direction.NORTH);   // calls the toString
        System.out.printf("North name %s %n",Direction.NORTH.name());
        System.out.printf("North position %s %n",Direction.NORTH.ordinal());
        System.out.printf("North vs West %s %n%n",Direction.NORTH.compareTo(Direction.WEST));

        Direction south = Direction.valueOf("SOUTH");
        System.out.printf("The south is %s%n",south);
        south = ExampleEnum.valueOf(Direction.class, "SOUTH");
        System.out.printf("The second south is %s%n%n",south);

        Arrays.stream(Direction.values()).forEach(System.out::println);
    }
}

enum ExampleEnum{
    MALE, FEMALE
}
