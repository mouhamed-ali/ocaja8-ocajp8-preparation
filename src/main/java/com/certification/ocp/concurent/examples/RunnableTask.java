package com.certification.ocp.concurent.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RunnableTask extends Thread {

    CyclicBarrier cyclicBarrier;
    RunnableTask(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.printf("%s , Parties : %d , Number of threads currently blocked in await() : %d %n",
                    Thread.currentThread().getName(),cyclicBarrier.getParties(), cyclicBarrier.getNumberWaiting());
            /*
             * when  3 parties will call await() method (i.e. common barrier point)
             * CyclicBarrrierEvent will be triggered and all waiting threads will be released.
             */
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("As "+cyclicBarrier.getParties()+ " threads have reached common barrier point "
                + Thread.currentThread().getName() +
                " has been released");
    }

    public static void main(String... args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3 , () -> System.out.printf("%n ** Execute : as 3 " +
                "threads have reached common barrier point this method will be triggered %n%n"));

        RunnableTask runnableTask=new RunnableTask(cyclicBarrier);

        new Thread(runnableTask,"Thread-1").start();
        Thread.sleep(1000);
        new Thread(runnableTask,"Thread-2").start();
        Thread.sleep(1000);
        new Thread(runnableTask,"Thread-3").start();

        Thread.sleep(1000);
        new Thread(runnableTask,"Thread-4").start();
        //Thread.sleep(1000);
        //new Thread(runnableTask,"Thread-5").start();
        //Thread.sleep(1000);
        //new Thread(runnableTask,"Thread-6").start();
    }
}
