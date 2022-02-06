package com.mannonov.timermvp.mvp

import com.mannonov.timermvp.base.BasePresenter

interface TimerContract {

    interface View {

        fun updateProgressBar(percent: Long)
        fun updateTimer(time: Long)
        fun finishTimer(model: Model)
        fun changeTimerStatus(model: Model)

    }

    interface Presenter : BasePresenter<View> {

        fun onStop()
        fun onResume()
        fun onStart()
        fun onFinish()
        fun onReset()

    }

    interface Model {
        val isTick: Boolean
        val time: Int
        val currentTime: Int
    }

}