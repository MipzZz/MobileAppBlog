package com.example.firstapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels


import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.firstapp.Flags

import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityTestNavBinding

class TestNavActivity : AppCompatActivity() {
    lateinit var binding:ActivityTestNavBinding
    private val flag: Flags by viewModels()
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MyLog","Активити Нав создан")
        navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)
        Log.d("MyLog","Контроллер определен")
        flag.alreadyExistModule.value = true

    }
}