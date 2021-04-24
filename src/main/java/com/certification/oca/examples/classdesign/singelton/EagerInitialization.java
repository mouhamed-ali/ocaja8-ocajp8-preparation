package com.certification.oca.examples.classdesign.singelton;

public class EagerInitialization {

    private static EagerInitialization INSTANCE = new EagerInitialization();
    private EagerInitialization(){
        System.out.printf("%n Creating an instance%n");
    }

    public static synchronized EagerInitialization getInstance() {
        return INSTANCE;
    }

    public void talk(){
        System.out.printf("%n I'm Talking ...%n");
    }

    public static void main(String[] args) {

        // initialization is eager, a simple call of the class will create the instance
        System.out.printf("%n %s%n",EagerInitialization.class);
        // the instance has been created without even calling the getInstance method
        EagerInitialization eagerInitialization = EagerInitialization.getInstance();
        eagerInitialization.talk();
    }
}
