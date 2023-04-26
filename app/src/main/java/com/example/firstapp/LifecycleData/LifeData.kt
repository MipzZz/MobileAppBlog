package com.example.firstapp.LifecycleData

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class LifeData:ViewModel() {

    val title:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val state: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }


}