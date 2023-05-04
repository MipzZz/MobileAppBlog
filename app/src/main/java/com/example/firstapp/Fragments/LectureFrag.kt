package com.example.firstapp.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firstapp.MicroFragments.HeadFrag
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentLectureBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LectureFrag : Fragment() {

    lateinit var binding: FragmentLectureBinding
    private val lifeData: LifeData by activityViewModels()
    private val dynamicObject: DynamicObjects by activityViewModels()
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLectureBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentMod = dynamicObject.dynamicModule.value!!.Num.toString()
        val currentLess = dynamicObject.dynamicLesson.value!!.Num.toString()
        val currentState = currentMod + currentLess
        mAuth = FirebaseAuth.getInstance()
        mDatabase = Firebase.database.reference
        val currentUser = mAuth.currentUser
        val currentUserUid = currentUser?.uid




        lifeData.title.value = "Урок"
        lifeData.state.value = "Урок"

        childFragmentManager.beginTransaction().replace(R.id.frHeadLect, HeadFrag.newInstance())
            .commit()
        binding.txtLecture.text = dynamicObject.dynamicLesson.value!!.Lec

        mDatabase.child("/users/$currentUserUid").child(currentState).get().addOnSuccessListener {

            if (it.value != 0 && it.value != null) {
                binding.btRead.visibility = View.INVISIBLE
            } else {
                binding.btRead.setOnClickListener {

                    lifeData.progress.value = 5f + lifeData.progress.value!!
                    mDatabase.child("/users/$currentUserUid").child(currentState).setValue(1)
                }

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LectureFrag()
    }
}