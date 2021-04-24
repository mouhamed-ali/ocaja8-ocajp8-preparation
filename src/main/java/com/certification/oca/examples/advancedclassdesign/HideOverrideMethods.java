package com.certification.oca.examples.advancedclassdesign;

import java.io.IOException;

public class HideOverrideMethods {

    // If a subclass defines a static method with the same signature as a static method in the superclass,
    // then the method in the subclass hides the one in the superclass.
    public static void main(String[] args) throws Exception{

        Cat.staticMethod();
        Cat myCat = new Cat();
        myCat.staticMethod();
        myCat.instanceMethod();
        System.out.println("------------------------------------");
        Animal.staticMethod();
        Animal myAnimal = myCat;
        myAnimal.staticMethod();
        myAnimal.instanceMethod();
    }
}

class Animal {
    public static void staticMethod() throws IOException{
        System.out.printf("Animal : Static Method. %n");
    }
    protected void instanceMethod() throws Exception{
        System.out.printf("Animal : Instance Method. %n");
    }
}

class Cat extends Animal {

    public static void staticMethod() throws RuntimeException{
        // this is hiding and not overriding
        System.out.printf("Cat : Static Method. %n");
    }
    public void instanceMethod() throws IOException {
        // when overriding a method you can make the scope more large protected -> public (the opposite is wrong)
        // make the exception more specific Exception -> IOException
        System.out.printf("Cat : Instance Method. %n");
    }
}
