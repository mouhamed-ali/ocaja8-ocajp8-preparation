package com.certification.ocp.concurent;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ThreadingProblems {

    public static void main(String[] args) {

        boolean enableDeadLock = false;
        boolean enableStarvation = false;
        boolean enableLiveLock = false;
        boolean raceConditionLock = true;

        // Deadlock example
        // Deadlock describes a situation where two or more threads are blocked forever
        // Deadlock may happen when multiple threads attempt to acquire the same locks at the same time, but in different order
        if(enableDeadLock) {
            DeadLockTask task1 = new DeadLockTask("Task 1");
            DeadLockTask task2 = new DeadLockTask("Task 2");
            new Thread(() -> task1.doStart(task2)).start();
            new Thread(() -> task2.doStart(task1)).start();
            // In this example, threads are blocked because each of them tries to obtain the lock by the other thread. This situation
            // is called a deadlock
        }

        // Starvation example
        // Starvation describes a situation where some threads are unable to gain access to a shared resource while it is occupied by other threads
        // Starvation happens also when a greedy thread gains exclusive access to a resource for a long period of time, leaving other threads no chance
        // to continue, or when a thread keeps failing to acquire a lock due to the competition of other threads
        if(enableStarvation){
            StarvationTask starvationTask = new StarvationTask();   // this is a shared resource like in the first case
            new Thread(() -> starvationTask.printThreadName(), "Task n°1").start();
            new Thread(() -> starvationTask.printThreadName(), "Task n°2").start();
        }

        // LiveLock example
        // Livelock describes a situation when multiple threads are busy responding to the actions of each other, these threads
        // keep executing particular statements, resulting in the program being unable to make progress
        if(enableLiveLock){
            LiveLockTask task1 = new LiveLockTask();
            LiveLockTask task2 = new LiveLockTask();
            new Thread(() -> task1.setOpp(task2)).start();
            try { Thread.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
            new Thread(() -> task2.setSame(task1)).start();
        }

        // Race conditions example
        // race conditions are situations where multiple threads access and change the same resources at the same time, resulting
        // in unpredictable outcomes
        if(raceConditionLock){
            RaceConditionTask raceConditionTask = new RaceConditionTask();
            new Thread(() -> raceConditionTask.increase()).start();
            new Thread(() -> raceConditionTask.increase()).start();
            // if we call the increase method in a sequential mode two times we get 200_000 but as wa are calling it in 2 different
            // threads we will get a number between 100_000 and 200_000, the reason is when the first thread gets and calculates the new value
            // the second reads the value before the first affects the new value to count variable. This scenario is a representation of a race condition
            // you can solve this issue by making the increase method synchronized
            try { Thread.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();} // this is used to make sure the print called after the treatment
            System.out.printf("The result is %d", raceConditionTask.getCount());
        }
    }

    @AllArgsConstructor
    public static class DeadLockTask {

        private String name;
        public synchronized void doStart(DeadLockTask task){

            System.out.printf("'%s' is starting ...%n", name);
            // the console line is mandatory to reproduce the issue otherwise the process will be fast
            task.doStop();
            // at this point, task1#doStart waits for the task2#doStart ending so he can call the doStart#doStop
            // the task2 also waits fo the task1#doStart ending to call task1#doStop
        }

        private synchronized void doStop() {
            System.out.printf("'%s' is stopped. %n", name);
        }
    }

    public static class StarvationTask {
        public synchronized void printThreadName() {
            int count = 1000;
            while (count > 0){
                try { Thread.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
                count--;
                System.out.printf("The current thread name is : %s%n", Thread.currentThread().getName());
            }
        }
    }

    public static class LiveLockTask {
        private volatile boolean counter;
        private boolean getCounter() {
            try { Thread.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
            System.out.printf("The current thread name is : %s, counter : %b%n", Thread.currentThread().getName(), this.counter);
            return counter;
        }
        public void setSame(LiveLockTask task){
            while (counter!=task.getCounter()) this.counter = !this.counter;
        }
        public void setOpp(LiveLockTask task){
            while (counter==task.getCounter()) this.counter = !this.counter;
        }
    }

    @Getter
    public static class RaceConditionTask{
        private int count;
        public void increase(){
            for(int i = 0; i < 100_000; i++) count++;
        }

        public int getCount() {
            return count;
        }
    }
}
