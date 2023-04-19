package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ModuleItemBinding

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonsHolder>() {
    val lessonsList = ArrayList<LessonsData>()
    class LessonsHolder(item: View): RecyclerView.ViewHolder(item) {

        val binding = ModuleItemBinding.bind(item)
        fun bind(Lesson:LessonsData) = with(binding){
            imModule.setImageResource(Lesson.ImgId)
            txtMainTitle.text = Lesson.Title
            txtDesc.text = Lesson.Desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.module_item, parent, false)
        return LessonsHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    override fun onBindViewHolder(holder: LessonsHolder, position: Int) {
        holder.bind(lessonsList[position])
    }

    fun addLesson(lesson:LessonsData){
        lessonsList.add(lesson)
        notifyDataSetChanged()
    }
}
