package com.sdoward.barstewarddarts.android.java;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Turn {

    public static Turn create(Throw firstThrow, Throw secondThrow, Throw thirdThrow) {
        return new AutoValue_Turn(firstThrow, secondThrow, thirdThrow);
    }

    public abstract Throw getFirstThrow();

    public abstract Throw getSecondThrow();

    public abstract Throw getThirdThrow();

}
