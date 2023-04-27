package com.example.firstapp.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firstapp.HeadFrag
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentLectureBinding


class LectureFrag : Fragment() {

    lateinit var binding: FragmentLectureBinding
    private val lifeData: LifeData by activityViewModels()
    private val dynamicObject: DynamicObjects by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLectureBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifeData.title.value = "Урок"
        lifeData.state.value = "Урок"

        childFragmentManager.beginTransaction().replace(R.id.frHeadLect, HeadFrag.newInstance()).commit()

        binding.txtLecture.text = dynamicObject.dynamicLesson.value!!.Lec



    }


    companion object {
        @JvmStatic
        fun newInstance() = LectureFrag()
    }
}