package com.example.firstapp.DB

import android.accounts.AuthenticatorException
import androidx.lifecycle.LiveData
import com.example.firstapp.DB.Entities.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepository(private val accountDao: AccountDao) {


    val readAllData: LiveData<List<Account>> = accountDao.getAllAccounts()

    fun readAccountData(id:Int): Flow<Account?>{
        return accountDao.readAccountData(id)
    }

    suspend fun addAccount(account: Account){
        accountDao.addAccount(account)
    }


}