package com.certification.oca.examples.examples.test;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deer extends DeerParent {
    public Deer() {
        System.out.print("Deer");
    }

    double a = 0x1_1_1;

    public Deer(int age) {
        System.out.print("DeerAge");
    }

    private boolean hasHorns() {
        return false;
    }

    public static final void main(String[] args) {
        Deer deer = new Reindeer(5);
        System.out.println("," + deer.getName() + "     $");


        if( 5 > 1l ){

        }

        int j = 1;


        int a = j++ + 5 ;


        System.out.println( j = j++ );

        System.out.println( j );

        //for(int i=0; i<10 ; ) {
          //     i = i++;
            //   System.out.println("Hello World");
            //}

        StringBuilder ab = new StringBuilder("a");
        StringBuilder bc = new StringBuilder("a");
        System.out.println(ab.equals(bc));

        //int[] ids, types;
        int[] ids = new int[] {0,0,0}, types = new int[] {0,0,0};

        System.out.println(types[1]);
        System.out.println(ids[1]);

        String[] strings = { "stringValue" };
        Object[] objects = strings;
        String[] againStrings = (String[]) objects;
        //againStrings[0] = new StringBuilder();   // DOES NOT COMPILE

        ArrayList<String> list = new ArrayList();
        list.add("ADD");
        list.add("REMOVE");

        String[] tab = list.toArray(new String[0]);

        System.out.println(Arrays.toString(tab));

//        LocalDate.of(2015, Month.JANUARY, 32);

        Period wrong = Period.ofYears(1).ofWeeks(1);

        StringBuilder numbers = new StringBuilder("0123456789");
        numbers.delete(2,  8);
        System.out.println(numbers);
        numbers.append("-").insert(2, "+");
        System.out.println(numbers);
        int[] tableau = {5};
        System.out.println(tableau.length);

        List<Integer> ages = new ArrayList<>();
        ages.add(Integer.parseInt("5"));
        ages.add(Integer.valueOf("6"));
        ages.add(7);
        ages.add(null);
        for (int age : ages) System.out.print(age);

    }

    public String getName(){

        return "Deer";
    }

    private int getMembre(){
        return 99;
    }
}

class DeerParent{


    private String getName(){

        return "DeerParent";
    }
}

class Reindeer extends Deer {

    public Reindeer(int age) {
        System.out.print("Reindeer");
    }

    public String getName(){

        return "Reindeer";
    }

    public boolean hasHorns() {
        return true;
    }


    private int getMembre(){
        return 0;
    }
}
