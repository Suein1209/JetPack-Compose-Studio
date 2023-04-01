package com.suein.myapplication.jpub.chapter.ch51

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class Chapter51SharedFlowDemoViewModel : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInt()
    }

    private fun sharedFlowInt() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000)
                println("Emitting $i")
                _sharedFlow.emit(i)
            }
        }
    }

}