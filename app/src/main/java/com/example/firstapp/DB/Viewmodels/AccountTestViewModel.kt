package com.example.firstapp.DB.Viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.DB.AccountDatabase
import com.example.firstapp.DB.Entities.AccountTestIsPass
import com.example.firstapp.DB.Repositories.AccountTestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AccountTestViewModel (application: Application): AndroidViewModel(application) {

    private val repository: AccountTestRepository

    init{
        val accountTestDao = AccountDatabase.getDatabase(application).accountTestDao()
        repository = AccountTestRepository(accountTestDao)
    }

    fun addAccountTest(accountTest: AccountTestIsPass) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPassedTest(accountTest)
        }
    }

    fun checkTestState(AccountId: Int, TestId: String): Flow<Boolean?> {
        return repository.checkTestState(AccountId, TestId)
    }

}