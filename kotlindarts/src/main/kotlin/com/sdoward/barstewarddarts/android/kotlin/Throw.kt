package com.sdoward.barstewarddarts.android.kotlin

data class Throw(val lifeValue: Int, val value: Int, val number: Int, val isTriple: Boolean,
            val isDuble: Boolean, val isOuterBull: Boolean,
            val isInnerBull: Boolean, val isMiss: Boolean) {

    companion object {

        private val NOT_VALID = -1
        private val OUTER_BULL = 25
        private val INNER_BULL = 50
        private val MISS = 0
        private val NOTHING = 0
        private val SINGLE = 1
        private val DOUBLE = 2
        private val TRIPLE = 3


        fun single(number: Int): Throw {
            return Throw(SINGLE, number, number, false, false, false, false, false)
        }

        fun duble(number: Int): Throw {
            return Throw(DOUBLE, number * DOUBLE, number, false, false, false, false, false)
        }

        fun triple(number: Int): Throw {
            return Throw(TRIPLE, number * TRIPLE, number, false, false, false, false, false)
        }

        fun outerBull(): Throw {
            return Throw(SINGLE, OUTER_BULL, NOT_VALID, false, false, false, true, false)
        }

        fun innerBull(): Throw {
            return Throw(SINGLE, INNER_BULL, NOT_VALID, false, false, true, false, false)
        }

        fun miss(): Throw {
            return Throw(NOTHING, MISS, NOT_VALID, false, false, false, false, true)
        }
    }

}
