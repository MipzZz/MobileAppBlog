package com.example.firstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Transitions:ViewModel() {

    val navToLesson: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}