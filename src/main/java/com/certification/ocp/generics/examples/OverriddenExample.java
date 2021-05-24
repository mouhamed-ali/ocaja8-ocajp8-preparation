package com.certification.ocp.generics.examples;

public class OverriddenExample {

    static class LastError<T> {
        private T lastError;

        void setError(T t) {
            System.out.printf("#LastError # setError | message : %s%n", t);
        }
    }

    static class StrLastError<S extends CharSequence> extends LastError<String> {
        public StrLastError(S s) {
        }

        void setError(S s) {
            System.out.printf("#StrLastError # setError | message : %s%n", s);
        }
    }

    public static void main(String[] args) {

        StrLastError<StringBuilder> errBuilder = new StrLastError<>(new StringBuilder());
        errBuilder.setError("Builder : Last Error");    // calls setError(String t)
        errBuilder.setError(new StringBuilder("Builder : Last Error")); // calls setError(StringBuilder_extends_CharSequence s)

        System.out.printf("%n ---------------------------------------- %n");

        StrLastError<String> errString = new StrLastError<>("Error");
        //errString.setError("Last Error");

        // the last code line generated a compilation error but why ?
        // It seems like setError in StrLastError is overridden but this is not the case. At compilation time the knowledge of S is not available
        // So the compiler records the signatures of these two methods as setError(String) in superclass and setError(S_extends_CharSequence)
        // in subclass treating them as overloaded and not overridden
        // so when you call the setError with a string builder as the first case, there is no problem as these two methods does not match
        // setError(StringBuilder) matches setError(S_extends_CharSequence) and not setError(String)
        // but setError("Last Error") matches matches setError(S_extends_CharSequence) and not setError(String) which creates the ambiguous compilation error
    }
}
