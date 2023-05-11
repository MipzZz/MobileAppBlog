package com.example.firstapp.DB.Viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.firstapp.DB.AccountDatabase
import com.example.firstapp.DB.Entities.AccountLectureIsRead
import com.example.firstapp.DB.Repositories.AccountLectureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class AccountLecViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AccountLectureRepository
    val readAllData: LiveData<List<AccountLectureIsRead>>

    init {
        val accountLecDao = AccountDatabase.getDatabase(application).accountLecDao()
        repository = AccountLectureRepository(accountLecDao)
        readAllData = repository.readAllData
    }

    fun addAccountLec(accountLec: AccountLectureIsRead) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addReadLec(accountLec)
        }
    }

    fun checkState(AccountId: Int, LecId: String): Flow<Boolean?> {
        return repository.checkState(AccountId,LecId)
    }
}