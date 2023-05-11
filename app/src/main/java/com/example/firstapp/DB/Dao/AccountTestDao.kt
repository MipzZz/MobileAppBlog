package com.example.firstapp.DB.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstapp.DB.Entities.AccountTestIsPass
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountTestDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPassedTest(TestIsPassed: AccountTestIsPass)

    @Query("SELECT isPass FROM ACCOUNT_TEST_ISPASS WHERE accountId = :AccountId AND testId = :TestId")
    fun checkTestState(AccountId: Int, TestId: String): Flow<Boolean?>

}