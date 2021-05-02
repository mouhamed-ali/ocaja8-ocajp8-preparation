package com.certification.ocp.localization.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalizationUtils {

    /**
     * absolute path of the resources directory
     */
    public static String RESOURCES_DIR_LOCATION = Arrays.asList( System.getProperty("user.dir"), "src","main","resources").stream().collect(Collectors.joining(File.separator));

    /**
     * This method prints the program classpath files names on the console
     */
    public static void displayClassPath() {

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();

        Arrays.stream(urls)
                .forEach(System.out::println);
        System.out.println("----------------------------------------");
    }

    /**
     * This method loads the given directory files and add them to the program class path
     *
     * @param path  add the directory content to the classpath
     * @throws Exception
     */
    public static void addPath(String path) throws Exception {
        File f = new File(path);
        URI u = f.toURI();
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u.toURL()});
    }
}
