package com.example.firstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DynamicObjects:ViewModel() {

    val dynamicModule: MutableLiveData<ModulesData> by lazy {
        MutableLiveData<ModulesData>()
    }

}