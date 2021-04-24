package com.certification.ocp.io.characters;

import java.io.*;

public class BufferedWriterExample {

    public static void main(String[] args) throws IOException {

        // a BufferedWriter writes text to a character-input stream, buffering characters to provide efficient writing of characters, arrays and lines
        // you can use either : BufferedWriter(Writer writer) ; BufferedWriter(Writer writer, int size)

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/com/certification/ocp/io/characters/BufferedWriterExample.txt"));
        bufferedWriter.write("Welcome to this ");
        bufferedWriter.newLine();
        bufferedWriter.write(new char[]{'t', 'u', 't', 'o', 'r', 'i', 'a', 'l'});
        bufferedWriter.close();
    }
}
