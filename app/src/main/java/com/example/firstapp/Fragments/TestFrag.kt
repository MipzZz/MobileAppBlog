package com.example.firstapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.Adapter.LessonsAdapter
import com.example.firstapp.Adapter.TestsAdapter
import com.example.firstapp.HeadFrag
import com.example.firstapp.LessonsData
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.TestsData
import com.example.firstapp.databinding.FragmentTestBinding


class TestFrag : Fragment(), TestsAdapter.Listener {

    lateinit var binding: FragmentTestBinding
    private val lifeData: LifeData by activityViewModels()
    private val dynamicObject: DynamicObjects by activityViewModels()
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
    ): View {
        binding = FragmentTestBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Общий массив тестов





        lifeData.state.value = "Тесты"
        lifeData.title.value = "Тесты"

        val adapter = TestsAdapter(this)

        val testTitles = resources.getStringArray(R.array.testTitles)
        val testQAmount = resources.getStringArray(R.array.qAmount)

        childFragmentManager.beginTransaction().replace(R.id.frHeadTests, HeadFrag.newInstance()).commit()

        binding.apply {
            rcycTests.layoutManager = GridLayoutManager(context,2)
            rcycTests.adapter = adapter
            for (index in 0..testTitles.size - 1) {
                val test = TestsData(
                    imIdList[index],
                    testTitles[index],
                    testQAmount[index],
                    index
                )
                adapter.addTest(test)
            }

        }
    }

    override fun onClick(test: TestsData) {
        dynamicObject.dynamicTest.value = test
    }

    companion object {
        @JvmStatic
        fun newInstance() = TestFrag()
    }
}