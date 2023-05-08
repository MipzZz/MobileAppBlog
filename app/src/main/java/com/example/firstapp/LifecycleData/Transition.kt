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

    val goSignIn: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goSignUp: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goToTests: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goToStart: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goToWelcome: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val goToProfile: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}