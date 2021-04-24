package com.certification.ocp.io.characters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderExample {

    public static void main(String[] args) throws IOException {

        // a BufferedReader reads text from a character-input stream, buffering characters to provide efficient reading of characters, arrays and lines
        // you can use either : BufferedReader(Reader reader) ; BufferedReader(Reader reader, int size)

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // you can create a stream from the system input, this code transforms the system input bytes to characters
        String userInput = bufferedReader.readLine();
        System.out.println(userInput);
        bufferedReader.close();

        System.out.println(" --------------------------------------");

        BufferedReader fileReader = new BufferedReader(new FileReader("src/main/java/com/certification/ocp/io/characters/FileReaderExample.txt"));
        fileReader.lines().forEach(System.out::println);
        fileReader.close();
    }
}
