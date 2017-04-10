package com.sdoward.barstewarddarts.android.java;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Throw {

    private static final int NOT_VALID = -1;
    private static final int OUTER_BULL = 25;
    private static final int INNER_BULL = 50;
    private static final int MISS = 0;
    private static final int NOTHING = 0;
    private static final int SINGLE = 1;
    private static final int DOUBLE = 2;
    private static final int TRIPLE = 3;


    public static Throw single(int number) {
        return new AutoValue_Throw(SINGLE, number, number, false, false, false, false, false);
    }

    public static Throw duble(int number) {
        return new AutoValue_Throw(DOUBLE, number * DOUBLE, number, false, false, false, false, false);
    }

    public static Throw triple(int number) {
        return new AutoValue_Throw(TRIPLE, number * TRIPLE, number, false, false, false, false, false);
    }

    public static Throw outerBull() {
        return new AutoValue_Throw(SINGLE, OUTER_BULL, NOT_VALID, false, false, false, true, false);
    }

    public static Throw innerBull() {
        return new AutoValue_Throw(SINGLE, INNER_BULL, NOT_VALID, false, false, true, false, false);
    }

    public static Throw miss() {
        return new AutoValue_Throw(NOTHING, MISS, NOT_VALID, false, false, false, false, true);
    }

    public abstract int getLifeValue();

    public abstract int getValue();

    public abstract int getNumber();

    public abstract boolean isDuble();

    public abstract boolean isTriple();

    public abstract boolean isOuterBull();

    public abstract boolean isInnerBull();

    public abstract boolean isMiss();

}
