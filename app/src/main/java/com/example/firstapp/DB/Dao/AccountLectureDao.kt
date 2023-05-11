package com.example.firstapp.DB.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstapp.DB.Entities.Account
import com.example.firstapp.DB.Entities.AccountLectureIsRead
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountLectureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReadLec(LectureIsRead: AccountLectureIsRead)

    @Query("SELECT isRead FROM ACCOUNT_LECTURE_ISREAD WHERE accountId = :AccountId AND lectureId = :LecId")
    fun checkState(AccountId: Int, LecId: String): Flow<Boolean?>

    @Query("SELECT * FROM ACCOUNT_LECTURE_ISREAD")
    fun getAllData(): LiveData<List<AccountLectureIsRead>>
}