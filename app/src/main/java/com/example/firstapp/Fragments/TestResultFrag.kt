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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class TestResultFrag : Fragment() {
    lateinit var binding: FragmentTestResultBinding
    val lifeData:LifeData by activityViewModels()
    private val dynamicObjects:DynamicObjects by activityViewModels()
    val transition: Transition by activityViewModels()
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference


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
        val currentState = dynamicObjects.dynamicTest.value
        val state = currentState?.Num.toString()
        mAuth = FirebaseAuth.getInstance()
        mDatabase = Firebase.database.reference
        val currentUser = mAuth.currentUser
        val currentUserUid = currentUser?.uid

        if (score == qAmountInt) {
            lifeData.progress.value = 5f + lifeData.progress.value!!
        }


        binding.btTryAgain.setOnClickListener {
            transition.goAgain.value = true
        }

        binding.btQuit.setOnClickListener{
            transition.goToTests.value = true
        }
    }

    companion object {

        fun newInstance() =
            TestResultFrag()
    }
}