package com.example.firstapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.firstapp.Activity.NavHostActivity
import com.example.firstapp.DB.AccountDao
import com.example.firstapp.DB.AccountDatabase
import com.example.firstapp.DB.Entities.Account
import com.example.firstapp.DB.AccountViewModel
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.databinding.FragmentSingUpBinding

class SignUpFrag : Fragment() {
    lateinit var mAccountViewModel: AccountViewModel
    lateinit var binding: FragmentSingUpBinding
    val transition: Transition by activityViewModels()
    val lifeData: LifeData by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(inflater)

        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        binding.btRegistr.setOnClickListener{
            insertDataToDatabase()
            transition.goToStart.value = true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imBtBack2.setOnClickListener{
            parentFragmentManager.popBackStack()
        }


    }

    private fun insertDataToDatabase() {
        val firstName = binding.edName.text.toString()
        val lastName = binding.edSurName.text.toString()
        val phone = binding.edPhone.text.toString()
        val email = binding.edEmailReg.text.toString()
        val password = binding.edPass.text.toString()

        if(inputCheck(firstName,lastName)){
            // Create Account Object
            val account = Account(0,firstName,lastName,email, password, phone, 0f)

            // Add Data in Database
            mAccountViewModel.addAccount(account)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            transition.goSignIn.value = true
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName:String, lastName:String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }

    companion object {

        fun newInstance() =
            SignUpFrag()
    }
}