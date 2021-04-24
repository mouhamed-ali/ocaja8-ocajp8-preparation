package com.certification.ocp.exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Closeable;
import java.io.IOException;

public class CreateAutoCloseableException {

    public static void main(String[] args) {

        try(Person person = new Person()){

        } catch (Exception e) {
            // if you delete the Exception declaration from the close method, this block will be optional
            e.printStackTrace();
        }

        try(Book book = new Book()) {

        } catch (IOException e) {
            // if you delete the IOException declaration from the close method, this block will be optional
            e.printStackTrace();
        }

        // To create a resource you have to implement the AutoCloseable of the Closeable and override the close method
        // Closeable is a sub-interface from the AutoCloseable interface
        // close method from Closeable interface throws an IOException
        // close method from AutoCloseable interface throws an Exception
    }

    @AllArgsConstructor
    @ToString
    public static class Person implements AutoCloseable{
        @Override
        public void close() throws Exception {
        }
    }

    @AllArgsConstructor
    @ToString
    public static class Book implements Closeable {
        @Override
        public void close() throws IOException {
        }
    }
}
