package com.certification.ocp.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamApiNio {

    public static void main(String[] args) throws IOException {

        // public static Stream<String> lines(Path path) throws IOException , the default charset is UTF-8
        // public static Stream<String> lines(Path path, Charset cs) throws IOException
        // unlike the readAllLines which reads all the lines into a list, this one populates the lines into a stream lazily
        Stream<String> lines = Files.lines(Paths.get("src/main/java/com/certification/ocp/nio/readme.md"), Charset.forName("ISO-8859-1"));
        lines.forEach(System.out::println);
        lines.close();  // it's recommended to close IO streams , https://mkyong.com/java/java-8-should-we-close-the-stream-after-use/
        // using the UTF-8-Charset, look at the first line
        System.out.println(
                Files.lines(Paths.get("src/main/java/com/certification/ocp/nio/readme.md")).findFirst().get()
        );
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // public static Stream<Path> list(Path dir) throws IOException
        // returns a lazily populated stream whose elements are entries of the given directory. The search is no recursive
        Files.list(Paths.get(".")).forEach(System.out::println);
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) throws IOException
        // public static Stream<Path> walk(Path start, FileVisitOption... options) throws IOException, this method calls the above using maxDepth=Integer.MAX_VALUE
        // The walk methods return a Stream that is lazily populated with Path instances by walking the file tree rooted at the given starting file
        // by default the walk method doesn't follow symbolic links. to follow them use the unique constant FOLLOW_LINKS of FileVisitOption enum
        // walk starts by visiting the starting file (root), then the its basic files. If the basic file is a directory, entries in the directory and its descendant will be followed
        // a maxDepth of zero means only the starting file will be visited
        Files.walk(Paths.get("./target"), 1).forEach(System.out::println);
        System.out.printf(" ---------------------------------- %n");
        Files.walk(Paths.get("./target/maven-status"), 4).forEach(System.out::println);
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        // public static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException
        // the find method returns a Stream that is lazily populated with Path instances by searching for file in a file tree rooted at a given starting file
        // this method works exactly like the walk method, the difference is that it filters entries by applying the biPredicate on each entry
        // the biPredicate takes the Path object and its file attributes as parameters
        Files.find(Paths.get("./target"),
                Integer.MAX_VALUE,
                (path, attrs) -> path.getFileName().toString().endsWith(".properties") && attrs.isRegularFile()
        ).forEach(System.out::println);
    }
}
