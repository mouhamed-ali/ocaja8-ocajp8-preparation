package com.certification.ocp.io.bytes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStreamExample {

    public static void main(String[] args) {

        // FileInputStream is a convenience class for reading binary files like images
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/com/certification/ocp/io/characters/mohamed.png")){
            // you can also pass a File instance as parameter

            byte[] buffer = new byte[200];  // the buffer must be larger than the file size
            fileInputStream.read(buffer);
            System.out.println(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
