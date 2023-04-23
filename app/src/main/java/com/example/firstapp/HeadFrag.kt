package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.firstapp.databinding.FragmentHeadBinding


class HeadFrag : Fragment() {
    private val lifeData: LifeData by activityViewModels()
    lateinit var binding: FragmentHeadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifeData.title.observe(activity as LifecycleOwner) {
            binding.txtHead.text = it
        }

        binding.imProfile.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }

    }





    companion object {

        @JvmStatic
        fun newInstance() = HeadFrag()
    }


}
