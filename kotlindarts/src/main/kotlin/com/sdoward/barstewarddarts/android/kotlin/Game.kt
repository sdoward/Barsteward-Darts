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
        if (players.any { it.lives < 1 })
            gameInstructions.displayWinner(players[pointer].name)
        pointer = if (pointer == players.size - 1) 0 else pointer.inc()
    }

    private fun displayCurrentPlayer() {
        gameInstructions.displayCurrentPlayer(players[pointer].name)
    }

    private fun reconsileLives(thro: Throw) {
        for ((i, player) in players.withIndex()) {
            if (numberHit(thro, player)) {
                val amount = if (i == pointer) -thro.lifeValue else thro.lifeValue
                player.lives = player.lives.plus(amount)
            }
        }
    }

    private fun displayDrinkingInstructions(turn: Turn) {
        players.map { player ->
            val drinkAmount = turn
                    .toList()
                    .filter { numberHit(it, player) }
                    .fold(0) { total, thro -> total.plus(thro.lifeValue) }
            Pair(player, drinkAmount)
        }
                .filter { it.second != 0 }
                .map { it.first.name.plus(" drink ").plus(it.second) }
                .let { gameInstructions.displayDrinkingInstructions(it) }
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
