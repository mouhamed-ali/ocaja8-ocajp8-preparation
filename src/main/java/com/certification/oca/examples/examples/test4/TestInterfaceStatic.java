package com.certification.oca.examples.examples.test4;

import java.util.ArrayList;

public class TestInterfaceStatic {


    public static final void main(String[] args){

        ArrayList<String> buiStrings = new ArrayList<>(2);
        System.out.println(buiStrings.size());
        for(String str : buiStrings)
            System.out.println(buiStrings.size());
    }

    private static void changeMe(Object obj) {

        obj = null;
    }
}

class Mineral{

    TestIF j;
}

class Gem extends Mineral{}
