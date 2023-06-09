package com.example.firstapp.DB.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.firstapp.DB.Entities.Account
import com.example.firstapp.DB.Entities.AccountSignInTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAccount(account: Account)

    @Query("SELECT * FROM accounts_table WHERE id = :accountId")
    fun readAccountData(accountId:Int): Flow<Account?>

    @Query("SELECT * FROM ACCOUNTS_TABLE ORDER BY id ASC")
    fun getAllAccounts(): LiveData<List<Account>>

    @Update
    suspend fun updateAccount(account: Account)

}