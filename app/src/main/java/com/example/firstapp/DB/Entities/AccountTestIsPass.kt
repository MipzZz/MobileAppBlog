package com.example.firstapp.DB.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "account_test_isPass",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"]
        )
    ])
data class AccountTestIsPass (
    @PrimaryKey
    val accountId: Int,
    val testId: Int,
    val isPass: Boolean
)

