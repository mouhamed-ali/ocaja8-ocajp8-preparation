package com.certification.ocp.localization.examples;

import com.certification.ocp.localization.utils.LocalizationUtils;
import com.certification.ocp.localization.utils.TalkativeResourceBundleControl;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PropertyResourceExample {

    public static final void main(String... args) throws Exception{

        LocalizationUtils.addPath(LocalizationUtils.RESOURCES_DIR_LOCATION);
        LocalizationUtils.displayClassPath();

        // the default locale will be fr_FR
        Locale.setDefault(new Locale("fr", "FR"));

        ResourceBundle rb = ResourceBundle.getBundle(
                "Cars",
                new Locale("en", "US", "POSIX"),
                new TalkativeResourceBundleControl()        // shows the candidates of the given locale (en_US)
        );
        System.out.printf("engine : %s %n",rb.getString("engine"));     // loaded from Cars_en_US.properties
        System.out.printf("horses : %s %n",rb.getString("horses"));     // loaded from Cars_en_US.properties
        System.out.printf("country : %s %n",rb.getString("country"));   // loaded from Cars_en_US.properties
        System.out.printf("en : %s %n",rb.getString("en"));             // loaded from Cars_en.properties
        System.out.printf("global : %s %n",rb.getString("global"));     // loaded from Cars.properties

        try {
            System.out.printf("currency : %s %n",rb.getString("currency")); // cannot be loaded from Cars_fr_FR.properties
        }catch (MissingResourceException e){
            System.err.format("An error occurred : %s%n", e.toString());
        }
        try {
            System.out.printf("language : %s %n",rb.getString("language")); // cannot be loaded from Cars_fr.properties
        }catch (MissingResourceException e){
            System.err.format("An error occurred : %s%n", e.toString());
        }
    }
}
