package com.example.firstapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.TestsData
import com.example.firstapp.databinding.TestItemBinding

class TestsAdapter(val listener: Listener): RecyclerView.Adapter<TestsAdapter.TestsHolder>() {
    val testsList = ArrayList<TestsData>()
    class TestsHolder(item: View): RecyclerView.ViewHolder(item) {

        val binding = TestItemBinding.bind(item)
        fun bind(test: TestsData, listener: Listener) = with(binding){
            imPrevTest.setImageResource(test.ImgId)
            txtTestTitle.text = test.Title
            txtNumQ.text = test.qAmount
            imStart.setOnClickListener{
                listener.onClick(test)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_item, parent, false)
        return TestsHolder(view)
    }

    override fun getItemCount(): Int {
        return testsList.size
    }

    override fun onBindViewHolder(holder: TestsHolder, position: Int) {
        holder.bind(testsList[position], listener)
    }

    fun addTest(test: TestsData){
        testsList.add(test)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(test: TestsData)
    }
}