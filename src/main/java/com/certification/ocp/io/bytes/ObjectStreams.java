package com.certification.ocp.io.bytes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.*;

public class ObjectStreams {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // An ObjectOutputStream writes primitive values and java objects to a OutputStream; those serialized values and objects
        // can be recovered using an ObjectInputStream via an InputStream
        // Only objects that support the Serializable can be written to and read back from streams

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                "src/main/java/com/certification/ocp/io/bytes/Student.bin"
        ));
        objectOutputStream.writeInt(1);  // object index
        objectOutputStream.writeUTF("First Student");    // title
        objectOutputStream.writeObject(new Student(1, "Steve"));
        objectOutputStream.writeBoolean(Boolean.TRUE);    // success
        // the content will be created without invoking the flush methods
        objectOutputStream.flush();
        // i added the flush here because of the last statement writeBoolean. It works without the flush for other types but not for this boolean

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(
                "src/main/java/com/certification/ocp/io/bytes/Student.bin"
        ));
        System.out.printf("index : %d%n", objectInputStream.readInt());
        System.out.printf("title : %s%n", objectInputStream.readUTF());
        Student student = (Student) objectInputStream.readObject();
        System.out.printf("student : %s%n", student);
        System.out.printf("success : %b%n", objectInputStream.readBoolean());

        objectInputStream.close();
        objectInputStream.close();
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Student implements Serializable{
        private int id;
        private String name;
    }
}
