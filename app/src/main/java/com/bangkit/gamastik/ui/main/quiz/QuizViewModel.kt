package com.bangkit.gamastik.ui.main.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Quiz Fragment"
    }
    val text: LiveData<String> = _text
}