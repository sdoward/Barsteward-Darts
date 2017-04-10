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
        verify<GameInstructions>(instructions).displayCurrentPlayer("name1")
        game.acceptThrow(Throw.innerBull())
        game.acceptThrow(Throw.innerBull())
        game.acceptThrow(Throw.innerBull())
        verify<GameInstructions>(instructions).displayCurrentPlayer("name2")
        game.acceptThrow(Throw.innerBull())
        game.acceptThrow(Throw.innerBull())
        game.acceptThrow(Throw.innerBull())
        verify<GameInstructions>(instructions, times(2)).displayCurrentPlayer("name1")
    }

    @Test
    fun shouldDecrementLifeWhenOwnNumberHit() {
        game.acceptThrow(Throw.single(1))
        game.acceptThrow(Throw.single(1))
        game.acceptThrow(Throw.miss())
        Assertions.assertThat(game.players[0].lives).isEqualTo(1)
    }

    @Test
    fun shouldRemove2LivesWhenOwnNumberHit() {
        game.acceptThrow(Throw.duble(1))
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        Assertions.assertThat(game.players[0].lives).isEqualTo(1)
    }

    @Test
    fun shouldRemove3LivesWhenOwnNumberHit() {
        game.acceptThrow(Throw.triple(1))
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        Assertions.assertThat(game.players[0].lives).isEqualTo(0)
    }

    @Test
    fun shouldIncrementLifeWhenOtherPlayersNumberHit() {
        game.acceptThrow(Throw.single(2))
        game.acceptThrow(Throw.single(2))
        game.acceptThrow(Throw.miss())
        Assertions.assertThat(game.players[1].lives).isEqualTo(5)
    }

    @Test
    fun shouldAdd2LivesWhenDoubleOtherOtherPlayer() {
        game.acceptThrow(Throw.duble(2))
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        Assertions.assertThat(game.players[1].lives).isEqualTo(5)
    }

    @Test
    fun shouldAdd3LivesWhenDoubleOtherOtherPlayer() {
        game.acceptThrow(Throw.triple(2))
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        Assertions.assertThat(game.players[1].lives).isEqualTo(6)
    }

    @Test
    fun shouldEndGameWhenAnyLifeIsBelow1() {
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.triple(1))
        verify(instructions).displayWinner("name1")
    }

    @Test
    fun shouldNotEndGameWhenNoLivesAreBelow1() {
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        game.acceptThrow(Throw.miss())
        verify(instructions, never()).displayWinner(anyString())
    }

    @Test
    fun shouldDisplayListOfDrinkingInstructions() {
        game.acceptThrow(Throw.triple(1))
        game.acceptThrow(Throw.duble(2))
        game.acceptThrow(Throw.miss())

        val expectingDrinkingInstructions = mutableListOf<String>()
        expectingDrinkingInstructions.add("name1 drink 3")
        expectingDrinkingInstructions.add("name2 drink 2")

        verify(instructions).displayDrinkingInstructions(expectingDrinkingInstructions)
    }

}