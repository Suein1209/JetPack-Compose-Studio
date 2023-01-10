package com.suein.myapplication.toto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class TestContact(val firstName: String, val lastName: String)

class TotoViewModel : ViewModel() {

    private val contacts by lazy {
        listOf(
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("A_F1", "A_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("B_F1", "B_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("C_F1", "C_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
            TestContact("D_F1", "D_L2"),
        )
    }

    val grouped = contacts.groupBy { it.firstName[0] }

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    fun onNameChange(newName: String) {
        _name.value = newName
        _error.value = newName == "error"
    }

    private val _timeoutText = MutableStateFlow("Timeout!!")
    val timeoutText: StateFlow<String> = _timeoutText

    suspend fun changeTimeoutText(timeoutText: String) {
        withContext(Dispatchers.Main) {
            _timeoutText.emit(timeoutText)
        }
    }
}