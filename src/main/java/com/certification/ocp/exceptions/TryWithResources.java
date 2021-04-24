package com.certification.ocp.exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TryWithResources {

    public static void main(String[] args) {

        System.out.printf(" --------------------------------------------------------------------\t FIRST PART \t---------------------------------------------------------- %n%n");
        try {
            firstPart();
        }catch (Exception e){
            System.out.printf("## Main Method CATCH : catch the exception %s%n", e.toString());
        }

        System.out.printf(" --------------------------------------------------------------------\t SECOND PART \t---------------------------------------------------------- %n%n");
        try {
            secondPart();
        }catch (Exception e){
            System.out.printf("## Main Method CATCH : catch the exception %s%n", e.toString());
        }
    }

    private static void firstPart() {
        // an ordinary try catch
        try{
            new FileInputStream("D:/file_not_found.txt");
        }catch (RuntimeException | FileNotFoundException e){
            // in an ordinary try-catch block the try is mandatory + a catch block or finally block or both (try block only causes a compilation failure)
            // RuntimeException and FileNotFoundException must be unrelated (they do not have the same hierarchy)
            // e is implicitly final here which means it cannot have another value (it will be deducted at runtime after throwing the exception)
            System.out.printf("Exception occurred %s%n",e.toString());
        }finally {
            System.out.println("finally block called");
        }
        System.out.printf(" ---------------------------------- %n");

        // try with resource
        // the person instance must be declared inside the try with resource not outside, otherwise you'll have a compilation error
        try(Person person = new Person("Kris")) {
            // try with resource can be done with only a try block
            // if the close method of Person throws a exception, it must be handled in a catch block here
            System.out.println(person);
        }
        System.out.printf(" ---------------------------------- %n");

        // try with resource
        try(Person person1 = new Person("Person-1");Person person2 = new Person("Person-2")) {
            // resources declared in try with resources will be closed in the opposite order of their creation
            // catch and finally blocks, if any, are executed after the declared resources are closed
            throw new ArithmeticException("Cannot divide by 0");
        }catch (RuntimeException e){
            System.out.printf("RuntimeException occurred %s%n",e.toString());
            throw e;
        }finally {
            System.out.println("finally block called");
        }
    }

    @AllArgsConstructor
    @ToString
    public static class Person implements AutoCloseable{
        String name;
        @Override
        public void close() {
            // the close method from autocloseable throws an exception but we will omit it here
            System.out.printf("close method has been called on Person %s%n", this.name);
        }
    }


    private static void secondPart() {

        // If exceptions are thrown from both try block and during the closing of resources. the one from catch block will be thrown while the exception
        // caused by resources closing is suppressed
        // an exception thrown from an implicit operation is less important from the one thrown by an explicit operation
        // try with resource
        try(Book book = new Book()){

            throw new Exception("Generated Exception");
        } catch (ReflectiveOperationException e) {
            System.out.printf("Catch the exception %s%n", e.toString());
        } catch (Exception e) {
            System.out.printf("Catch the exception %s%n", e.toString());
        }
        // try block , in an ordinary try block, the statement throws the exception (closing resources) in finally block while
        // the exception from the try block will be suppressed; make lock to true to see the exception
        // an exception thrown from an implicit operation is less important from the one thrown by an explicit operation
        boolean lock = true;
        if(lock)
            try{
                Book bookExample = new Book();
                throw new ArithmeticException("Cannot divide by 0");
            } finally {
                throw new NullPointerException("instance reference is null");
            }
        if(!lock)
            // try with resource ; same example (first behavior) using a runtime exception; make lock to false to see the exception
            try(Book book = new Book()){

                throw new ArithmeticException("Cannot divide by 0");
            } catch (ReflectiveOperationException e) {
                System.out.printf("Catch the exception %s%n", e.toString());
            }
    }

    public static class Book implements AutoCloseable{

        @Override
        public void close() throws ReflectiveOperationException {
            //  Exception -> ReflectiveOperationException -> ClassNotFoundException
            throw new ClassNotFoundException("Manually Book class exception");
        }
    }
}
