package com.sdoward.barstewarddarts.android.kotlin

class Game(val players: List<Player>, private val gameInstructions: GameInstructions) {

    private val turns = mutableListOf<Turn>()

    private var pointer = 0

    fun start() {
        displayCurrentPlayer()
        pointer++
    }

    fun acceptThrow(thro: Throw) {
        TurnValidator.acceptThrow(thro)
        if (TurnValidator.turnIsComplete()) {
            acceptTurn(TurnValidator.turn)
        }
    }

    private fun acceptTurn(turn: Turn) {
        turn.toList().forEach { reconsileLives(it) }
        turns.add(turn)
        displayDrinkingInstructions(turn)
        displayCurrentPlayer()
        if (players.any { it.lives < 1 }) {
            gameInstructions.displayWinner(players[pointer].name)
        }
        if (pointer == players.size - 1) {
            pointer = 0
        } else {
            pointer.inc()
        }
    }

    private fun displayCurrentPlayer() {
        gameInstructions.displayCurrentPlayer(players[pointer].name)
    }

    private fun reconsileLives(thro: Throw) {
        for (i in players.indices) {
            val player = players[i]
            if (numberHit(thro, player)) {
                if (i == pointer) {
                    player.lives = player.lives - thro.lifeValue
                } else {
                    player.lives = player.lives + thro.lifeValue
                }
            }
        }
    }

    private fun displayDrinkingInstructions(turn: Turn) {
        players.filter {
            turn.toList().any {thro -> numberHit(thro, it) }
        }.map {
            val drinkAmount = turn.toList().fold(0) {
                total, thro -> total.plus(getDrinkAmount(thro, it))
            }
            it.name.plus(" drink ").plus(drinkAmount)
        }.let {
            gameInstructions.displayDrinkingInstructions(it)
        }
    }

    private fun getDrinkAmount(thro: Throw, player: Player): Int {
        if (numberHit(thro, player)) {
            return thro.lifeValue
        } else {
            return 0
        }
    }

    private fun numberHit(thro: Throw, player: Player): Boolean {
        return thro.number == player.number
    }

}

interface GameInstructions {

    fun displayCurrentPlayer(name: String)

    fun displayWinner(name: String)

    fun displayDrinkingInstructions(drinkers: List<String>)

}
