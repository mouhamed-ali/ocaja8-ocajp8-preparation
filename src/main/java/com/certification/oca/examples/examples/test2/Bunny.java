package com.certification.oca.examples.examples.test2;

public class Bunny {

    public static void main(String[] args) {
        Lemur lemur = new Lemur();
        System.out.println(lemur.age);
        System.out.println(lemur.hasHair());

        Primate primate = lemur;
        System.out.println(primate.hasHair());
    }
}

class Primate {

    public boolean hasHair() {
        return true;
    }
}

class Lemur extends Primate {

    public int age = 10;

    public boolean hasHair() {
        return false;
    }
}


