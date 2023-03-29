package com.suein.myapplication.jpub.chapter.ch41

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.math.roundToInt

class Chapter41TestViewModel : ViewModel() {

    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    fun convertTemp(temp: String) {
        val tt = try {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                ((tempInt - 32) * 0.5556).roundToInt().toString()
            } else {
                ((tempInt * 1.8) + 32).roundToInt().toString()
            }
        } catch (e: Exception) {
            "Invalid Entry"
        }

        Log.e("suein", "convertTemp : $tt")
        result = tt
    }

    fun switchChange() {
        val temp = !isFahrenheit
        Log.e("suein", "viewmodel : 1switchChange : $temp")
        isFahrenheit = temp
        Log.e("suein", "viewmodel : 2switchChange : $isFahrenheit")
    }
}