package com.certification.ocp.lambda;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.LongBinaryOperator;

public class BinaryOperatorInterfaces {

    public static void main(String[] args) {

        // The binary operator is a specialization of BiFunction representing an operation upon two operands of the same type
        // and producing a result of the same type of operands
        // BinaryOperator is a sub interface of BiFunction, it inherits all members of the BiFunction interface including abstract
        // and default methods
        // BinaryOperator has three specializations including IntBinaryOperator, LongBinaryOperator and DoubleBinaryOperator
        // BinaryOperator<T> : T apply(T left, T right)
        // XBinaryOperator  :  x applyAsX(x left, x right)

        BinaryOperator<String> binaryOperator = (s1,s2) -> s1.concat(".").concat(s2);
        System.out.println(binaryOperator.apply("google", "com"));

        LongBinaryOperator longBinaryOperator = (l1, l2) -> l1 + l2;
        System.out.println(longBinaryOperator.applyAsLong(12,7));

        BinaryOperator<Double> operator = (d1,d2) -> d1*d2;
        //double result = operator.apply(1,2);
        // the code above does not compile !!!
        // the operator accepts objects of type Double and thanks to autoboxing it can accepts only primitives of type double
        // event this will not work : double result = operator.apply(1.0f,2.0f);

        // these works because the input is a primitive double and you can easily cast a primitive float or int to double
        DoubleBinaryOperator operatorDouble = (d1,d2) -> d1*d2;
        double result = operatorDouble.applyAsDouble(1,2);
        result = operatorDouble.applyAsDouble(1.0f,2.0f);
    }
}
