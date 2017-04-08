package com.sdoward.barstewarddarts.android.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TurnValidatorTest {

    private val turnValidator = TurnValidator

    @Test
    fun shouldReturnFalseWhenTurnIsNotComplete() {
        assertThat(turnValidator.turnIsComplete()).isFalse()

        turnValidator.acceptThrow(Throw.innerBull())

        assertThat(turnValidator.turnIsComplete()).isFalse()

        turnValidator.acceptThrow(Throw.innerBull())

        assertThat(turnValidator.turnIsComplete()).isFalse()
    }

    @Test
    fun shouldReturnTrueWhenTurnIsComplete() {
        turnValidator.resetTurn() // not sure why this is necessary

        turnValidator.acceptThrow(Throw.innerBull())
        turnValidator.acceptThrow(Throw.outerBull())
        turnValidator.acceptThrow(Throw.miss())

        assertThat(turnValidator.turnIsComplete()).isTrue()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenForthTurnIsAdded() {
        turnValidator.resetTurn()

        turnValidator.acceptThrow(Throw.innerBull())
        turnValidator.acceptThrow(Throw.outerBull())
        turnValidator.acceptThrow(Throw.miss())
        turnValidator.acceptThrow(Throw.duble(1))
    }

    @Test
    fun shouldReturnTurnWhenValid() {

        turnValidator.acceptThrow(Throw.innerBull())
        turnValidator.acceptThrow(Throw.outerBull())
        turnValidator.acceptThrow(Throw.miss())

        val expectedTurn = Turn(Throw.innerBull(), Throw.outerBull(), Throw.miss())
        val actualTurn = turnValidator.turn

        assertThat(expectedTurn).isEqualTo(actualTurn)
    }

}