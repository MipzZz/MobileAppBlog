package com.example.firstapp.LifecycleData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapp.LessonsData
import com.example.firstapp.ModulesData
import com.example.firstapp.TestsData
import com.example.firstapp.UserData

class DynamicObjects:ViewModel() {

    val dynamicModule: MutableLiveData<ModulesData> by lazy {
        MutableLiveData<ModulesData>()
    }

    val dynamicLesson: MutableLiveData<LessonsData> by lazy {
        MutableLiveData<LessonsData>()
    }

    val dynamicTest: MutableLiveData<TestsData> by lazy {
        MutableLiveData<TestsData>()
    }

    val dynamicUser: MutableLiveData<UserData> by lazy {
        MutableLiveData<UserData>()
    }


}