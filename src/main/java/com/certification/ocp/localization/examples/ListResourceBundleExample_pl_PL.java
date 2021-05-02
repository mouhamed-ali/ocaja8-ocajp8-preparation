package com.certification.ocp.localization.examples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ListResourceBundleExample_pl_PL extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"currency", "polish zloty"},
                {"toUsdRate", new BigDecimal("3.401")},
                {"cities", new String[] { "Warsaw", "Cracow" }},
                {new Integer(99).toString(), new String[] { "Not", "Working" }} // PN : you cannot define a key as Integer, the key ** must ** by a string
        };
    }

    public static void main(String[] args){

        Locale locale = new Locale("pl", "PL");
        ResourceBundle exampleBundle = ResourceBundle.getBundle("com.certification.ocp.localization.examples.ListResourceBundleExample", locale);

        System.out.println(exampleBundle.getString("currency"));
        System.out.println(exampleBundle.getObject("toUsdRate"));
        System.out.println(Arrays.toString((String[])exampleBundle.getObject("cities")));
        System.out.println(Arrays.toString(exampleBundle.getStringArray("99")));
    }
}
