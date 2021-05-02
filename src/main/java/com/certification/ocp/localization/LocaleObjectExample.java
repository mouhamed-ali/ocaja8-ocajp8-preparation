package com.certification.ocp.localization;


import java.util.Locale;

public class LocaleObjectExample {

    public static void main(String[] args) {

        /*
            A locale object is a representation of a geographical, political or cultural region. It contains information about language, region, variant, script and extensions.
            * Language can be an ISO 639 alpha-2 or alpha-3 code or registered language subtag.
            * Region (Country) is ISO 3166 alpha-2 country code or UN numeric-3 area code.
            * Variant is a case-sensitive value or set of values specifying a variation of a Locale.
            * Script must be a valid ISO 15924 alpha-4 code.
            * Extensions is a map which consists of single character keys and String values.
         */

        // You can build instances of the Locale class using setter method of Local.Builder
        Locale enLocale = new Locale.Builder().setLanguage("en").build();
        Locale frLocale = new Locale.Builder().setLanguage("fr").setRegion("CA").build();   // the locale is for Canada

        // You can also use one of the three constructors of the Locale class
        Locale de = new Locale("de");
        Locale ruLocale = new Locale("jp", "JA");   // Japan , Japanese
        Locale usUnixLocale = new Locale("en", "US", "UNIX"); //  (United State, UNIX platform)

        // You can also use the static method Locale::getLanguageTag using strings conforms to the IETF BCP 47 language tag
        Locale usLocale = Locale.forLanguageTag("ko-KR");       // even if you pass a non valid string, the method will not throw an exception
        System.out.printf("The US Locale is %s%n", usLocale);   // PN: the displayed locale is ko_KR and not ko-KR (south korea)

        // You can also use several static final fields from the Locale class
        Locale zhLocale = Locale.CHINESE;   // only the language will be initialized
        System.out.printf("The language is '%s' and the region is [%s]%n", zhLocale.getLanguage(), zhLocale.getCountry());
        Locale itLocale = Locale.ITALY;     // the country and the mail language will be initialized
        System.out.printf("The language is '%s' and the region is [%s]%n", itLocale.getLanguage(), itLocale.getCountry());

        // The default Locale is the one used by your jvm after starting it and that corresponds to your Locale
        System.out.printf("My default Locale is %s%n", Locale.getDefault());
        Locale.setDefault(Locale.UK);
        System.out.printf("My new default Locale is %s%n", Locale.getDefault());

        new Locale.Builder().build();   // PN : this does not contain your default locale, it contains an empty locale

        Locale unknownLocale = new Locale("US", "en");
        System.out.printf("The unknown locale is %s%n", unknownLocale); // prints us_EN. The locale is just an identifier, it doesn't check if the locale is valid or not
    }
}
