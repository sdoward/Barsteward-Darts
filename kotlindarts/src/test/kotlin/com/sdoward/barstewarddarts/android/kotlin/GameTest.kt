package com.sdoward.barstewarddarts.android.kotlin

import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class GameTest {

    private val instructions: GameInstructions = Mockito.mock(GameInstructions::class.java)
    private val players = listOf(Player("name1", 1), Player("name2", 2))
    private var game = Game(players, instructions)

    @Test
    fun shouldDisplayPlayers() {
        game.start()
        verify(instructions).displayCurrentPlayer("name1")
        game.apply {
            acceptThrow(Throw.innerBull())
            acceptThrow(Throw.innerBull())
            acceptThrow(Throw.innerBull())
        }
        verify(instructions).displayCurrentPlayer("name2")
        game.apply {
            acceptThrow(Throw.innerBull())
            acceptThrow(Throw.innerBull())
            acceptThrow(Throw.innerBull())
        }
        verify(instructions, times(2)).displayCurrentPlayer("name1")
    }

    @Test
    fun shouldDecrementLifeWhenOwnNumberHit() {
        game.apply {
            acceptThrow(Throw.single(1))
            acceptThrow(Throw.single(1))
            acceptThrow(Throw.miss())
        }
        Assertions.assertThat(game.players[0].lives).isEqualTo(1)
    }

    @Test
    fun shouldRemove2LivesWhenOwnNumberHit() {
        game.apply {
            acceptThrow(Throw.duble(1))
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
        }
        Assertions.assertThat(game.players[0].lives).isEqualTo(1)
    }

    @Test
    fun shouldRemove3LivesWhenOwnNumberHit() {
        game.apply {
            acceptThrow(Throw.triple(1))
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
        }
        Assertions.assertThat(game.players[0].lives).isEqualTo(0)
    }

    @Test
    fun shouldIncrementLifeWhenOtherPlayersNumberHit() {
        game.apply {
            acceptThrow(Throw.single(2))
            acceptThrow(Throw.single(2))
            acceptThrow(Throw.miss())
        }
        Assertions.assertThat(game.players[1].lives).isEqualTo(5)
    }

    @Test
    fun shouldAdd2LivesWhenDoubleOtherOtherPlayer() {
        game.apply {
            acceptThrow(Throw.duble(2))
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
        }
        Assertions.assertThat(game.players[1].lives).isEqualTo(5)
    }

    @Test
    fun shouldAdd3LivesWhenDoubleOtherOtherPlayer() {
        game.apply {
            acceptThrow(Throw.triple(2))
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
        }
        Assertions.assertThat(game.players[1].lives).isEqualTo(6)
    }

    @Test
    fun shouldEndGameWhenAnyLifeIsBelow1() {
        game.apply {
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
            acceptThrow(Throw.triple(1))
        }
        verify(instructions).displayWinner("name1")
    }

    @Test
    fun shouldNotEndGameWhenNoLivesAreBelow1() {
        game.apply {
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
            acceptThrow(Throw.miss())
        }
        verify(instructions, never()).displayWinner(anyString())
    }

    @Test
    fun shouldDisplayListOfDrinkingInstructions() {
        game.apply {
            acceptThrow(Throw.triple(1))
            acceptThrow(Throw.duble(2))
            acceptThrow(Throw.miss())
        }

        val expectingDrinkingInstructions = mutableListOf<String>()
        expectingDrinkingInstructions.add("name1 drink 3")
        expectingDrinkingInstructions.add("name2 drink 2")

        verify(instructions).displayDrinkingInstructions(expectingDrinkingInstructions)
    }

}