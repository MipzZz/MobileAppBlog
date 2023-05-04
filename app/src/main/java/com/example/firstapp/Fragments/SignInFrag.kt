package com.example.firstapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firstapp.Activity.NavHostActivity
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFrag : Fragment() {
    lateinit var binding: FragmentSignInBinding
    val transition: Transition by activityViewModels()
    private lateinit var mAuth:FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imBtBack3.setOnClickListener{
            transition.goBack.value = true
        }

        binding.btLogin.setOnClickListener{
            if(!binding.edEmail.text.toString().isEmpty() and !binding.edPassword.text.toString().isEmpty()) {
                mAuth.signInWithEmailAndPassword(
                    binding.edEmail.text.toString(),
                    binding.edPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) startActivity(Intent(activity, NavHostActivity::class.java))
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
            SignInFrag()
    }
}