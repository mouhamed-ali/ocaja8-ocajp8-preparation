package com.certification.oca.examples.examples.test;

import java.util.ArrayList;
import java.util.List;

public class Lambda {

    static String str = "chaine";

    static{
        str = "chaine2";
    }

    {
        str = "chaine3";
    }

    public static final void main(String[] a){

        List<String> bunnies = new ArrayList<>();
        bunnies.add("long ear");
        bunnies.add("hobit");
        bunnies.add("floppy");
        bunnies.add("hoppy");
        System.out.println(bunnies);     // [long ear, hobit, floppy, hoppy]
        bunnies.removeIf(s -> s.charAt(0) != 'h');
        System.out.println(bunnies);     // [hobit, hoppy]

        boolean[] koala = new boolean[2];
    }
}

class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
    public static Bird eat(int food) {
        System.out.println("Bird is eating "+food+" units of food");
        return null;
    }
}


