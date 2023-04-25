package com.example.firstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Flags:ViewModel() {

    val alreadyExistModule: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}