package com.certification.ocp.io.characters;

import java.util.Locale;
import java.util.Scanner;

public class ScannerExample {

    public static void main(String[] args) {

        // Java api enables you to access standard input and output stream of your platform via static fields of the java.lang.System class. fields are in, out and err
        // System.in  : InputStream represents standard input stream
        // System.out : PrintStream represents standard output stream
        // System.err : PrintStream represents standard error stream

        // a Scanner instance can be created to scan data from the standard input stream
        Scanner scanner = new Scanner(System.in);
        String input;
        StringBuilder output = new StringBuilder();
        while (true){
            System.out.print("Enter your message here : ");
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit"))
                break;
            output.append(" ").append(input);
        }
        System.out.printf("You message is : %s%n", output.toString());    // prints to the standard output
        System.out.format("You message is : %s%n", output.toString());    // prints to the standard output using format method
        System.err.format("You message is : %s%n", output.toString());    // prints to the standard error using format method
        scanner.close();

        // Standard Stream vs Console Object (check the console example)
        // Standard stream are byte based while console is character based that's why you need to convert the bytes to characters
        // when using Standard stream (here we use an intermediate class which is scanner)
        // Standard streams are always existent while a console instance might or might not be depending on the platform and how the vm is launched
    }
}
