package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.databinding.FragmentProgressBarBinding

class ProgressBarFrag : Fragment() {

    lateinit var binding: FragmentProgressBarBinding
    val lifeData: LifeData by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProgressBarBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifeData.progress.observe(activity as LifecycleOwner) {

            binding.progressBar.progress += lifeData.progress.value!!

        }
    }
    companion object {

        fun newInstance() =
            ProgressBarFrag()
    }
}

