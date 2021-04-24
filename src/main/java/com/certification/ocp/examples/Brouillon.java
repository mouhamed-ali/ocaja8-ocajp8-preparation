package com.certification.ocp.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Brouillon<T> {

    private List<T> data;
    private boolean foundMatch = false;
    public Brouillon(List<T> list) {
        this.data = list;
    }
    public void exists(T value,int start, int end) {
        if(end-start<=1) {
            foundMatch = foundMatch || value.equals(data.get(start));
        } else {
            final int middle = start + (end-start)/2;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    exists(value,start,middle);
                }
            };
            new Thread(runnable).run();
            new Thread(() -> exists(value,middle,end)).run();
        }
    }

    public static void main(String[] participants) {

        List<Integer> original = new ArrayList<>(Arrays.asList(1,2,3));
        Deque<Integer> copy3 = new ConcurrentLinkedDeque<>(original);
        System.out.println(copy3);
        System.out.println("-------------------------------");
        for(Integer q : copy3) {
            copy3.push(0);
            System.out.println(copy3);
        }
        System.out.println("-------------------------------");
        System.out.println(copy3);
    }
}
