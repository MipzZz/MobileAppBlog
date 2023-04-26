package com.example.firstapp.LifecycleData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapp.LessonsData
import com.example.firstapp.ModulesData

class DynamicObjects:ViewModel() {

    val dynamicModule: MutableLiveData<ModulesData> by lazy {
        MutableLiveData<ModulesData>()
    }

    val dynamicLesson: MutableLiveData<LessonsData> by lazy {
        MutableLiveData<LessonsData>()
    }

}