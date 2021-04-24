package com.certification.ocp.io.bytes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileOutputStreamExample {

    public static void main(String[] args) throws IOException {

        // FileInputStream is a convenience class for writing binary files like images
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/certification/ocp/io/characters/FileOutputStreamExample.bin", true);
        // you can also pass a File instance as parameter and a boolean append as a second parameter
        // a file will be created if the file does not exist
        // true boolean at the end means that the data must be added to the end of the file

        byte[] buffer = getNowAsBytes();
        // data must be converted to its binary form before using it
        fileOutputStream.write(buffer);
        fileOutputStream.flush();

        buffer = getNowAsBytes();
        fileOutputStream.write(buffer, 4, 10);  // writes the buffer starting from offset 4 to position 4+10=14
        fileOutputStream.close();
    }

    private static byte[] getNowAsBytes() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).concat("\n").getBytes();
    }
}
