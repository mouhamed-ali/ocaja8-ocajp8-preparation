package com.certification.oca.examples.advancedclassdesign.innerclass;

public class AnonymousClass {

    // Anonymous Classes are a special case of local classes, they have no name and allow you to declare and instantiate a class at the same time
    // They comply with all the restrictions of local classes and in addition they cannot define constructors
    // An Anonymous class must extend a class or implement an interface

    // to create an anonymous class ; use the new operator + the name of the interface to implement or the class to extend

    public static void main(String[] args) {

        AnonymousClass  anonymousClass = new AnonymousClass(){

            // public AnonymousClass(){} ; An anonymous class cannot have an explicitly declared constructor.
        };

        Fox fox = new Fox() {
            @Override
            public String getName() {
                return null;
            }
        };

        // replace it with lambda
        Fox fox2 = () -> null;
    }

    public interface Fox{
        String getName();
    }
}
