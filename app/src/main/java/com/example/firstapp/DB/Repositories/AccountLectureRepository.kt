package com.example.firstapp.DB.Repositories

import androidx.lifecycle.LiveData
import com.example.firstapp.DB.Dao.AccountLectureDao
import com.example.firstapp.DB.Entities.Account
import com.example.firstapp.DB.Entities.AccountLectureIsRead
import kotlinx.coroutines.flow.Flow

class AccountLectureRepository(private val accountLectureDao: AccountLectureDao) {

    val readAllData: LiveData<List<AccountLectureIsRead>> = accountLectureDao.getAllData()


    suspend fun addReadLec(LectureIsRead:AccountLectureIsRead){
        accountLectureDao.addReadLec(LectureIsRead)
    }

    fun checkState(AccountId: Int, LecId: String): Flow<Boolean?> {
        return accountLectureDao.checkState(AccountId, LecId)
    }

}