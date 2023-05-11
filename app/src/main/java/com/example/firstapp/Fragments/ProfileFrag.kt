package com.example.firstapp.Fragments


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
import com.example.firstapp.LifecycleData.Transition
import com.example.firstapp.databinding.FragmentProfileBinding



class ProfileFrag : Fragment() {
    lateinit var binding: FragmentProfileBinding
    val transition: Transition by activityViewModels()
    val lifeData: LifeData by activityViewModels()
    lateinit var mAccountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btProfileBack.setOnClickListener {
           parentFragmentManager.popBackStack()
        }

        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        val data = mAccountViewModel.readAccountData(lifeData.account.value!!.id)
        data.asLiveData().observe(activity as LifecycleOwner){
            binding.edTxtName.setText(it?.firstName)
            binding.edTxtSecName.setText(it?.lastName)
            binding.edTxtEmail.setText(it?.email)
            binding.edTxtPhone.setText(it?.phone)
        }

        binding.btLogOut.setOnClickListener{
            transition.goToWelcome.value = true
        }
    }


}
