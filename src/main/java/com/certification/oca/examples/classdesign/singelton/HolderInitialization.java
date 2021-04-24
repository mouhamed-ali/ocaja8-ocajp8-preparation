package com.certification.oca.examples.classdesign.singelton;

public class HolderInitialization {

    // Initialization on demand holder Idiom
    private HolderInitialization(){}

    public static synchronized HolderInitialization getInstance() {
        return HolderInitializationHolder.INSTANCE;
    }

    public void talk(){
        System.out.printf("%n I'm Talking ...%n");
    }

    public static class HolderInitializationHolder {
        private static HolderInitialization INSTANCE = new HolderInitialization();
    }

    public static void main(String[] args) {

        HolderInitialization holderInitialization = HolderInitialization.getInstance();
        holderInitialization.talk();
    }
}
