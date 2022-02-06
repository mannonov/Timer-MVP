package com.mannonov.timermvp.mvp

import android.os.CountDownTimer
import java.lang.ref.WeakReference

class TimerPresenterImpl(private var timerModelImpl: TimerModelImpl) : TimerContract.Presenter {

    private var viewRef: WeakReference<TimerContract.View>? = null

    override fun onStop() {

    }

    override fun onResume() {

    }

    override fun onStart() {
        timerModelImpl = timerModelImpl.copy(isTick = true)
        val time = timerModelImpl.time * 1000L
        viewRef?.get()?.changeTimerStatus(timerModelImpl)
        object : CountDownTimer(time, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                viewRef?.get()?.updateTimer(millisUntilFinished / 1000)
                viewRef?.get()?.updateProgressBar(millisUntilFinished * 100 / time)
            }

            override fun onFinish() {
                viewRef?.get()?.finishTimer(timerModelImpl)
            }
        }.start()
    }

    override fun onFinish() {

    }

    override fun onReset() {

    }

    override fun attachView(view: TimerContract.View) {
        viewRef = WeakReference(view)
    }

    override fun detachView() {

    }
}