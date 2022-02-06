package com.mannonov.timermvp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mannonov.timermvp.R
import com.mannonov.timermvp.databinding.FragmentTimerBinding
import com.mannonov.timermvp.mvp.TimerContract
import com.mannonov.timermvp.mvp.TimerModelImpl
import com.mannonov.timermvp.mvp.TimerPresenterImpl


class TimerFragment : Fragment(), TimerContract.View {

    private lateinit var _binding: FragmentTimerBinding
    private val binding get() = _binding

    private lateinit var presenter: TimerContract.Presenter

    private val args: TimerFragmentArgs by navArgs()

    private val TAG = "TimerFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTimerBinding.inflate(layoutInflater)
        presenter =
            TimerPresenterImpl(timerModelImpl = TimerModelImpl(isTick = false, time = args.time))
        presenter.attachView(this)
        return _binding.root
    }

    override fun updateProgressBar(percent: Long) {
        binding.progressBarTimer.progress = percent.toInt()
        Log.d(TAG, "updateProgressBar: $percent")
    }

    override fun updateTimer(time: Long) {
        val sec = time % 60
        val min = time / 60
        binding.tvLessTime.text = String.format("%d : %d", min, sec)
    }

    override fun finishTimer(model: TimerContract.Model) {
        binding.btnStartPause.visibility = View.VISIBLE
        binding.tvLessTime.text = String.format("%d : %d", 0, args.time)
        binding.progressBarTimer.progress = 100
        Snackbar.make(
            binding.root,
            "Finished",
            Snackbar.LENGTH_INDEFINITE
        ).show()
    }

    override fun changeTimerStatus(model: TimerContract.Model) {
        Log.d(TAG, "changeTimerStatus: $model")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartPause.setOnClickListener {
            presenter.onStart()
            binding.btnStartPause.visibility = View.GONE
        }

        binding.tvLessTime.text = String.format("%d : %d", 0, args.time)


    }


}