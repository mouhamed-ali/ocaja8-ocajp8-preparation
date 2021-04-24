package com.certification.ocp.concurent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierExample {

    public static final int NUMBER_OF_PARTIES = 2;

    public static void main(String[] args) {

        /*
            CyclicBarrier is a synchronization class that allows a set of threads to wait for each other when reaching a common barrier
            The barrier is named cyclic because it can be reused after the waiting threads are released
            A CyclicBarrier instance can be created with a Runnable task that is run once per barrier point, after all threads arrive
            and before any threads are released. This task is also known as barrier action
            You can use one of these constructors to create a cyclic barrier :
                public CyclicBarrier(int parties, Runnable barrierAction)
                public CyclicBarrier(int parties)

            CyclicBarrier methods :
            * await : Waits until all parties have invoked await on the barrier. The barrier is tripped only if all the parties have called this method
                you can also specify a timeout. if the timeout is reached and there is a party that has not arrived, a timeout exception is thrown and the barrier is broken
                public int await() throws InterruptedException, BrokenBarrierException
                public int await(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException, TimeoutException
                    returns the arrival index of the current thread, where index getParties() - 1 indicates the first to arrive and zero indicates the last to arrive
            * getNumberWaiting : returns the number of parties currently waiting at the barrier
            * getParties : returns the number of parties required to trip to barrier
            * isBroken : checks if the barrier is in the broken state
            * reset : resets the barrier to its initial state

         */
        CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_PARTIES, () -> System.out.println("Threads released ..."));
        // This barrier is tripped (visited) is 3 threads have invoked its await method
        // The runnable action represents the barrier action. It will be triggered each time the barrier is tripped.

        // First party
        Runnable part1 = () -> {
            for(int i = 0; i < NUMBER_OF_PARTIES; i++){
                int position = -1;
                try {
                    position = barrier.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) { e.printStackTrace(); }
                // after releasing the threads all of them will access the console to write the thread name and the the round
                System.out.printf("Thread 1 - Arrival index : %d - Round %d%n",position, i);
            }
        };
        // Second party
        Runnable part2 = () -> {
            for(int i = 0; i < NUMBER_OF_PARTIES; i++){
                int position = -1;
                try {
                    Thread.sleep(1 * 1000); // change the 1 to 2 or a greater number to generate a timeout exception
                    position = barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) { e.printStackTrace(); }
                System.out.printf("Thread 2 - Arrival index : %d - Round %d%n",position, i);
            }
        };
        new Thread(part1).start();
        new Thread(part2).start();
    }
}
