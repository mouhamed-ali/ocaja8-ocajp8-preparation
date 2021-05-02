package com.certification.ocp.localization.utils;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

// Extend ResourceBundle.Control and override getCandidateLocales method to get the list of candidate locales that Java searches for
public class TalkativeResourceBundleControl extends ResourceBundle.Control {
    // override the default getCandidateLocales method to print the candidate locales first
    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
        List<Locale> candidateLocales = super.getCandidateLocales(baseName, locale);
        System.out.printf("Candidate locales for base bundle name %s and locale %s %n",
                baseName, locale.getDisplayName());
        candidateLocales.forEach(System.out::println);
        System.out.println("-----------------------------------------");
        return candidateLocales;
    }
}
