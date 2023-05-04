package com.example.firstapp

data class ModulesData(val ImgId:Int, val Title:String, val Subtitle:String, val Num:Int)
data class LessonsData(val ImgId: Int, val Title: String, val Desc: String, val Lec: String, val Num:Int)
data class AbcData (val Letter:Char, val Desc:String)
data class TestsData(val ImgId: Int, val Title: String, val qAmount:String, val Num:Int, val IsDone:Boolean)
data class UserData(val Name:String, val SecondName: String, val Phone: String, val Email: String, val Progress: Float)