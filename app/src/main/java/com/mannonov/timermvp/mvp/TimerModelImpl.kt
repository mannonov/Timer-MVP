package com.mannonov.timermvp.mvp

data class TimerModelImpl(
    override val isTick: Boolean = false,
    override val time: Int = 0,
    override val currentTime: Int = 0,
) : TimerContract.Model