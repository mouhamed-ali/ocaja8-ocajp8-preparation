package com.certification.ocp.concurent.examples;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoinFibonacci extends RecursiveAction {

    private static final int NUM_THREADS = 5;
    private long fibNumber; // This is the fibonacci number to calculate
    private long fibResult; // This is the fibonacci result after calculation

    public ForkJoinFibonacci(long fibNumber) {
        this.fibNumber = fibNumber;
    }

    private long fib(long n) {
        if (n <= 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }

    @Override
    protected void compute() {

        if (fibNumber < NUM_THREADS) {
            fibResult = fib(fibNumber);
        } else {
            ForkJoinFibonacci partOne = new ForkJoinFibonacci(fibNumber - 2);
            ForkJoinFibonacci partTwo = new ForkJoinFibonacci(fibNumber - 1);
            ForkJoinTask.invokeAll(partOne, partTwo);
            fibResult = partOne.fibResult + partTwo.fibResult;
        }
    }

    public static void main(String... args){

        ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);
        ForkJoinFibonacci task = new ForkJoinFibonacci(8);
        pool.invoke(task);
        System.out.printf("fib(8) is 21 and our result is : %s %n",task.fibResult);
    }
}
