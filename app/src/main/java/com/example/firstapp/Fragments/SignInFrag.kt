package com.example.firstapp.Fragments

import android.accounts.Account
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.firstapp.Activity.NavHostActivity
import com.example.firstapp.DB.AccountDatabase
import com.example.firstapp.DB.AccountViewModel
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFrag : Fragment() {
    lateinit var mAccountViewModel: AccountViewModel
    lateinit var binding: FragmentSignInBinding
    val transition: Transition by activityViewModels()
    val lifeData: LifeData by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        val data = mAccountViewModel.readAllData
        binding.imBtBack3.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        binding.btLogin.setOnClickListener{
            data.observe(activity as LifecycleOwner){AccountsList ->
                AccountsList.forEach{ account ->
                    if (account.email == binding.edEmail.text.toString()
                        && account.password == binding.edPassword.text.toString()) {
                        transition.goToStart.value = true
                        lifeData.account.value = account
                    }
                }

            }
        }
    }

    companion object {

        fun newInstance() =
            SignInFrag()
    }
}