package com.certification.ocp.concurent;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ForkJoinExample {

    public static void main(String[] args) {

        /*
            - The Fork/Join framework is designed for work that can be broken into smaller pieces recursively, allowing you to take advantage
            of multiple processors
            - The Fork/Join framework distributes tasks to worker threads in a thread pool, which employs a work-stealing algorithm. work-stealing means
            worker threads running out of jobs can steal tasks from busy threads.
            - The main component of the Fork/Join framework is the ForkJoinPool class, representing thread pools and executing ForkJoinTask processes
            - ForkJoinPool is an implementation of the ExecutorService. The main difference between ForkJoinPool and other implementations is the use of work-stealing algorithm
            - a ForkJoinPool instance can be created using a static factory method or a constructor :
                public static ForkJoinPool commonPool()
                    returns the common pool instance, the default thread pool for a ForkJoinTask. This may help to reduce the resources usage.
                public ForkJoinPool()
                    created a ForkJoinPool with parallelism level equal to the number of processors available to the java virtual machine
                public ForkJoinPool(int parallelism)
                    Creates a ForkJoinPool with the specified parallelism level
            - The most important class of the ForkJoinPoll class is the invoke method. This method performs the given task and returns the result upon completion :
                public <T> T invoke(ForkJoinTask<T> task)

            - ForkJoinTask is an abstract base class representing tasks running within a ForkJoinPool
            - Tasks are usually submitted and retrieved via the fork and join methods, or their derivatives invoke and invokeAll :
                public final ForkJoinTask<V> fork() :   Asynchronously executes this task in the pool
                public final V join() : Returns the result of the computation when it is done
                public final V invoke() : Performs this task, awaits its completion and return its result
                public static void invokeAll(ForkJoinTask<?>... tasks) : Performs all the given tasks, the awaits their completion

             - Two subclasses of ForkJoinTask, namely RecursiveAction and RecursiveTask classes are usually extended to indicate tasks
             - RecursiveAction is used for computations that do not return and RecursiveTask used for computations that returns a value
             - The only abstract method that must be implemented by a subclass of RecursiveAction and RecursiveTask is compute
                    RecursiveAction : protected abstract void compute()
                    RecursiveTask   : protected abstract V compute()
             - You should adhere to this algorithm inside the compute method to implement the fork/join framework :
                if ( the given work is small enough )
                    do the work directly
                else
                    split the work into multiple pieces
                    invoke those pieces and wait for the results
         */
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new UnicodeAction("ABCD"));

        System.out.printf("%n ----------------------------- %n");

        List<Integer> numbers = new ArrayList<>();
        IntStream.rangeClosed(0,1000).forEach(i -> numbers.add(i));
        System.out.printf("The sum that we should have is\t : %d%n", IntStream.rangeClosed(0,1000).sum());

        ForkJoinPool pool2 = new ForkJoinPool();
        int result = pool2.invoke(new SumTask(numbers));
        System.out.printf("The result of computation is\t : %d%n", result);
    }

    @AllArgsConstructor
    public static class UnicodeAction extends RecursiveAction {
        private String word;
        @Override
        protected void compute() {
            if(word.length() <= 1){
                System.out.printf("Char : %s , Unicode : %s , - Printed by [%s]%n", word, word.codePointAt(0), Thread.currentThread().getName());
                return;
            }
            UnicodeAction action1 = new UnicodeAction(word.substring(0, word.length()/2));
            UnicodeAction action2 = new UnicodeAction(word.substring(word.length()/2));
            ForkJoinTask.invokeAll(action1, action2);
            //action1.invoke();
            //action2.invoke();
            /*
                the invoke method performs this task then wait for its completion. implying this method is a blocking operation
                action2 will be invoked after the action1 completes. Therefore no more than one worker thread executes this task at the time
             */

        }
    }

    @AllArgsConstructor
    public static class SumTask extends RecursiveTask<Integer>{
        private List<Integer> numbers;
        @Override
        protected Integer compute() {
            if(numbers.size() <= 100){
                System.out.printf("Compute range [%d,%d] , - Printed by [%s]%n", numbers.get(0), numbers.get(numbers.size()-1), Thread.currentThread().getName());
                return numbers.stream().mapToInt(i -> i).sum();
            }
            SumTask task1 = new SumTask(numbers.subList(0, numbers.size()/2));
            SumTask task2 = new SumTask(numbers.subList(numbers.size()/2, numbers.size()));
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
            /*
                task1.fork();
                return task1.join() + task2.compute();
                If you use this bloc of code you'll have the same result but the process will be sequential instead of parallel
                The task1 will be forked then joined before the task2 is computed. task1 must complete before the task2 making the operation sequential
                You'll have the same behavior if you use this instruction :
                return task1.compute() + task2.compute(); after the task1 completion the task2 will be invoked
             */
        }
    }
}
