package com.certification.ocp.generics.lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayDequeDemo {

    public static void main(String[] args) {

        // ArrayDeque is a resizable array implementation of the Deque interface supporting insertion removal and retrieval of elements at both ends
        // ArrayDeque have no capacity restrictions
        // default constructor holds 16 elements, you can override the number using the ArrayDeque(int size) constructor, you can also pass a collection
        // ArrayDeque does not define any method of its own all methods are from its Deque interface of AbstractCollection (because ArrayDeque extends AbstractCollection but it's not our subject here)

        ArrayDeque<String> arrayDeque = new ArrayDeque<>(Arrays.asList("C", "D", "E"));

        // add* ; inserts the specified element to the deque
        arrayDeque.addFirst("B"); arrayDeque.addLast("F");  arrayDeque.addFirst("A"); arrayDeque.addLast("G");  // returns void
        arrayDeque.add("H"); // returns boolean ; this is a method from AbstractCollection
        print(arrayDeque);

        // remove* ; removes an element from the deque
        System.out.println("remove*");
        System.out.println(arrayDeque.removeFirst());
        System.out.println(arrayDeque.removeLast());
        print(arrayDeque);  // you can use remove(Object o) from AbstractCollection also

        // get* ; returns the element at the specified position
        System.out.println("### get*");
        System.out.println(arrayDeque.getFirst());
        System.out.println(arrayDeque.getLast());

        // peek* ; retrieves but not remove the element at the specified position
        System.out.println("### peek*");    // peek : jeter un coup d'oeil
        System.out.println(arrayDeque.peekFirst());
        System.out.println(arrayDeque.peekLast());
        System.out.println(arrayDeque.peek());  // peek first

        // poll* ; retrieves and remove the element at the specified position
        System.out.println("### poll*");    // Obtenir
        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollLast());
        System.out.println(arrayDeque.poll());  // poll first
        print(arrayDeque);

        // offer* ; inserts the element at the specified position
        System.out.println("### offer*");   // offrir
        System.out.println(arrayDeque.offerFirst("A")); // returns boolean (uses addFirst check implementation)
        System.out.println(arrayDeque.offerLast("Y"));
        System.out.println(arrayDeque.offer("Z"));  // offer last
        print(arrayDeque);

        // the most of the methods here calls each other, example : offerFirst calls addFirst so check the implementation
        // the difference between get , peek and poll is : if deque empty : poll and peek -> returns null | get throws NoSuchElementException

        Iterator<String> iterator = arrayDeque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
        iterator = arrayDeque.descendingIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    private static void print(ArrayDeque<String> list) {
        System.out.printf("List elements are : %s%n", list);
    }
}
