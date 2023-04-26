package com.example.firstapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.AbcData
import com.example.firstapp.Adapter.AbcAdapter
import com.example.firstapp.HeadFrag
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentAbcBinding


class AbcFrag : Fragment() {
    lateinit var binding: FragmentAbcBinding

    private val lifeData: LifeData by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbcBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifeData.state.value = "Азбука Визажа"
        lifeData.title.value = "Азбука Визажа"

        childFragmentManager.beginTransaction().replace(R.id.frHeadAbc, HeadFrag.newInstance()).commit()

        val adapter = AbcAdapter()
        val abcItems = resources.getStringArray(R.array.Abc)
        binding.apply {
            rcycAbc.layoutManager = LinearLayoutManager(context)
            rcycAbc.adapter = adapter
            for(index in 0..abcItems.size-1){
                val abcItem = AbcData(
                    abcItems[index][0],
                    abcItems[index])
                adapter.addAbc(abcItem)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AbcFrag()
    }

}