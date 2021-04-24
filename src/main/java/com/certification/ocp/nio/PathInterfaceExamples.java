package com.certification.ocp.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInterfaceExamples {

    public static void main(String[] args) {

        Path path = Paths.get("/opt", "apps", "tomcat/catalina");
        // the path will not check if the file exists in the file system it's up to the user to check that
        Path fileName = path.getFileName(); // returns the last component of the path
        System.out.printf("The file name is : %s%n", fileName);
        System.out.printf(" ---------------------------------- %n");

        Path parent = path.getParent(); // returns the parent path  or null if this path does not have a parent
        System.out.printf("The parent path is : %s%n", parent);
        System.out.printf("The parent path is : %s%n", Paths.get("dir").getParent());   // null
        System.out.printf("The parent path is : %s%n", Paths.get("D:/").getParent());   // null
        System.out.printf(" ---------------------------------- %n");

        Path root = path.getRoot(); // returns the root component or null if the path does not have a root component
        System.out.printf("The root path is : %s%n", root);
        System.out.printf("The parent path is : %s%n", Paths.get("dir").getRoot());   // null
        System.out.printf("The parent path is : %s%n", Paths.get("D:/").getRoot());   // not null
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        path = Paths.get("D:\\", "opt", "java", "bin");
        int count = path.getNameCount();    // returns the number of name elements in the path (the root is not countable)
        System.out.printf("The number of elements is : %d%n", count);

        Path name = path.getName(1);
        // returns a name element of this path at the specified index. the index of the closest element to the root is 0
        System.out.printf("The element index 1 is : %s%n", name);

        Path subpath = path.subpath(0,2);
        // returns a relative path that is a subsequence of this path. the index of the closest element to the root is 0
        System.out.printf("The subpath is : %s%n", subpath);
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        Path absPath = Paths.get("D:\\dev");
        Path relPath = Paths.get("music");
        System.out.printf("%s is absolute : %b%n", absPath, absPath.isAbsolute());  // checks whether this path is absolute
        System.out.println(absPath == absPath.toAbsolutePath());
        System.out.printf("%s is absolute : %b%n", relPath, relPath.isAbsolute());
        System.out.printf("%s to absolute : %s%n", relPath, relPath.toAbsolutePath());
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");

        Path origin = Paths.get("tomcat/../../oracle/./java");
        Path normalized = origin.normalize();
        // the normalized method returns a path that is this path with redundant name elements eliminated
        // tomcat and the double dot will be removed because its redundant, the dot also is always redundant
        System.out.printf("The normalized path is : %s%n", normalized);

        Path target = Paths.get("tomcat");
        System.out.printf("Relative path from java to tomcat is : %s%n", origin.relativize(target));
        // forget the normalized path for now, to go from the path tomcat/../../oracle/./java to tomcat you have to go back by 5 steps

        System.out.printf("Relative path from normalized java to java is : %s%n", normalized.relativize(target));   // TODO can't understand how it works
        // to call the relativize both of the paths must be absolute or relative. if on of them is absolute and the second is relative
        // an illegal argument exception will be generated
        System.out.printf(" ---------------------------------- $$$$$ ---------------------------------- %n");
        origin = Paths.get("oracle/java");
        target = Paths.get("ocpjp8");
        System.out.printf("The resolved path is : %s%n", origin.resolve(target));
        // The resolve method resolved the specified path against this path. if the specified path if absolute then this method returns
        // the current path otherwise it joins the specified path to this path
        // if an absolute path is passed to the resolve method such a path will be returned directly without any change
        System.out.printf("The resolved path is : %s%n", origin.resolve(Paths.get("D://ocpjp8")));
        System.out.printf("The resolved path is : %s%n", Paths.get("D://ocpjp8").resolve(origin));
        System.out.printf("The resolved path is : %s%n", Paths.get("D://ocpjp8").resolve(Paths.get("D://oracle")));

        // the resolveSibling resolves the specified path this path's parent. this methods rule is the same as the resolve method
        Path sibling = Paths.get("aws");
        System.out.printf("The resolved sibling path is : %s%n", origin.resolveSibling(sibling));

    }
}
