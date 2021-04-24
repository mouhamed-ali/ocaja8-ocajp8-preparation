package com.certification.oca.examples.classdesign.immutable;

import java.util.ArrayList;
import java.util.List;

public class ImmutableClassExample {
    /*
     * Rules :
     * Make all fields private and final
     * Do not provide setter methods
     * Do not subclasses to override the immutable class methods (make the class final) extendable -> mutable
     * If instances of the class includes references to mutable objects, do not expose directly those objects (make copies)
     */

    private final String name;
    private final List<String> nickNames;

    public ImmutableClassExample(String name, List<String> nickNames){
        this.name = name;
        this.nickNames = new ArrayList<>(nickNames);
    }

    public String getName() {
        return name;
    }

    public List<String> getNickNames() {
        return new ArrayList<>(nickNames);
    }
}
