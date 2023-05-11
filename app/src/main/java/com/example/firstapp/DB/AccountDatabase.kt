package com.example.firstapp.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstapp.DB.Dao.AccountDao
import com.example.firstapp.DB.Dao.AccountLectureDao
import com.example.firstapp.DB.Entities.Account
import com.example.firstapp.DB.Entities.AccountLectureIsRead
import com.example.firstapp.DB.Entities.AccountTestIsPass

@Database(entities = [Account::class, AccountLectureIsRead::class, AccountTestIsPass::class], version = 1)
abstract class AccountDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun accountLecDao(): AccountLectureDao

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "account_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}