package com.certification.ocp.io.characters;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {

    public static void main(String[] args) {

        try(FileWriter fileWriter = new FileWriter("src/main/java/com/certification/ocp/io/characters/FileWriterExample.txt", false)){
            // FileWriter is a convenience class to write character files
            // like file reader you can instantiate a file writer using a File or the file name. If the file does not exists in file system it will be created
            // you can also using the constructor with an append boolean. true means that you'll add a text to the existing content. false to start from the beginning
            // the constructor with one parameter will use false as append
            fileWriter.write("Thank you ");
            fileWriter.append("\n for watching us !!!");
            // you can add characters using write or append

            // if file writer has been declared in the try with resources so you don't need to close it
            // like other output stream in the java io the content will not be saved to the destination util you call the method flush() on the stream
            // this method will be called when the stream is closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
