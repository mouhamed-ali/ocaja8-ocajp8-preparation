package com.certification.ocp.exceptions;

public class AssertExample {

    public static void main(String[] args) {

        // assert : affirmer in french
        // An assertion has a boolean value expression also called predicate you believe will evaluated to true when executed
        // Such an assertion statement throws an AssertionError if the given predicate is false

        int number = 99;
        assert Math.abs(number) > 0;    // asserts that the abs of 99 is valid, if the assert is false and AssertionError will be thrown without any details

        assert false : new String("This is a generated message"); // this will generate an error with details
        // second parameter is a message (it can be any type)

        assert false : 9 > 5; // you can even make statements
        // In java 8 assertions are compiled by default so you do not need to specify any option
        // Assertions are disabled at runtime ; you must use the flag -enableassertions or -ea to enable them
        // in Intellij add -ea as vm options to enable assertions

        //assert false : System.out.println("This is a void method"); // the second parameter must return a value otherwise you'll have a compilation exception

        // Assertions should not be used for critical checks as the cannot be executed at runtime

        StringBuilder stringBuilder = new StringBuilder("This is a string");
        assert false : stringBuilder.append("-builder");
        // this expression is valid and an assertion error will be thrown with 'This is a string-builder' as details but
        // assertions should not modify the state of an object as it may not be executed which risks your objects states

        // last indication java.lang.AssertionError is an unchecked error like RuntimeException
        // AssertionError extends Error which extends Throwable so such a statement is valid
        throw new AssertionError("An Assertion ERROR");
    }
}
