package com.certification.oca.examples.examples.test;

import java.util.ArrayList;
import java.util.List;

public class Finalizer {

    private static List objects = new ArrayList<>();

    protected void finalize()  {
        System.out.println("Launch finalize method : " + this.toString() + " , " + this.hashCode());
        objects.add(this);  // Don't do this
    }

    public static void main(String [] args) throws InterruptedException {

        int i1 = 999992;
        for(int i = 0; i< i1; i++){

            Finalizer finalizer = new Finalizer();
        }

        objects = null;

        System.out.println();

        for(int i = 0; i< i1; i++){}

        System.out.println();

        for(int i = 0; i< i1; i++){

            System.out.print("Stopping... ");
        }
    }
}
