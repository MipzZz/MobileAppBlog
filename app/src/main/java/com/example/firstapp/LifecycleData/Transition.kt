package com.example.firstapp.LifecycleData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class Transition:ViewModel() {

    val goBack: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goToResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goAgain: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}