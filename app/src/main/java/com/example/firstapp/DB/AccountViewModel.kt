package com.example.firstapp.DB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.firstapp.DB.Entities.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AccountViewModel(application: Application): AndroidViewModel(application) {


    private val repository: AccountRepository
    val readAllData: LiveData<List<Account>>


    init{
        val accountDao = AccountDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(accountDao)
        readAllData = repository.readAllData
    }

    fun readAccountData(id:Int):Flow<Account?> {
        return repository.readAccountData(id)
    }

    fun addAccount(account: Account){
        viewModelScope.launch(Dispatchers.IO){
            repository.addAccount(account)
        }
    }

}