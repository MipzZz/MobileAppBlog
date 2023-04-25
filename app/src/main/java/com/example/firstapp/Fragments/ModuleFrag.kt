package com.example.firstapp.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.Adapter.ModulesAdapter
import com.example.firstapp.Flags
import com.example.firstapp.ModulesData
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentModuleBinding


class ModuleFrag : Fragment(), ModulesAdapter.Listener {
    lateinit var binding: FragmentModuleBinding
    private val adapter = ModulesAdapter(this@ModuleFrag)
    private val flagExist: Flags by activityViewModels()
    private val ImIdList = listOf(
        R.drawable.plug1,
        R.drawable.plug2,
        R.drawable.plug3,
        R.drawable.plug4,
        R.drawable.plug5,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModuleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ModuleFrag()
    }

    private fun init(){
            val modTitles = resources.getStringArray(R.array.ModTitle)
            val modDesc = resources.getStringArray(R.array.ModDesc)
            binding.apply {
                rcycModules.layoutManager = LinearLayoutManager(context)
                rcycModules.adapter = adapter
                for(index in 0..modTitles.size-1){
                    val module = ModulesData(
                        ImIdList[index],
                        modTitles[index],
                        modDesc[index],
                        index)
                    adapter.addModule(module)
                }
           }
    }

    override fun onClick(module: ModulesData) {
        Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()
        }
    }
