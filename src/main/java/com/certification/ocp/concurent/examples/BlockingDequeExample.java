package com.certification.ocp.concurent.examples;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BlockingDequeExample {

    static BlockingDeque<Integer> queue = new LinkedBlockingDeque<>(3);
    /*
     * As the capacity is 3 and we have 5 threads to populate this queue 2 of them will wait 10 seconds
     * before checking the capacity and try to add new element (this operation fails)
     *
     * When pulling elements from the deque 3 of them will pull elements successfully but the rest waits 10
     * seconds before abandon the operation
     */

    public static void main(String[] participants) {

        System.out.printf("%s - %s - Executing the first part %n",getNow(), Thread.currentThread().getName());
        IntStream.iterate(1, i -> i+1).limit(5)
                .parallel()
                .forEach(s -> {
                    try {
                        queue.offerLast(s,10000,TimeUnit.MILLISECONDS);
                        System.out.printf("%s - %s - Adding %d to the queue %n",getNow(), Thread.currentThread().getName(), s);
                        /*
                         * Inserts the specified element at the end of this deque, waiting up to the specified wait time if
                         * necessary for space to become available.
                         */
                    } catch (InterruptedException e) {e.printStackTrace();}
                });
        System.out.printf("** %s - %s - Executing the second part %n",getNow(), Thread.currentThread().getName());
        System.out.println(queue);
        System.out.println("--------------------------------------------");
        IntStream.iterate(1, i -> 5).limit(5)
                .parallel()
                .forEach(s -> {
                    try {
                        Integer pulledInt = queue.pollFirst(10, TimeUnit.SECONDS);
                        System.out.printf("%s - %s - Pulling %d from the queue %n",getNow(), Thread.currentThread().getName(),pulledInt);
                        /*
                         * Retrieves and removes the first element of this deque, waiting up to the specified wait time if necessary for
                         * an element to become available.
                        */
                    } catch (InterruptedException e) {e.printStackTrace();}
                });
        System.out.printf("Queue content : %s %n",queue);
        System.out.printf("%s - %s - Get the size : %d %n",getNow(), Thread.currentThread().getName(),queue.size());
    }

    public static String getNow(){
        return LocalDateTime.now().toString();
    }
}
