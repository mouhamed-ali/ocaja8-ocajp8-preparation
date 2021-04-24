package com.certification.ocp.concurent.examples;

import java.util.Arrays;
import java.util.List;

public class ReduceTerminal3 {

    public String concat1(List<String> values) {
        return values.parallelStream()
                .reduce("a",
                        (x,y)->x + y,
                        String::concat);
    }
    public String concat2(List<String> values) {
        return values.parallelStream()
                .reduce((w,z)-> z + w ).get();
    }
    public static void main(String... questions) {
        ReduceTerminal3 c = new ReduceTerminal3();
        List<String> list = Arrays.asList("Cat \t","Hat \t","Fat \t","Bat \t","Cow \t");
        String x = c.concat1(list);
        String y = c.concat2(list);
        System.out.printf("%s %n%s",x,y);
    }
}
