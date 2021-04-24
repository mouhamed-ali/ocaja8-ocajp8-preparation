package com.certification.ocp.nio.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PathExample {

    public static void main(String... args) throws IOException {

        Path path = Paths.get("/opt/logs/java/logs.log");
        for(Path element : path)
            System.out.println(element);
        System.out.println(path.getNameCount());
        System.out.printf("------------------------------------- %n");


        Path p = Paths.get("../../CV");
        Files.walk(p)
                .map(z -> z.toAbsolutePath().toString())
                .filter(s -> s.endsWith(".doc"))
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.printf("------------------------------------- %n");

        Files.find(p,Integer.MAX_VALUE,
                (w,a) -> w.toAbsolutePath().toString().endsWith(".doc"))
                .collect(Collectors.toList()).forEach(System.out::println);

    }
}
