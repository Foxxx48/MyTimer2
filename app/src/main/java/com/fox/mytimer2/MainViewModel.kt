package com.fox.mytimer2

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {

    var sec = 0
    var isRunning = false

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    private val _time = MutableLiveData<String>()
    val time: LiveData<String>
        get() = _time

     fun runTimer() {



        val handler = Handler().apply {
            post(object : Runnable {
                override fun run() {
                    myLog("Runnable is run")
                    val hours = sec / 3600
                    val minutes = (sec % 3600) / 60
                    val secs = sec % 60

                    val time =
                        String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                    _time.value = time

                    myLog("$time")

                    if (isRunning) {
                        sec++

                    }
                    postDelayed(this, 1000)
                }
            })
        }
    }

    fun changeRunningState(): Boolean {
         isRunning = true
        return isRunning
    }

    private fun myLog(message: String) {
        Log.d(MYAPP, message)
    }

    companion object {
        private const val SECONDS = "seconds"
        private const val IS_RUNNING = "isRunning"
        private const val MYAPP = "MyAPP"
    }

}