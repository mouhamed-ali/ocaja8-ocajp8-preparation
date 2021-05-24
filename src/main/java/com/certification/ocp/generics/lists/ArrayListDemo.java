package com.certification.ocp.generics.lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>(55);   // default capacity is 10; here we've specified 55
        list = new ArrayList<>();   // capacity is 10

        // public boolean add(E e) ; Returns: true if this collection changed as a result of the call
        list.add("A"); list.add("B"); list.add("A"); list.add("C"); list.add("A");  // add adds the element at the end
        // add returns a boolean (from Collection interface) : true if this collection changed as a result of the call

        // public void add(int index, E element)
        list.add(4, "D");   // add D after C; index 4
        print(list);

        // public E set(int index, E element) ; Returns: the element previously at the specified position
        list.set(4, "E");   // replace the D with E
        print(list);

        // public E remove(int index) ; Returns the element that was removed from the list
        list.remove(2); // removes A in index

        // public boolean remove(Object o) ; Returns true if this list contained the specified element
        // Removes the first occurrence of the specified element from this list, if it is present.
        list.remove("B");   // removes first object equal to A
        print(list);
        System.out.printf("First Index of A is %d%n", list.indexOf("A"));
        System.out.printf("Last Index of A is %d%n", list.lastIndexOf("A"));   // first and last are the same

        Iterator<String> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.printf("%10s%n", listIterator.next());
        }
    }

    private static void print(ArrayList<String> list) {
        System.out.printf("List elements are : %s%n", list);
    }
}
