package com.example.firstapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.firstapp.DB.Entities.AccountTestIsPass
import com.example.firstapp.DB.Viewmodels.AccountLecViewModel
import com.example.firstapp.DB.Viewmodels.AccountTestViewModel
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
    lateinit var mAccountTestViewModel: AccountTestViewModel
    lateinit var testId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val score = lifeData.testScore.value
        val qAmountStr = dynamicObjects.dynamicTest.value!!.qAmount
        val qAmountInt = qAmountStr.substring(0,qAmountStr.indexOf(" ")).toInt()

        binding = FragmentTestResultBinding.inflate(inflater)
        mAccountTestViewModel = ViewModelProvider(this).get(AccountTestViewModel::class.java)
        testId = dynamicObjects.dynamicTest.value!!.Num.toString()

        val accId: Int = lifeData.account.value!!.id
        val data = mAccountTestViewModel.checkTestState(accId,testId)

        if (score == qAmountInt) {
            if (data.asLiveData().value == null) {
                insertTestToDatabase(accId,testId)
            }
            lifeData.progress.value = 5f + lifeData.progress.value!!
        }
        binding.txtResultScore.text = getString(
            R.string.result_score,
            score,
            qAmountInt
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btTryAgain.setOnClickListener {
            transition.goAgain.value = true
        }

        binding.btQuit.setOnClickListener{
            transition.goToTests.value = true
        }
    }

    private fun insertTestToDatabase(accountId:Int, testId:String){
        val accTest = AccountTestIsPass(accountId, testId,true)
        mAccountTestViewModel.addAccountTest(accTest)
        Toast.makeText(requireContext(),"Тест пройден", Toast.LENGTH_LONG).show()
    }

    companion object {

        fun newInstance() =
            TestResultFrag()
    }
}