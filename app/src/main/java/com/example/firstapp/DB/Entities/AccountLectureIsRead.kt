package com.example.firstapp.DB.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "account_Lecture_isRead",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"]
        )
    ]
)
data class AccountLectureIsRead (
    @PrimaryKey
    val accountId: Int,
    val lectureId: Int,
    val isRead: Boolean
)