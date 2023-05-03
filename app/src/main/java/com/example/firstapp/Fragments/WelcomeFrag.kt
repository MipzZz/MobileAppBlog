package com.example.firstapp.Fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentWelcomeBinding


class WelcomeFrag : Fragment() {
    lateinit var binding: FragmentWelcomeBinding
    val transition: Transition by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btSignIn.setOnClickListener {
            transition.goSignIn.value = true
        }

        binding.btSignUp.setOnClickListener {
            transition.goSignUp.value = true
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            WelcomeFrag()
    }
}