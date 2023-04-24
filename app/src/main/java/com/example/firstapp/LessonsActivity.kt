package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityLessonsBinding
import com.example.firstapp.databinding.ActivityMainBinding

class LessonsActivity : AppCompatActivity(), LessonsAdapter.Listener {
    var lesson = LessonsData(0, "", "", "")
    lateinit var binding: ActivityLessonsBinding
    private val adapter = LessonsAdapter(this)
    private val ImIdList = listOf(
        R.drawable.plug1,
        R.drawable.plug2,
        R.drawable.plug3,
        R.drawable.plug4,
        R.drawable.plug5,
    )
    private var index = 0
    private val lifeData: LifeData by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLessonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imBackLess.setOnClickListener {
            finish()
        }
        init()
    }

    override fun onResume() {
        super.onResume()
        lifeData.title.value = "Уроки"
        supportFragmentManager.beginTransaction().replace(R.id.frHead, HeadFrag.newInstance())
            .commit()
    }

    private fun init() {
        val allModTitles = arrayOf(
            resources.getStringArray(R.array.M1MainTitle),
            resources.getStringArray(R.array.M2MainTitle),
            resources.getStringArray(R.array.M3MainTitle),
        )

        val allModDesc = arrayOf(
            resources.getStringArray(R.array.M1MainDesc),
            resources.getStringArray(R.array.M2MainDesc),
            resources.getStringArray(R.array.M3MainDesc)
        )

        val allLec = arrayOf(
            resources.getStringArray(R.array.M1Lecs),
            resources.getStringArray(R.array.M2Lecs),
            resources.getStringArray(R.array.M3Lecs)
        )

        val numModule = intent.getIntExtra("numModule", 999)

        val modTitles = allModTitles[numModule]
        val modDesc = allModDesc[numModule]
        val modLec = allLec[numModule]


        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@LessonsActivity)
            rcView.adapter = adapter
            for (index in 0..modTitles.size - 1) {
                val lesson = LessonsData(
                    ImIdList[index],
                    modTitles[index],
                    modDesc[index],
                    modLec[index]
                )
                adapter.addLesson(lesson)
            }

        }
    }

    override fun onClick(lesson: LessonsData) {
        startActivity(Intent(this, LectureActivity::class.java).apply {
            putExtra("Lec", lesson.Lec)
        })
    }
}
