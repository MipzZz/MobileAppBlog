//package com.example.firstapp
//
//import android.content.ClipData.Item
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface Dao {
//    @Insert
//    fun insertItem(item: Item)
//
//    @Query("SELECT * FROM Users")
//    fun getAllItems(): Flow<List<Item>>
//}