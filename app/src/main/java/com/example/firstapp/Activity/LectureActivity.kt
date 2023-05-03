package com.example.firstapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.firstapp.MicroFragments.HeadFrag
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityLectureBinding



class LectureActivity : AppCompatActivity() {
    lateinit var binding: ActivityLectureBinding
    private val  lifeData: LifeData by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val lec = intent.getStringExtra("Lec")
        binding.txtLec.text = lec
    }

    override fun onResume() {
        super.onResume()
        lifeData.title.value = "Урок"
        supportFragmentManager.beginTransaction().replace(R.id.frHeadLec, HeadFrag.newInstance()).commit()
    }

    fun onClickFin (view: View){
        finish()
    }
}