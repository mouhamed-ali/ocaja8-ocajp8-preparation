package com.certification.ocp.concurent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {

        /*
            CopyOnWriteArrayList is a thread-safe variant of the ArrayList class in which all mutative operations are implemented by
            making a copy of the underlying array. Manipulating a collection this way is too expensive, so this class is only suitable
            when the number of write operations is too small compared to the number of read operations

            When an iterator instance is created to iterate over elements in the list, it provides a snapshot state of the list at the point
            that the iterator was constructed.

            All methods in CopyOnWriteArrayList has the same functionality as the methods of the same name in the ArrayList class.
            CopyOnWriteArrayList has two extra methods :
            * addIfAbsent : appends the given element if it is not already present in the list
                 public boolean addIfAbsent(E e)
            * addAllAbsent : appends elements in the given collection that are not existent in the list
                public int addAllAbsent(Collection<? extends E> c)
         */

        List<String> immutableList = new CopyOnWriteArrayList<>(Arrays.asList("Java", "Vue", "React"));
        immutableList.add("Angular");
        System.out.print(immutableList);
        System.out.printf("%n ----------------------------- %n");

        Iterator<String> iterator = immutableList.iterator();
        // At this point an iterator is created referencing to the snapshot or a copy of the current elements in tha backing array
        // Any changes to the list later on like below are not reflected in the iteration
        immutableList.add("Python");
        immutableList.remove("Java");
        while (iterator.hasNext()){
            System.out.printf("%s, ",iterator.next());
            immutableList.add("Scala");
        }
        System.out.printf("%n ----------------------------- %n");
        // we can now see the modifications done on the backing array
        System.out.println(immutableList);

        System.out.printf("%n ----------------------------- %n");
        iterator = immutableList.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();
            System.out.printf("%s, ",element);
            iterator.remove();
            // When iterator is created, it references a snapshot array that never changes.
            // As changing operations are not supported during iteration and doing so would result in unsupported operation exception
        }
    }
}
