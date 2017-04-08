package com.sdoward.barstewarddarts.android.kotlin

object TurnValidator {

    private var firstThrow: Throw? = null
    private var secondThrow: Throw? = null
    private var thirdThrow: Throw? = null

    fun acceptThrow(frow: Throw) {
        if (firstThrow == null) {
            firstThrow = frow
        } else if (secondThrow == null) {
            secondThrow = frow
        } else if (thirdThrow == null) {
            thirdThrow = frow
        } else {
            throw IllegalStateException("3 throws are complete. ")
        }

    }

    fun turnIsComplete() :Boolean {
        return firstThrow !== null && secondThrow !== null && thirdThrow !== null
    }

    val turn: Turn
        get() {
            if (turnIsComplete()) {
                val turn = Turn(firstThrow!!, secondThrow!!, thirdThrow!!)
                resetTurn()
                return turn
            } else {
                throw java.lang.IllegalStateException("3 throws haven't been completed")
            }

        }
     fun resetTurn() {
        firstThrow = null
        secondThrow = null
        thirdThrow = null
    }


}