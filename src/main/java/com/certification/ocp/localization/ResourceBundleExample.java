package com.certification.ocp.localization;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class ResourceBundleExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        /*
            The ResourceBundle class represents resources contains Locale specific objects. This class is essential in dealing with localization.
            The ResourceBundle class contains a static method named getBundle, which reads a properties file with the specified base name and the default locale
            public static final ResourceBundle getBundle(String baseName)
         */
        ResourceBundle examRB = ResourceBundle.getBundle("Exam"); // Assuming you have the Exam.properties file in your classpath
        Enumeration<String> examEnumeration = examRB.getKeys();
        while (examEnumeration.hasMoreElements()){
            System.out.printf("One of the exam key is : %s%n", examEnumeration.nextElement());
        }
        Set<String> setKeys = examRB.keySet();
        setKeys.forEach(System.out::println);

        // to get a property value, you can use the getString method
        System.out.printf("The value of the save key is : '%s'%n", examRB.getString("save"));
        System.out.printf("The value of the cancel key is : '%s'%n", examRB.getString("cancel"));
        System.out.printf("The value of the continue key is : '%s'%n", examRB.getString("continue"));
        // and if a resource property is missed, an exception will be thrown
        try {
            System.out.printf("The value of the \"continue\" key is : '%s'%n", examRB.getString("\"continue\""));
        }catch (MissingResourceException e){
            System.err.format("An error occurred : %s%n", e.toString());
            Thread.sleep(500); // this error log takes a while to print the message on the console
        }
        System.out.printf("%n ---------------------------------------- %n%n");

        // The ResourceBundle class has two descendants, PropertyResourceBundle that manages strings from properties files and ListResourceBundle that manages objects from java classes
        /*
            If a resource bundle for the specified locale does not exist, the ResourceBundle::getBundle method will attempt to find the closest match before falling back on the bundle
            for the default locale, then on the bundle with the base name.
            If you look for the bundle Countries_fr_CA for example and your locale is en_US for example, this is the order in which the bundles are searched for :
            Countries_fr_CA -> Countries_fr -> Countries_en_US -> Countries_en -> Countries
            Notice that getBundle looks for a class file matching the base name (Countries) and the given locale. It only checks for properties files if it can't find a class file.
         */
        PropertyResourceBundle propertiesBundle1 = (PropertyResourceBundle) ResourceBundle.getBundle("Exam");   // you can create a property resource bundle using a cast or one of the constructors

        String resourcesLocation = Arrays.asList( System.getProperty("user.dir"), "src","main","resources").stream().collect(Collectors.joining(File.separator)) + File.separator;  // resources dir of the project
        PropertyResourceBundle propertiesBundle2 = new PropertyResourceBundle(new FileInputStream(resourcesLocation + "Exam.properties"));    // public PropertyResourceBundle (InputStream stream) throws IOException
        PropertyResourceBundle propertiesBundle3 = new PropertyResourceBundle(new FileReader(resourcesLocation + "Exam.properties"));    // public PropertyResourceBundle (Reader reader) throws IOException
        // the '.properties' is mandatory here and you have to specify the full path. files will be loaded from file system not the classpath

        /*
            A concrete subclass of ListResourceBundle must provide an implementation of the abstract method getContents : abstract protected Object[][] getContents();
            The result is a two dimension array, each element of the inner array has two elements : The key which must be a string, the value that can be of any object reference type
         */
        System.out.printf("My current locale is fr_FR - %s%n", Locale.getDefault());
        Locale usLocale = new Locale("en", "US");
        ResourceBundle myBundle = ResourceBundle.getBundle("com.certification.ocp.localization.Country", usLocale);
        System.out.printf("The capital is : %s%n", myBundle.getString("capital"));                      // origin Country_en_US.class
        System.out.printf("The parent language is : %s%n", myBundle.getObject("parent-language"));      // origin Country_en.class
        System.out.printf("The area is : %d%n", myBundle.getObject("area"));                            // origin Country.class
        // If you check the getObject implementation, you'll see the hierarchy. If the key is not found in the bundle, we will look in its parent.
        // the parent of Country_en_US.class is Country_en.class. The parent of Country_en.class is Country.class.

        // TODO : based on course, if a key is not present in a given locale bundle, we will check in the default locale then in the base bundle name. Otherwise, we will re-apply the process on properties files.
        // TODO : This is not the case and the code below does not work.
        //System.out.printf("The monument is : %d%n", myBundle.getObject("monument"));                      // origin Country_fr_FR.class
        //System.out.printf("The second capital is : %s%n", myBundle.getString("second-capital"));        // origin Country_en_US.properties
        //System.out.printf("The language is : %s%n", myBundle.getString("language"));                    // origin Country_fr.properties
        //System.out.printf("The currency is : %s%n", myBundle.getString("currency"));                    // origin Country.properties
    }
}
