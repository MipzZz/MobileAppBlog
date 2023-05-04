package com.example.firstapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.firstapp.Activity.NavHostActivity
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.UserData
import com.example.firstapp.databinding.FragmentSingUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpFrag : Fragment() {
    lateinit var binding: FragmentSingUpBinding
    val transition: Transition by activityViewModels()
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mDatabase = Firebase.database.reference
        val currentUser = mAuth.currentUser
        val currentUserUid = currentUser?.uid
        val currentUserEmail = currentUser?.email
        binding.imBtBack2.setOnClickListener{
            transition.goBack.value = true
        }

        binding.btRegistr.setOnClickListener{
            if(!binding.edEmailReg.text.toString().isEmpty() and !binding.edPass.text.toString().isEmpty()) {
                mAuth.createUserWithEmailAndPassword(
                    binding.edEmailReg.text.toString(),
                    binding.edPass.text.toString()
                ).addOnCompleteListener{
                    if (it.isSuccessful) {
                        val user = UserData(
                            Name = binding.edName.text.toString(),
                            SecondName = binding.edSurName.text.toString(),
                            Phone = binding.edPhone.text.toString()
                        )
                        mDatabase.child("/users/$currentUserUid").setValue(user)
                        startActivity(Intent(activity, NavHostActivity::class.java))
                    }
                    else Toast.makeText(context,"Что-то не так", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(context,"Введите данные", Toast.LENGTH_LONG).show()
            }
        }

    }

    companion object {

        fun newInstance() =
            SignUpFrag()
    }
}