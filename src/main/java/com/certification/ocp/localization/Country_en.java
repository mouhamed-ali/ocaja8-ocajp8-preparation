package com.certification.ocp.localization;

import java.util.ListResourceBundle;

/**
 *  The List resource bundle class must be public and not inner class otherwise you will face a MissingResourceException
 */
public class Country_en extends ListResourceBundle {

    private Object[][] data = {{"parent-language", "Latin"}};

    @Override
    protected Object[][] getContents() {
        return data;
    }
}
