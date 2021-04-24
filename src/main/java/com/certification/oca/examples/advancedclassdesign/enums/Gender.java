package com.certification.oca.examples.advancedclassdesign.enums;

public enum Gender {
    MALE{
        // constants body are governed by normal rules of anonymous classes including the fact that it cannot define constructors
        @Override
        void print() {
            System.out.println("I'm a male");
        }
    },
    FEMALE{
        @Override
        void print() {
            System.out.println("I'm a female");
        }
    };
    abstract void print();

    public static void main(String[] args) {
        Gender.FEMALE.print();
        Gender.MALE.print();

        Gender male = Gender.MALE;
        // enums extends the Enum class and the declaration below is valid
        Enum<Gender> male2 = Gender.MALE;
    }
}
