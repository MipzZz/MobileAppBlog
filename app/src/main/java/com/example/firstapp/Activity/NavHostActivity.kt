package com.example.firstapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels


import androidx.navigation.NavController
import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController

import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.Transition


import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityTestNavBinding

class NavHostActivity : AppCompatActivity(){
    lateinit var binding:ActivityTestNavBinding
    private val dynamicObject: DynamicObjects by viewModels()
    private val transition:Transition by viewModels()
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentContainerView)
        binding.bottNavTest.setupWithNavController(navController)

        dynamicObject.dynamicModule.observe(this){
            navController.navigate(R.id.lessonsFrag)
        }

        transition.goBack.observe(this){
            navController.popBackStack()
        }


        binding.bottNavTest.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.moduleFrag -> {
                    navController.navigate(R.id.moduleFrag)
                    true
                }

                R.id.abcFrag -> {
                    navController.navigate(R.id.abcFrag)
                    true
                }

                R.id.testFrag -> {
                    navController.navigate(R.id.testFrag)
                    true
                }

                else -> {
                    Log.i("NavBar", "Error?")
                    false
                }
            }

        }
    }
}


