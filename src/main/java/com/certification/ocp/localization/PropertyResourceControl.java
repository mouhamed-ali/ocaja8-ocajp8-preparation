package com.certification.ocp.localization;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyResourceControl {

    public static final void main(String... args) throws Exception{

        addPath("D:\\demo\\src\\main\\resources");
        //System.out.println(Locale.getDefault());
        Locale.setDefault(new Locale("en", "US"));
        ResourceBundle rb = ResourceBundle.getBundle("Cars",
                new Locale("fr", "CA"),
                new TalkativeResourceBundleControl());
        //System.out.println(rb.getString("country") + " "+ rb.getString("horses"));
        System.out.println(rb.getString("horses"));
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

// Extend ResourceBundle.Control and override getCandidateLocales method
// to get the list of candidate locales that Java searches for
class TalkativeResourceBundleControl extends ResourceBundle.Control {
    // override the default getCandidateLocales method to print
    // the candidate locales first
    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
        List<Locale> candidateLocales = super.getCandidateLocales(baseName, locale);
        System.out.printf("Candidate locales for base bundle name %s and locale %s %n",
                baseName, locale.getDisplayName());
        candidateLocales.forEach(System.out::println);
        System.out.println("-----------------------------------------");
        return candidateLocales;
    }
}

