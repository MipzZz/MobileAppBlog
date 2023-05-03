package com.example.firstapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapp.LifecycleData.Transition
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityAuthorizationBinding

class AuthorizationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthorizationBinding
    val transition: Transition by viewModels()
    private lateinit var navControllerFirst: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navControllerFirst = findNavController(R.id.fragmentContainerView2)


        transition.goSignIn.observe(this){
            navControllerFirst.navigate(R.id.signInFrag)
        }

        transition.goSignUp.observe(this){
            navControllerFirst.navigate(R.id.signUpFrag)
        }

        transition.goBack.observe(this){
            navControllerFirst.popBackStack()
        }

    }
}