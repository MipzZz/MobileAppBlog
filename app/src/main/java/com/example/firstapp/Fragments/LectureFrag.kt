package com.example.firstapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.firstapp.DB.Entities.Account
import com.example.firstapp.DB.Entities.AccountLectureIsRead
import com.example.firstapp.DB.Viewmodels.AccountLecViewModel
import com.example.firstapp.DB.Viewmodels.AccountViewModel
import com.example.firstapp.MicroFragments.HeadFrag
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentLectureBinding


@Suppress("SENSELESS_COMPARISON")
class LectureFrag : Fragment() {

    lateinit var binding: FragmentLectureBinding
    private val lifeData: LifeData by activityViewModels()
    private val dynamicObject: DynamicObjects by activityViewModels()
    private lateinit var mAccountLecViewModel: AccountLecViewModel
    private lateinit var mAccountViewModel: AccountViewModel
    lateinit var lecId: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLectureBinding.inflate(inflater)

        mAccountLecViewModel = ViewModelProvider(this).get(AccountLecViewModel::class.java)
        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        lecId =
            dynamicObject.dynamicModule.value!!.Num.toString() + dynamicObject.dynamicLesson.value!!.Num.toString()




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accId: Int = lifeData.account.value!!.id
        val data = mAccountLecViewModel.checkState(accId,lecId)

        data.asLiveData().observe(activity as LifecycleOwner){
            val res = it?.toString()
            if (res == "true") binding.btRead.visibility = View.INVISIBLE
        }

        if (data.asLiveData().value != null) binding.btRead.visibility = View.INVISIBLE

        binding.btRead.setOnClickListener{
            if (data.asLiveData().value == null) {
                insertReadLecToDatabase(accId, lecId)
                updateProgress()
            }
        }
        lifeData.title.value = "Урок"
        lifeData.state.value = "Урок"

        childFragmentManager.beginTransaction().replace(R.id.frHeadLect, HeadFrag.newInstance())
            .commit()
        binding.txtLecture.text = dynamicObject.dynamicLesson.value!!.Lec

    }

    private fun insertReadLecToDatabase(accountId:Int, lectureId:String){
        val accLec = AccountLectureIsRead(accountId, lectureId,true)
        mAccountLecViewModel.addAccountLec(accLec)
        Toast.makeText(requireContext(), "Read",Toast.LENGTH_LONG).show()
    }

    private fun updateProgress(){
        val accId = lifeData.account.value!!.id
        val firstName = lifeData.account.value!!.firstName
        val lastName = lifeData.account.value!!.lastName
        val phone = lifeData.account.value!!.phone
        val email = lifeData.account.value!!.email
        val password = lifeData.account.value!!.password
        val progress = lifeData.account.value!!.progress + 10f

        val account = Account(accId,firstName,lastName,email, password, phone, progress)

        mAccountViewModel.updateAccount(account)

    }



    companion object {
        @JvmStatic
        fun newInstance() = LectureFrag()
    }
}