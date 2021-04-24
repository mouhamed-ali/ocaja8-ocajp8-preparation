package com.certification.ocp.concurent.examples;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFibonacci2 extends RecursiveTask<Long> {

    private static final int NUM_THREADS = 5;
    private long fibNumber; // This is the fibonacci number to calculate

    public ForkJoinFibonacci2(long fibNumber) {
        this.fibNumber = fibNumber;
    }
    private long fib(long n) {
        if (n <= 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }

    @Override
    protected Long compute() {

        if (fibNumber < NUM_THREADS) {
            return fib(fibNumber);
        } else {
            ForkJoinFibonacci2 partOne = new ForkJoinFibonacci2(fibNumber - 2);
            partOne.fork();
            ForkJoinFibonacci2 partTwo = new ForkJoinFibonacci2(fibNumber - 1);
            Long result = partTwo.compute();
            return partOne.join() + result;
        }
    }

    public static void main(String... args){

        ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);
        ForkJoinFibonacci2 task = new ForkJoinFibonacci2(8);
        System.out.printf("fib(8) is 21 and our result is : %s %n",pool.invoke(task));
    }
}
