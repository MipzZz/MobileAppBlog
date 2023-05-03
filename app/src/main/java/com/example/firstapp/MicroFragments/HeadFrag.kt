package com.example.firstapp.MicroFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.firstapp.Activity.ProfileActivity
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.databinding.FragmentHeadBinding


class HeadFrag : Fragment() {
    private val lifeData: LifeData by activityViewModels()
    private val transition: Transition by activityViewModels()
    lateinit var binding: FragmentHeadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        binding.imBtBack.setOnClickListener{
            transition.goBack.value = true
        }

        if (lifeData.state.value == "Модули" || lifeData.state.value == "Тесты" || lifeData.state.value == "Азбука Визажа"){
            binding.imBtBack.visibility = View.INVISIBLE
        }
        else{
            binding.imBtBack.visibility = View.VISIBLE
        }

    }





    companion object {

        @JvmStatic
        fun newInstance() = HeadFrag()
    }


}
