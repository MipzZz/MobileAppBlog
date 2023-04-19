package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ModuleItemBinding

class ModulesAdapter: RecyclerView.Adapter<ModulesAdapter.ModulesHolder>() {
    val moduleList = ArrayList<ModulesData>()
    class ModulesHolder(item: View): RecyclerView.ViewHolder(item) {

        val binding = ModuleItemBinding.bind(item)
        fun bind(Module:ModulesData) = with(binding){
            imModule.setImageResource(Module.ImgId)
            txtMainTitle.text = Module.Title
            txtDesc.text = Module.Subtitle

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModulesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.module_item, parent, false)
        return ModulesHolder(view)
    }

    override fun getItemCount(): Int {
        return moduleList.size
    }

    override fun onBindViewHolder(holder: ModulesHolder, position: Int) {
        holder.bind(moduleList[position])
    }

    fun addModule(module:ModulesData){
        moduleList.add(module)
        notifyDataSetChanged()
    }
}