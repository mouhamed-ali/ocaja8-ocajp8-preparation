package com.certification.ocp.generics.examples;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardsExample {

    public static void main(String[] args) {

        System.out.println("Print strings list");
        List<String> stringList = new ArrayList<>(Arrays.asList("This", "is", "a", "simple", "example"));
        print(stringList);

        System.out.printf("%n%nPrint long list%n");
        List<Long> longList = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 3L, 4L, 5L));
        print(longList);

        // so what's the difference between List<Object>, List and List<?>.
        // Well if you pass List<Object> to print, you'll have a compilation error
        System.out.printf("%n%nModifyAndPrintUnsafe long list%n");
        modifyAndPrintUnsafe(longList);
        System.out.printf("%n");
        modifyAndPrintUnsafe(stringList);
        // as this method uses a raw type, it will not check the list type before modifying it which will work with longList
        // but with stringList it will merge longs with strings but be aware there is no runtime exceptions (errors related to generics are mostly compile time)

        System.out.printf("%n%nModifyAndPrintSafe long list%n");
        modifyAndPrintSafe(stringList);

        // Tip
        System.out.printf("%n%nTip%n");
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List rawList = integerList;
        rawList.add("'This will be stored in the arraylist - you have to use generics to prevent this at compile time'");
        print(rawList);
    }

    public static void print(@NonNull List<?> list) {

        // the List<?> here means that this is a list of any type
        // List<?> is a supertype of any List type which means you can pass List<Integer>, List<String> or even List<Object>
        list.forEach(obj -> System.out.printf("%s , ", obj));
    }

    public static void modifyAndPrintUnsafe(@NonNull List list) {

        list.remove(list.size() - 1);
        list.add(Long.MAX_VALUE);
        list.forEach(obj -> System.out.printf("%s , ", obj));
    }

    public static void modifyAndPrintSafe(@NonNull List<?> list) {

        // if you use the wildcard, you are saying to the compiler that the type is unknown and you have to ignore it
        // if you try to add an element like in the code below, the compiler does not know the type of objects hold by the list so it is risky
        // to add elements to it. The compiler will not allow you to call methods that modify the object
        list.remove(list.size() - 1);
        //list.add(Long.MAX_VALUE);     // compile error
        list.forEach(obj -> System.out.printf("%s , ", obj));
    }
}
