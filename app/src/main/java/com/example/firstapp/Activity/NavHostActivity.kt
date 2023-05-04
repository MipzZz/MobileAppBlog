package com.example.firstapp.Activity


import android.os.Bundle
import android.util.Log
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
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentContainerView)
        binding.bottNavTest.setupWithNavController(navController)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val currentUserUid = currentUser?.uid
        mDatabase = Firebase.database.reference

      // mDatabase.child("/users/$currentUserUid").child("Progress").get().addOnSuccessListener { lifeData.progress.value =
       //    it.value as Float?
      // }





        lifeData.progress.observe(this){
            mDatabase.child("/users/$currentUserUid").child("Progress").setValue(lifeData.progress.value)
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


