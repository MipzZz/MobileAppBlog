package com.example.firstapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.Adapter.LessonsAdapter
import com.example.firstapp.MicroFragments.HeadFrag
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LessonsData
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentLessonsBinding


class LessonsFrag : Fragment(), LessonsAdapter.Listener {
    lateinit var binding: FragmentLessonsBinding
    private val lifeData: LifeData by activityViewModels()
    private val dynamicObjects: DynamicObjects by activityViewModels()
    private val imIdList = listOf(
        R.drawable.plug1,
        R.drawable.plug2,
        R.drawable.plug3,
        R.drawable.plug4,
        R.drawable.plug5,
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = LessonsAdapter(this)
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

        val allImage = ""

       val numModule = dynamicObjects.dynamicModule.value!!.Num

        val modTitles = allModTitles[numModule]
        val modDesc = allModDesc[numModule]
        val modLec = allLec[numModule]


        lifeData.title.value = "Уроки"
        lifeData.state.value = "Уроки"

        childFragmentManager.beginTransaction().replace(R.id.frHeadLess, HeadFrag.newInstance()).commit()

        binding.apply {
            rcycLessons.layoutManager = LinearLayoutManager(context)
            rcycLessons.adapter = adapter
            for (index in 0..modTitles.size - 1) {
                val lesson = LessonsData(
                    imIdList[index],
                    modTitles[index],
                    modDesc[index],
                    modLec[index]
                )
                adapter.addLesson(lesson)
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = LessonsFrag()
    }

    override fun onClick(lesson: LessonsData) {
        dynamicObjects.dynamicLesson.value = lesson
    }
}