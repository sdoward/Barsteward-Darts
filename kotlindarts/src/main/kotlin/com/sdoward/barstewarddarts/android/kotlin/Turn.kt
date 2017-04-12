package com.sdoward.barstewarddarts.android.kotlin

data class Turn(val firstThrow: Throw, val secondThrow: Throw, val thirdThrow: Throw) {

    fun toList() = listOf(firstThrow, secondThrow, thirdThrow)

}
