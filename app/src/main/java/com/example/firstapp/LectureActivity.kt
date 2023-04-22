package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.example.firstapp.databinding.ActivityLectureBinding



class LectureActivity : AppCompatActivity() {
    lateinit var binding: ActivityLectureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickFin (view: View){
        finish()
    }
}