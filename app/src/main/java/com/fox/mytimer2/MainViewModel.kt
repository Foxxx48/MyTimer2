package com.fox.mytimer2

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {

    private var sec = 0
    private var isRunning = false

    private val _time = MutableLiveData<String>()
    val time: LiveData<String>
        get() = _time

    fun runTimer() {
         MyHandler.apply {
            post(object : Runnable {
                override fun run() {
//                    myLog("Runnable is run")
                    val hours = sec / 3600
                    val minutes = (sec % 3600) / 60
                    val secs = sec % 60

                    val time =
                        String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                    _time.value = time

                    myLog(time)

                    if (isRunning) {
                        sec++
                    }
                    postDelayed(this, 1000)
                    myLog("Run № ${this.hashCode()}")
                    myLog("Handler № ${MyHandler.hashCode()}")
                }

            })
         }

    }

   fun setStart() {
       isRunning = true
   }

    fun setPause(){
        isRunning = false
    }

   fun setClear() {
        isRunning = false
        sec = 0
    }

    private fun myLog(message: String) {
        Log.d(MYAPP, message)
    }

    companion object {
        private const val MYAPP = "MyAPP"
    }

}