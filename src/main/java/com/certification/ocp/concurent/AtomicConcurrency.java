package com.certification.ocp.concurent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class AtomicConcurrency {

    public static void main(String[] args) {

        // the java.util.concurrent.atomic package contains classes support lock free and thread safe operations on single variables
        // most popular classes are, AtomicBoolean, AtomicInteger, AtomicLong and AtomicReference
        AtomicBoolean atomicBoolean = new AtomicBoolean();  // default value is false
        AtomicInteger atomicInteger = new AtomicInteger();  // default value is 0
        AtomicLong atomicLong = new AtomicLong(1L);// value is 1
        AtomicReference<String> atomicReference = new AtomicReference<>("Java");    // default value is null

        // the value enclosed in an atomic instance can be retrieved using the get method
        int atomicInt = atomicInteger.get();    String atomicString = atomicReference.get();

        // Atomic classes define a method supporting conditional update operations, named compareAndSet. This method sets a variable
        // to a new value if it currently holds the expected value
        atomicReference.compareAndSet("Vue", "VueJS");  // does nothing as the current value is Java
        atomicReference.compareAndSet("Java", "Java8");  // changes java to java8
        System.out.printf("The current java version is : %s%n", atomicReference.get());
        System.out.printf(" ---------------------------------- %n");

        // Atomic classes defines unconditionally methods to set a new value using set and getAndSet
        atomicBoolean.set(Boolean.TRUE);    // atomically set the value
        System.out.printf("The current boolean value is : %b%n", atomicBoolean.get());
        Boolean booleanValue = atomicBoolean.getAndSet(Boolean.FALSE);  // atomically set the value
        System.out.printf("The old boolean value is %b and the new one is %b%n", booleanValue, atomicBoolean.get());
        System.out.printf(" ---------------------------------- %n");

        System.out.printf("The old integer value is %d%n", atomicInteger.get());
        // AtomicInteger and AtomicLong defines these methods to update the numeric value
        atomicInteger.addAndGet(5); // adds atomically 5 and then returns the new value
        atomicInteger.getAndAdd(2); // gets the data and then adds 2 atomically
        atomicInteger.incrementAndGet();
        atomicInteger.getAndIncrement();
        atomicInteger.decrementAndGet();
        atomicInteger.getAndDecrement();
        System.out.printf("The new integer value is %d%n", atomicInteger.get());
        System.out.printf(" ---------------------------------- %n");

        // all of the atomic classes implements these methods except for atomicBoolean
        System.out.printf("The old long value is %d%n", atomicLong.get());
        // updateAndGet , getAndUpdate takes an UnaryOperator
        atomicLong.updateAndGet(i -> i * 5);  // update the value by applying the function
        atomicLong.getAndUpdate(i -> i - 3);
        System.out.printf("The long value after applying the update function is %d%n", atomicLong.get());
        // accumulateAndGet , getAndAccumulate takes a BinaryOperator
        atomicLong.accumulateAndGet(7, (i1,i2) -> i1 - i2);
        System.out.printf("The long value after applying the first accumulate function is %d%n", atomicLong.get());
        atomicLong.accumulateAndGet(10, (i1,i2) -> i1 + i2);
        System.out.printf("The long value after applying the second accumulate function is %d%n", atomicLong.get());
        System.out.printf(" ---------------------------------- %n");

        System.out.printf("The old string value is %s%n", atomicReference.get());
        atomicReference.getAndUpdate(String::toUpperCase);
        atomicReference.accumulateAndGet(".2.1", String::concat);
        System.out.printf("The long value after applying the second accumulate function is %s%n", atomicReference.get());

    }

    public static class IntrinsicClass{
        // Intrinsic locks, also called monitors, are internal entities associated with objects
        // Every object has exactly one intrinsic lock (used implicitly when using synchronized on a method)
        private int number1;
        private int number2;
        // the fields modification must be atomic here, if we use synchronized on methods and if one of the fields is accessed
        // the access to the other field will be forbidden. instead of using the default monitor used by synchronized you can use you own
        private Object monitor1 = new Object();
        private Object monitor2 = new Object();
        public void increaseNumber1(){
            synchronized (monitor1){number1++;}
        }
        public void decreaseNumber2(){
            synchronized (monitor2){number2--;}
        }
    }
}
