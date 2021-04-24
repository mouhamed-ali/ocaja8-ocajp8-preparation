package com.certification.ocp.localization;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyResourceExample {

    public static final void main(String... args) throws Exception{

        addPath("D:\\demo\\src\\main\\resources");

        Locale.setDefault(new Locale("th", "TH"));
        ResourceBundle rb = ResourceBundle.getBundle("Cars", new Locale("ar", "TS")); // arab_Tunisia
        System.out.printf("engine : %s %n",rb.getString("engine"));
        System.out.printf("horses : %s %n",rb.getString("horses"));
        System.out.printf("country : %s %n",rb.getString("country"));
        System.out.printf("global : %s %n",rb.getString("global"));
        System.out.printf("en : %s %n",rb.getString("en"));
    }

    public static void displayClassPath() {

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();

        Arrays.stream(urls)
                .forEach(System.out::println);
        System.out.println("----------------------------------------");
    }

    public static void addPath(String s) throws Exception {
        File f = new File(s);
        URI u = f.toURI();
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u.toURL()});
    }
}
