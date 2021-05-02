package com.certification.ocp.localization;

import java.util.ListResourceBundle;

/**
 *  The List resource bundle class must be public and not inner class otherwise you will face a MissingResourceException
 */
public class Country extends ListResourceBundle {

    private Object[][] data = {{"capital", "Unknown"}, {"area", new Integer(99)}};

    @Override
    protected Object[][] getContents() {
        return data;
    }
}