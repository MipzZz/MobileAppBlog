package com.example.firstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class LifeData:ViewModel() {

    val title:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val lecture:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val closeLessons:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val closeLec:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


}