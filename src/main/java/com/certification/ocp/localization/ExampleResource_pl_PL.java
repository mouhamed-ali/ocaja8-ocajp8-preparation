package com.certification.ocp.localization;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExampleResource_pl_PL extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"currency", "polish zloty"},
                {"toUsdRate", new BigDecimal("3.401")},
                {"cities", new String[] { "Warsaw", "Cracow" }},
                {new Integer(99), new String[] { "Not", "Working" }}
        };
    }

    public static void main(String[] args){

        Locale locale = new Locale("pl", "PL");
        ResourceBundle exampleBundle = ResourceBundle.getBundle("com.example.ocjp.localization.ExampleResource", locale);

        System.out.println(exampleBundle.getString("currency"));
        System.out.println(exampleBundle.getObject("toUsdRate"));
        System.out.println(Arrays.toString((String[])exampleBundle.getObject("cities")));
    }
}
