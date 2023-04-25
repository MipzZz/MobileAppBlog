package com.example.firstapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentAbcBinding


class AbcFrag : Fragment() {
    lateinit var binding: FragmentAbcBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbcBinding.inflate(inflater)
        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = AbcFrag()
    }

}