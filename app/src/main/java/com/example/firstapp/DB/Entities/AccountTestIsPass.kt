package com.example.firstapp.DB.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "account_test_isPass")
data class AccountTestIsPass (
    val accountId: Int,
    @PrimaryKey
    val testId: Int,
    val isPass: Boolean
)

