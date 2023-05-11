package com.example.firstapp.MicroFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.firstapp.DB.Viewmodels.AccountViewModel
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.databinding.FragmentProgressBarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProgressBarFrag : Fragment() {
    lateinit var binding: FragmentProgressBarBinding
    val lifeData: LifeData by activityViewModels()
    lateinit var mAccountViewModel: AccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBarBinding.inflate(inflater)
        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = mAccountViewModel.readAccountData(lifeData.account.value!!.id)
        data.asLiveData().observe(activity as LifecycleOwner){
            binding.progressBar.progress = it!!.progress
            lifeData.progress.value = it.progress
        }
    }
    companion object {

        fun newInstance() =
            ProgressBarFrag()
    }
}

