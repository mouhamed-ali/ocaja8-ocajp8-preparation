package com.certification.oca.examples.examples.test3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class HOPP{
    int z = 0;
    public HOPP(int a) {
    }
}

interface STR{}

public class Hop  {


    public Hop() {

        new HOPP(4).z = 5;
    }

    public static void main(String[] args) {

        int dayOfWeek = 0;
        switch(dayOfWeek) {
            case 0:
                System.out.println("Sunday");
            default:
                System.out.println("Weekday");
            case 6:
                System.out.println("Saturday");
                break;
        }

        int[] a = new int[4];
        int z = 0;
        for(int x : a )
            switch (x){
                case 1 :
                    System.out.println();
            }

        DateTimeFormatter f = DateTimeFormatter.ofPattern("Y");
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("YY");
        System.out.println(f.format(LocalDate.now()));
        System.out.println(f1.format(LocalDate.now()));

        int x = 1;
        if((4>x) ^ ((++x + 2)>3)) x++;
        if((4> ++x) ^ !(++x == 5)) x++;
        System.out.println(x);

        Integer a1 = 0;

        String e1 = "";
        final int r = 7;

        label: if(r == 7){

            System.out.println("r == 7");

            int i = 0;
            outer :while ( i < 2){

                i++;
                if(i == 1){

                    continue outer;
                }
                System.out.println(i);
            }
        }

        System.out.println("break label");

    }
}
