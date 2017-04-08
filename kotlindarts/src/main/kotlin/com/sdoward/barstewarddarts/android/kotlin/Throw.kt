package com.sdoward.barstewarddarts.android.kotlin

data class Throw(val value: Int, private val number: Int?, val isTriple: Boolean,
            val isDuble: Boolean, val isOuterBull: Boolean,
            val isInnerBull: Boolean, val isMiss: Boolean) {

    companion object {

        private val OUTER_BULL = 25
        private val INNER_BULL = 50
        private val MISS = 0
        private val DOUBLE = 2
        private val TRIPLE = 3

        fun single(number: Int): Throw {
            return Throw(number, number, false, false, false, false, false)
        }

        fun duble(number: Int): Throw {
            return Throw(number * DOUBLE, number, false, false, false, false, false)
        }

        fun triple(number: Int): Throw {
            return Throw(number * TRIPLE, number, false, false, false, false, false)
        }

        fun outerBull(): Throw {
            return Throw(OUTER_BULL, null, false, false, false, true, false)
        }

        fun innerBull(): Throw {
            return Throw(INNER_BULL, null, false, false, true, false, false)
        }

        fun miss(): Throw {
            return Throw(MISS, null, false, false, false, false, true)
        }
    }
}
