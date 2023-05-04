package com.example.firstapp.Fragments


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.Adapter.ModulesAdapter
import com.example.firstapp.MicroFragments.HeadFrag
import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData

import com.example.firstapp.ModulesData
import com.example.firstapp.MicroFragments.ProgressBarFrag
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentModuleBinding


class ModuleFrag : Fragment(), ModulesAdapter.Listener {
    lateinit var binding: FragmentModuleBinding
    private val lifeData: LifeData by activityViewModels()
    private val dynamicObject: DynamicObjects by activityViewModels()
    private val ImIdList = listOf(
        R.drawable.plug1,
        R.drawable.plug2,
        R.drawable.plug3,
        R.drawable.plug4,
        R.drawable.plug5,
    )



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModuleBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ModulesAdapter(this)
        val modTitles = resources.getStringArray(R.array.ModTitle)
        val modDesc = resources.getStringArray(R.array.ModDesc)

        lifeData.title.value = "Модули"
        lifeData.state.value = "Модули"

        if (lifeData.progress.value == null) lifeData.progress.value = 0f
        binding.txtProg.text = getString(R.string.progress, lifeData.progress.value?.toInt())


        childFragmentManager.beginTransaction().replace(R.id.frHeadMod, HeadFrag.newInstance()).commit()
        childFragmentManager.beginTransaction().replace(R.id.frProgressLayout, ProgressBarFrag.newInstance()).commit()
        binding.apply {
            rcycModules.layoutManager = LinearLayoutManager(context)
            rcycModules.adapter = adapter
            for (index in 0..modTitles.size - 1) {
                val module = ModulesData(
                    ImIdList[index],
                    modTitles[index],
                    modDesc[index],
                    index
                )
                adapter.addModule(module)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ModuleFrag()
    }


    override fun onClick(module: ModulesData) {
        dynamicObject.dynamicModule.value = module
        }
    }
