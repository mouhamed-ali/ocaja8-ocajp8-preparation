package com.certification.ocp.io.characters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterExample {

    public static void main(String[] args) throws IOException {

        // a PrintWriter prints formatted representations of objects to a text output stream
        // The destination of a PrintWriter instance can be a File , OutputStream or Writer
        // Methods of PrintWriter never throw IO exceptions

        PrintWriter writer = new PrintWriter(System.out);   // auto flushing will be false by default
        // the System.out is a print stream here's the declarations from the System class
        // public final static InputStream in = null;
        // public final static PrintStream out = null;

        writer.write("Welcome to this tutorial !!!");
        writer.close();

        writer = new PrintWriter(
                new FileWriter("src/main/java/com/certification/ocp/io/characters/PrintWriter.txt"), true
        );  // the auto flushing is enabled here
        writer.println("1");    // auto flushing will be applied to println, printf and format
        writer.printf("2%n");
        writer.format("3%n");
        writer.write("4");  // auto flushing will no be applied to write
        writer.close();         // to test this code make a debug point here and run the code you'll find that the 4 is not printed
        System.out.println("End execution");
    }
}
