package com.example.firstapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapp.LifecycleData.Transition
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityAuthorizationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AuthorizationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthorizationBinding
    val transition: Transition by viewModels()
    val dynamicObjects: DynamicObjects by viewModels()
    private lateinit var navControllerFirst: NavController
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = Firebase.database.reference
        val currentUser = mAuth.currentUser
        if (currentUser != null) startActivity(Intent(this, NavHostActivity::class.java))
        navControllerFirst = findNavController(R.id.fragmentContainerView2)
        val currentUserUid = currentUser?.uid



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