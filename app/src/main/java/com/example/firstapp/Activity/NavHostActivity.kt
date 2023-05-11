package com.example.firstapp.Activity


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityTestNavBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NavHostActivity : AppCompatActivity(){
    lateinit var binding:ActivityTestNavBinding
    private val dynamicObject: DynamicObjects by viewModels()
    private val lifeData: LifeData by viewModels()
    private val transition:Transition by viewModels()
    private lateinit var navController:NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentContainerView)
        binding.bottNavTest.setupWithNavController(navController)

        binding.bottNavTest.visibility = View.GONE

        lifeData.bottomNavVisibility.observe(this){
            binding.bottNavTest.visibility = View.VISIBLE
        }
        transition.goToProfile.observe(this){
            navController.navigate(R.id.profileFrag)
        }

        transition.goToStart.observe(this){
            navController.navigate(R.id.moduleFrag)
        }

        transition.goSignIn.observe(this){
            navController.navigate(R.id.signInFrag)
        }

        transition.goSignUp.observe(this){
            navController.navigate(R.id.signUpFrag)
        }

        transition.goBack.observe(this){
            navController.popBackStack()
        }

        dynamicObject.dynamicModule.observe(this){
            navController.navigate(R.id.lessonsFrag)
        }

        dynamicObject.dynamicLesson.observe(this){
            navController.navigate(R.id.lectureFrag)
        }

        dynamicObject.dynamicTest.observe(this){
            navController.navigate(R.id.quizFrag)
        }

        transition.goBack.observe(this){
            navController.popBackStack()
        }

        transition.goToResult.observe(this){
            navController.navigate(R.id.testResultFrag)
        }

        transition.goAgain.observe(this){
            navController.navigate(R.id.quizFrag)
        }

        transition.goToTests.observe(this){
            navController.navigate(R.id.testFrag)
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


