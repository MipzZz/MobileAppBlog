package com.example.firstapp.DB.Repositories

import com.example.firstapp.DB.Dao.AccountLectureDao
import com.example.firstapp.DB.Dao.AccountTestDao
import com.example.firstapp.DB.Entities.AccountLectureIsRead
import com.example.firstapp.DB.Entities.AccountTestIsPass
import kotlinx.coroutines.flow.Flow

class AccountTestRepository(private val accountTestDao: AccountTestDao) {

    suspend fun addPassedTest(TestIsPass:AccountTestIsPass){
        accountTestDao.addPassedTest(TestIsPass)
    }

    fun checkTestState(AccountId: Int, TestId: String): Flow<Boolean?> {
        return accountTestDao.checkTestState(AccountId, TestId)
    }
}