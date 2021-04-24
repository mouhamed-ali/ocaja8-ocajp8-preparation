package com.certification.ocp.io.characters;

import java.io.Console;

public class ConsoleExample {

    public static void main(String[] args) {

        // The console class represents the unique character-based console device which is associated with the current java virtual machine
        // Whether the virtual machine has a console depends on the underlying platform and on the manner in which it is launched
        // If the vm has been launched in an interactive way, a console will be available
        // If the vm has been launched by a background job for example or an IDE, the console will not be available

        Console console = System.console();
        if (console == null) {
            System.err.print("You platform does not support the vm console");
            System.exit(99);
        }

        System.out.print("Enter your message here : ");
        String input = console.readLine();
        System.out.printf("You message is : %s%n", input);
        /*
            You can compile and run this class from the terminal instead of intellij :
            Go to the parent directory : src/main/java and run these commands
            javac com/certification/ocp/io/characters/ConsoleExample.java
            java com.certification.ocp.io.characters.ConsoleExample
         */
        System.out.printf(" ---------------------------------- %n");
        input = console.readLine("Enter your %s here : ","message");
        // Console has a lot of useful methods like this overloaded method of read-line
        System.out.printf("You message is : %s%n", input);

        System.out.printf(" ---------------------------------- %n");
        // and to read passwords you can use this
        char[] password = console.readPassword("Enter your %s : ","password");
        System.out.println(password);
    }
}
