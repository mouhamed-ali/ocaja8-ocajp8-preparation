package com.certification.ocp.concurent.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExample {

    public static void main(String[] args) {

        ExecutorService singleTread = Executors.newSingleThreadExecutor();
        Callable<Integer> worker1 = () -> {
            System.out.printf("Worker 1 is executed %n");
            return 1;
        };
        Callable<Integer> worker2 = () -> {
            System.out.printf("Worker 2 is executed %n");
            return 2;
        };

        Future<Integer> future1 = singleTread.submit(worker1);
        Future<Integer> future2 = singleTread.submit(worker2);

        singleTread.shutdown();

        // as the singleTread has only one thread -> the passed workers will be treated one by one in the order of theirs
        // submission
    }
}
