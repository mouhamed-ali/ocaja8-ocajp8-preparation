package com.certification.ocp.lambda;

import java.util.function.*;

public class PrimitiveInterfaces {

    public static void main(String[] args) {

        // for Predicate we have ; IntPredicate, LongPredicate and DoublePredicate;     boolean test(x value); x is the primitive type
        // for Consumer we have ; IntConsumer , LongConsumer and DoubleConsumer         void accept(x value);
        // methods of these interfaces accepts primitive values. the example below does not compile :

        DoublePredicate predicate = d -> d > 1.0;
        System.out.println(predicate.negate().test(0.0));

        // IntConsumer intConsumer = i -> System.out.println(i.intValue());
        // i here is of type int and not Integer ; void accept(int value); the code below compiles
        Consumer<Double> doubleConsumer = i -> System.out.println(i.intValue());

        // for functions we have three categories
        // IntFunction , LongFunction and DoubleFunction.       R apply(x value);           x is the primitive type (int, long or double)
        // ToIntFunction , ToLongFunction and ToDoubleFunction. x applyAsX(T value);        x is the primitive type; X can be (Integer, Long or Double)
        // XtoYFunction.                                        y applyAsY(x value);        x is the primitive type; X can be (Integer, Long or Double)

        LongFunction<String> longFunction = i -> String.valueOf(i);
        ToIntFunction<String> toIntFunction = s -> s.length();
        DoubleToLongFunction doubleToLongFunction = d -> Math.round(d);

        // for supplier we have; IntSupplier, LongSupplier, DoubleSupplier and BooleanSupplier;     x getAsX();
    }
}
