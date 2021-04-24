package com.certification.oca.examples.advancedclassdesign;

public final class FinalFields {    // FinalFields class cannot be extended

    //Final Fields Initialization

    private static final String FIELD_1 = "some value";
    private static final String FIELD_2;

    static {

        FIELD_2 = "some data";
    }

    private final String field_3 = "some data";
    private final String field_4;
    private final String field_5;

    {
        this.field_4 = "some data";
    }

    public FinalFields() {

        this.field_5 = "some data";
    }

    public FinalFields(String value) {

        this.field_5 = value;
    }

    // this method cannot be extended by subclasses
    public final Long getAmount(){
        return 0l;
    }
}
