package com.certification.ocp.concurent;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class RunnableCallableExecutorService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Runnable is a functional interface, designed to be implemented by classes whose instances are intended to be executed by another thread
        // The runnable interface defines a single abstract method, named run which takes no arguments and returns no value
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.printf("### %30s -- The current thread name in which i'm executed is : %s%n", "Thread", Thread.currentThread().getName());
            }
        };
        new Thread(runnable).start();
        System.out.printf("The main thread name is : %s%n", Thread.currentThread().getName());
        System.out.printf(" ---------------------------------- %n");

        // Callable is a functional interface, designed to be implemented by classes whose instances are intended to be executed by another thread
        // The callable interface defines a single abstract method, named call, which takes no arguments, returns a value and may throw a checked exception
        // public interface Callable<V> { V call() throws Exception; }
        // Callable are used by executor service interface

        // The executor interface represents objects that execute submitted Runnable tasks, providing a way of decoupling task submission from task execution
        // ExecutorService is a sub-interface of Executor, declaring additional methods to execute Callable tasks and methods to shut down the executor

        ExecutorService service1 = Executors.newFixedThreadPool(4);
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        // execute tasks
        // An ExecutorService must be shut down when it is no longer needed to avoid threads keeping running
        service1.shutdown();    // previously submitted tasks will executed and no new tasks will be accepted
        service2.shutdownNow(); // stop all actively executed tasks and terminating the processing of waiting tasks

        // submit a runnable task
        ExecutorService singleTread = Executors.newSingleThreadExecutor();
        Runnable runnable2 = () -> System.out.printf("### %30s -- The current thread name in which i'm executed is : %s%n", "SingleThreadExecutor", Thread.currentThread().getName());
        // Future<?> submit(Runnable task);
        singleTread.submit(runnable2);
        singleTread.shutdown();

        singleTread = Executors.newSingleThreadExecutor();
        Future<?> futureTask = singleTread.submit(runnable2);   // even if we pass a runnable, the submit and get methods returns a result and not a void. The generic type will be object here
        singleTread.shutdown();                                 // the shutdown method initiates the shutdown but it doesn't block the main thread
        Serializable taskResult = (Serializable) futureTask.get();
        // we've used a runnable so the process does not return a value (run method of runnable) but the get method must return one which is null that can be hold by an Object
        // if you choose Object class , you can omit the cast to Serializable but if you choose a type like String, the cast is mandatory
        System.out.printf("The result of the last computation is %d%n", taskResult);
        System.out.printf(" ---------------------------------- %n");

        // submit a callable task
        singleTread = Executors.newSingleThreadExecutor();
        // <T> Future<T> submit(Callable<T> task);
        Future<Integer> resultFuture = singleTread.submit(() -> {
            System.out.printf("### %30s -- The current thread name in which i'm executed is : %s%n", "Future#SingleThreadExecutor", Thread.currentThread().getName());
            return Integer.MAX_VALUE;
        });
        // V get() throws InterruptedException, ExecutionException;
        Integer result = resultFuture.get(); // the main thread is blocked while this method waits to the asynchronous computation result
        System.out.printf("The result of the computation is %d%n", result);
        singleTread.shutdown();
        System.out.printf(" ---------------------------------- %n");

        ExecutorService executor = Executors.newCachedThreadPool();
        // newCachedThreadPool : Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
        // These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks. Calls to execute will reuse previously
        // constructed threads if available. If no existing thread is available, a new thread will be created and added to the pool.
        // Threads that have not been used for sixty seconds are terminated and removed from the cache (from the calls docs)

        Callable<String> task1 = () -> "Task 1";
        Callable<String> task2 = () -> "Task 2";
        Collection<Callable<String>> tasks = Arrays.asList(task1, task2);
        String resultTask = executor.invokeAny(tasks);
        // by calling invokeAny method, two submitted tasks may be executed by two different working threads. when one of those tasks completes, no matter task1 or task2, the result
        // of that task is returned and the other task will be canceled. So the value of resultTask is not determine, it can be Task 1 or Task 2
        System.out.printf("The result of the computation tasks is '%s'%n", resultTask);
        executor.shutdown();
        System.out.printf(" ---------------------------------- %n");

        List<Future<String>> futures = Executors.newCachedThreadPool().invokeAll(tasks);
        // the invokeAll method executes a collection of tasks, returning a list of Future instances that hold the result of those tasks when they all complete
        executor.shutdown();
        for (Future<String> future : futures) {
            System.out.println(System.out.printf("The result of the computation all tasks is '%s'%n", future.get()));
        }

        // PN : elements of the futures list from an invokeAll method is guaranteed to be in the same order as the submitted tasks
        // PN : invokeAll and invokeAny can only work on callable tasks and not runnable tasks
    }
}
