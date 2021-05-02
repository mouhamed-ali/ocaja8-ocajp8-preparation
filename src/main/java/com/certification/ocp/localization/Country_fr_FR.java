package com.certification.ocp.localization;

import java.util.ListResourceBundle;

/**
 *  The List resource bundle class must be public and not inner class otherwise you will face a MissingResourceException
 */
public class Country_fr_FR extends ListResourceBundle {

    private Object[][] data = {{"capital", "Paris"}, {"monument", "Eiffel tour"}};

    @Override
    protected Object[][] getContents() {
        return data;
    }
}
