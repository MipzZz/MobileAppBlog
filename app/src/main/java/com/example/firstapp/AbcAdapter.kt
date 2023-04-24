package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.AbcItemBinding


class AbcAdapter():RecyclerView.Adapter<AbcAdapter.AbcHolder>(){
    val abcList = ArrayList<AbcData>()

    class AbcHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = AbcItemBinding.bind(item)
        fun bind (abcItem:AbcData) = with(binding){
            txtLetter.text = abcItem.Letter.toString()
            txtLetDecs.text = abcItem.Desc
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbcHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.abc_item, parent, false)
        return AbcHolder(view)
    }

    override fun getItemCount(): Int {
        return abcList.size
    }

    override fun onBindViewHolder(holder: AbcHolder, position: Int) {
        holder.bind(abcList[position])
    }

    fun addAbc(abcItem:AbcData){
        abcList.add(abcItem)
        notifyDataSetChanged()
    }


}


