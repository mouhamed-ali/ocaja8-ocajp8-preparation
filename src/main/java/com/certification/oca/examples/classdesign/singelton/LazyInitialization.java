package com.certification.oca.examples.classdesign.singelton;

public class LazyInitialization {

    private static LazyInitialization INSTANCE;
    private LazyInitialization(){}

    public static synchronized LazyInitialization getInstance() {

        if(INSTANCE == null)
            INSTANCE = new LazyInitialization();
        return INSTANCE;
    }

    public void talk(){
        System.out.printf("%n I'm Talking ...%n");
    }

    public static void main(String[] args) {

        LazyInitialization lazyInitialization = LazyInitialization.getInstance();
        lazyInitialization.talk();
    }
}
