package com.example.firstapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.AbcData
import com.example.firstapp.Adapter.AbcAdapter
import com.example.firstapp.Flags
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentAbcBinding


class AbcFrag : Fragment() {
    lateinit var binding: FragmentAbcBinding
    private val adapter = AbcAdapter()
    private val flags: Flags by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbcBinding.inflate(inflater)
        Log.d("MyLog","Запуск фрагмента AbcFrag")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog","Фрагмент создан AbcFrag")
        init()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AbcFrag()
    }
    private fun init(){
        Log.d("MyLog","Инициализация AbcFrag")
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
            Log.d("MyLog","Цикл инициализации AbcFrag закончился")
        }



    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog","Фрагмент AbcFrag закончился")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyLog","Фрагмент вью AbcFrag закончился")
    }


}