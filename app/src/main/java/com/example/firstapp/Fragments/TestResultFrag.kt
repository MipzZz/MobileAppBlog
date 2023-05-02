package com.example.firstapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentTestResultBinding


class TestResultFrag : Fragment() {
    lateinit var binding: FragmentTestResultBinding
    val lifeData:LifeData by activityViewModels()
    private val dynamicObjects:DynamicObjects by activityViewModels()
    val transition: Transition by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val score = lifeData.testScore.value
        val qAmountStr = dynamicObjects.dynamicTest.value!!.qAmount
        val qAmountInt = qAmountStr.substring(0,qAmountStr.indexOf(" ")).toInt()
        binding.txtResultScore.text = getString(
            R.string.result_score,
            score,
            qAmountInt
        )

        binding.btTryAgain.setOnClickListener {
            transition.goAgain.value = true
        }
    }

    companion object {

        fun newInstance() =
            TestResultFrag()
    }
}