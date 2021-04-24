package com.certification.ocp.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class FilesClassExamples {

    public static void main(String[] args) throws IOException {

        // the Files class defines static methods that operate on Path instances
        // exists Method : public static boolean exists(Path path, LinkOption... options)
        // notExists Method : public static boolean notExists(Path path, LinkOption... options)
        // checks whether the file exists in the file system or not. the link options indicated how symbolic links are handled in case the file is a symbolic link
        // by default symbolic links are followed. the LinkOption enum type has only one constant named NOFOLLOW_LINKS. if an instance of this type is specified
        // symbolic links will not be followed
        Path path = Paths.get("src");
        System.out.printf("Does the src file exists : %s%n", Files.exists(path));
        System.out.printf("Does the src file not exists : %s%n", Files.notExists(path, LinkOption.NOFOLLOW_LINKS));
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        List<String> allLines = Files.readAllLines(Paths.get("src/main/java/com/certification/ocp/nio/readme.md"));
        // the default encoding will be UTF-8, you can specify another if you want like below
        allLines = Files.readAllLines(Paths.get("src/main/java/com/certification/ocp/nio/readme.md"), Charset.forName("ISO-8859-1"));
        System.out.println(allLines);
        // to get the same as bytes
        byte[] content = Files.readAllBytes(Paths.get("src/main/java/com/certification/ocp/nio/readme.md"));
        // These methods gets all the file content and ensures that the file is closed after processing it
        // These methods are suitable for small files as it gets the content per once and not suitable for large files
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        Path notExistPath = Paths.get("/home/images/image.jpg");
        if(Files.exists(notExistPath)){
            // public static void delete(Path path) throws IOException
            Files.delete(notExistPath);
        }else{
            System.out.println("File not exists ");
        }
        // public static boolean deleteIfExists(Path path) throws IOException
        boolean statusDeletion = Files.deleteIfExists(notExistPath);
        System.out.printf("Deletion file status is : %b%n", statusDeletion);
        // difference between delete and deleteIfExists is that delete throw NoSuchFileException if the file does not exists
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // you can copy files using these methods
        // public static Path copy(Path source, Path target, CopyOption... options)
        // public static long copy(Path source, OutputStream out) throws IOException
        // public static long copy(InputStream in, Path target, CopyOption... options)
        // when copying from file to file like the first method all copy options are valid except for the atomic move
        // when copying from input stream to a file the only supported option is replace existing
        Path copied = Paths.get("src/main/java/com/certification/ocp/nio/copied.md");
        Files.deleteIfExists(copied);
        Files.copy(Paths.get("src/main/java/com/certification/ocp/nio/readme.md"), copied, StandardCopyOption.COPY_ATTRIBUTES);
        Files.copy(copied, System.out);
        //Files.copy(System.in, copied, StandardCopyOption.REPLACE_EXISTING);
        // the line above will listen to your standard input and write all what you type into copied. stop the process to end it
        System.out.printf("%n ---------------------------------- $$$$$ ---------------------------------- %n");

        // public static Path move(Path source, Path target, CopyOption... options)
        // the move method moves files and directories. It supports two options atomic move and replace existing. If the atomic move
        // is specified the replace existing will be ignored even if it has been specified
        Path moved = Paths.get("src/main/java/com/certification/ocp/nio/moved.md");
        // public static Path move(Path source, Path target, CopyOption... options) throws IOException
        Files.move(copied, moved , StandardCopyOption.REPLACE_EXISTING);
        // if you specify StandardCopyOption.COPY_ATTRIBUTES as an option you will throw an Unsupported operation exception
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // The FileAttributes interface represents a view of attributes associated with a file
        // The most important subinterface of FileAttributeView is BasicFileAttributeView providing a view of a basic set of file attributes common to many file systems
        // Attributes supported by BasiFileAttributeView are corresponding to methods of the BasicFileAttributes interface

        // public static Object getAttribute(Path path, String attribute, LinkOption... options)

        Boolean isDir = (Boolean) Files.getAttribute(Paths.get("src"), "basic:isDirectory");
        // The attribute parameter indicated the parameter to be read and takes the form [view-name:]attribute-name if the view name is not specified
        // it will be considered as the basic view
        System.out.printf("The file is a directory : %s%n", isDir);
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // the getFileAttributeView and readAttributes methods read attributes of a file as a bulk (volume, masse) operation :
        // public static <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options)
        // public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options)
        // public static Map<String,Object> readAttributes(Path path, String attributes, LinkOption... options)
        BasicFileAttributeView attributeView = Files.getFileAttributeView(moved, BasicFileAttributeView.class);
        BasicFileAttributes attributes1 = attributeView.readAttributes();

        BasicFileAttributes attributes2 = Files.readAttributes(moved, BasicFileAttributes.class);

        Map<String,Object> map = Files.readAttributes(moved, "basic:*");

        System.out.printf("Size : %s , %s , %s%n", attributes1.size(), attributes2.size(), map.get("size"));
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // The setAttribute method sets a value for a file attribute; the attribute parameter identifies the attribute to be set
        // and takes the form [view-name:]attribute-name
        // public static Path setAttribute(Path path, String attribute, Object value, LinkOption... options)
        System.out.printf("The last modified time before : %s%n", attributes2.lastModifiedTime());
        Files.setAttribute(moved, "lastModifiedTime", FileTime.from(Instant.parse("2020-02-02T00:00:00.00Z")));
        System.out.printf("The last modified time after  : %s%n", Files.getLastModifiedTime(moved));
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        System.out.printf("Is the moved file hidden : %s%n", Files.isHidden(moved));
        System.out.printf("Is the moved file hidden : %s%n", Files.getAttribute(moved, "dos:hidden"));
        System.out.printf("Is the moved file hidden : %s%n", Files.getAttribute(moved, "hidden"));
        // the above code throws an exception be because the hidden attribute exists only on the dos view and not in the basic
    }
}
