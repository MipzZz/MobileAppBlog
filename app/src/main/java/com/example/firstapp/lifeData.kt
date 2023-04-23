package com.example.firstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class lifeData:ViewModel() {

    val title:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val lecture:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


}