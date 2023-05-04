package com.example.firstapp.MicroFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.databinding.FragmentProgressBarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProgressBarFrag : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
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

        mDatabase = Firebase.database.reference
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val currentUserUid = currentUser?.uid
        mDatabase.child("/users/$currentUserUid").child("Progress").setValue(binding.progressBar.progress)
        lifeData.progress.observe(activity as LifecycleOwner) {
            binding.progressBar.progress += lifeData.progress.value!!

        }
    }
    companion object {

        fun newInstance() =
            ProgressBarFrag()
    }
}

