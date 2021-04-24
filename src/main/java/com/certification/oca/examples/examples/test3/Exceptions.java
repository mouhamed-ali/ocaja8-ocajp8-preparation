package com.certification.oca.examples.examples.test3;

public class Exceptions {

    public static void main(String[] a){

        try {
            System.out.println("Done !!!");
        } catch (ExhibitClosed e) {// superclass exception
            System.out.print("try back later");
        }

    }
}

class AnimalsOutForAWalk extends RuntimeException { }

class ExhibitClosed extends RuntimeException { }

class ExhibitClosedForLunch extends ExhibitClosed { }
