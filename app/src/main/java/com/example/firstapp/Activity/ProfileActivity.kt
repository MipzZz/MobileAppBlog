package com.example.firstapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val currentUserUid = currentUser?.uid
        val currentUserEmail = currentUser?.email
        mDatabase = Firebase.database.reference

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imBackProf.setOnClickListener {
            finish()
        }

        binding.edTxtEmail.setText(currentUserEmail)
        mDatabase.child("/users/$currentUserUid").child("name").get().addOnSuccessListener { binding.edTxtName.setText(it.value.toString()) }
        mDatabase.child("/users/$currentUserUid").child("secondName").get().addOnSuccessListener { binding.edTxtSecName.setText(it.value.toString()) }
        mDatabase.child("/users/$currentUserUid").child("phone").get().addOnSuccessListener {  binding.edTxtPhone.setText(it.value.toString()) }

        binding.btLogOut.setOnClickListener{
            mAuth.signOut()
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }

    }
}