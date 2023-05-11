package com.example.firstapp.DB.Repositories

import androidx.lifecycle.LiveData
import com.example.firstapp.DB.Dao.AccountDao
import com.example.firstapp.DB.Entities.Account
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val accountDao: AccountDao) {


    val readAllData: LiveData<List<Account>> = accountDao.getAllAccounts()

    fun readAccountData(id:Int): Flow<Account?>{
        return accountDao.readAccountData(id)
    }
    suspend fun addAccount(account: Account){
        accountDao.addAccount(account)
    }

    suspend fun updateAccount(account: Account){
        accountDao.updateAccount(account)
    }

}