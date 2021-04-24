package com.certification.ocp.concurent.examples;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTask2 extends Thread {

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            Thread.sleep(1000);
            System.out.printf("%s , Parties : %d , Number of threads currently blocked in await() : %d %n",
                    Thread.currentThread().getName(),cyclicBarrier.getParties(), cyclicBarrier.getNumberWaiting());
            /*
             * when  3 parties will call await() method (i.e. common barrier point)
             * CyclicBarrrierEvent will be triggered and all waiting threads will be released.
             */
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("As "+cyclicBarrier.getParties()+ " threads have reached common barrier point "
                + Thread.currentThread().getName() +
                " has been released");
    }

    public static void main(String... args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3 , () -> System.out.printf("%n ** Execute : as 3 " +
                "threads have reached common barrier point this method will be triggered %n%n"));

        ExecutorService service = Executors.newFixedThreadPool(3);

        Runnable runnableTask = () -> await(cyclicBarrier);

        service.execute(runnableTask);
        Thread.sleep(1000);
        service.execute(runnableTask);
        Thread.sleep(1000);
        service.execute(runnableTask);

        System.out.println();
        Thread.sleep(1000);
        Callable<Integer> callableTask = () -> {await(cyclicBarrier); return 0;};
        service.invokeAll(Arrays.asList(callableTask,callableTask,callableTask));

        service.shutdown();
    }
}
