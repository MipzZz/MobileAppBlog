package com.example.firstapp.DB.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "account_Lecture_isRead")
data class AccountLectureIsRead (
    val accountId: Int,
    @PrimaryKey
    val lectureId: String,
    val isRead: Boolean
)