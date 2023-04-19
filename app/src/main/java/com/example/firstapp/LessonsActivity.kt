package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityLessonsBinding
import com.example.firstapp.databinding.ActivityMainBinding

class LessonsActivity : AppCompatActivity() {

    lateinit var binding: ActivityLessonsBinding
    private val adapter = LessonsAdapter()
    private val ImIdList = listOf(
        R.drawable.plug1,
        R.drawable.plug2,
        R.drawable.plug3,
        R.drawable.plug4,
        R.drawable.plug5,
    )
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLessonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@LessonsActivity)
            rcView.adapter = adapter
            for(index in 0..4){
                val lesson = LessonsData(ImIdList[index],
                    "Module $index",
                    "Subtitle of Module $index")
                adapter.addLesson(lesson)
            }
        }
    }

    fun goToModule(view: View){
        val i = Intent(this, LectureActivity::class.java)
        startActivity(i)

    }
}