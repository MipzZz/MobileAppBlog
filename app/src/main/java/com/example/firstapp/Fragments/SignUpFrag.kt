package com.example.firstapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.firstapp.Activity.NavHostActivity
import com.example.firstapp.Activity.ProfileActivity
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.databinding.FragmentSingUpBinding

class SignUpFrag : Fragment() {
    lateinit var binding: FragmentSingUpBinding
    val transition: Transition by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imBtBack2.setOnClickListener{
            transition.goBack.value = true
        }

        binding.btRegistr.setOnClickListener{
            startActivity(Intent(activity, NavHostActivity::class.java))
        }

    }

    companion object {

        fun newInstance() =
            SignUpFrag()
    }
}