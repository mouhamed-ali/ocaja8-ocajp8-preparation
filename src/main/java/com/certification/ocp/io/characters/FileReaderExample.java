package com.certification.ocp.io.characters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileReaderExample {

    public static void main(String[] args) {

        File file = new File("src/main/java/com/certification/ocp/io/characters/FileReaderExample.txt");
        // A file instance is a representation of a file or directory pathname. relative path will be build starting from the directory
        // in which the virtual machine has been launched which is D:/github/oca-ocp-demos in our case

        // get the default jvm encoding
        System.out.printf("Default JVM Encoding : %s%n", System.getProperty("file.encoding"));
        // Getting character encoding by java.nio.charset
        System.out.printf("Default Nio Encoding : %s%n", Charset.defaultCharset());

        try(FileReader fileReader = new FileReader(file)){
            // FileReader is a convenience class for reading character files. It uses the default character encoding (jvm) to read files

            System.out.printf("Default FileReader Encoding : %s%n", fileReader.getEncoding());
            // default file reader encoding
            System.out.printf(" ---------------------------------- %n");

            char[] buffer = new char[23];
            fileReader.read(buffer);    // Reads characters into an array.  This method will block until some input
            System.out.println(buffer); // the buffer must be larger than the content of the file otherwise you cannot get all lines

            System.out.printf(" ---------------------------------- %n");
            int character = fileReader.read();  // Reads a single character.  This method will block until a character is
            // if the file is empty read returns -1
            System.out.printf("integer is %d and its char is %c%n", character, (char) character);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
