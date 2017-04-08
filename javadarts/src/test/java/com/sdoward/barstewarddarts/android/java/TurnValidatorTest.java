package com.sdoward.barstewarddarts.android.java;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


public class TurnValidatorTest {

    private final TurnValidator turnValidator = new TurnValidator();

    @Test
    public void shouldReturnFalseWhenTurnIsNotComplete() {

        assertThat(turnValidator.turnIsComplete()).isFalse();

        turnValidator.acceptThrow(Throw.innerBull());

        assertThat(turnValidator.turnIsComplete()).isFalse();

        turnValidator.acceptThrow(Throw.innerBull());

        assertThat(turnValidator.turnIsComplete()).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTurnIsComplete() {
        turnValidator.acceptThrow(Throw.innerBull());
        turnValidator.acceptThrow(Throw.outerBull());
        turnValidator.acceptThrow(Throw.miss());

        assertThat(turnValidator.turnIsComplete()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenForthTurnIsAdded() {
        turnValidator.acceptThrow(Throw.innerBull());
        turnValidator.acceptThrow(Throw.outerBull());
        turnValidator.acceptThrow(Throw.miss());
        turnValidator.acceptThrow(Throw.duble(1));
    }

    @Test
    public void shouldReturnTurnWhenValid() {

        turnValidator.acceptThrow(Throw.innerBull());
        turnValidator.acceptThrow(Throw.outerBull());
        turnValidator.acceptThrow(Throw.miss());

        Turn expectedTurn = Turn.create(Throw.innerBull(), Throw.outerBull(), Throw.miss());
        Turn actualTurn = turnValidator.getTurn();

        assertThat(expectedTurn).isEqualTo(actualTurn);
    }


}