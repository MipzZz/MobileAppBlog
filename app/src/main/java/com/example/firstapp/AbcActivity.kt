package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityAbcBinding

class AbcActivity : AppCompatActivity() {
    lateinit var binding: ActivityAbcBinding
    private val adapter = AbcAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        val abcItems = resources.getStringArray(R.array.Abc)
        binding.apply {
            rcAbc.layoutManager = LinearLayoutManager(this@AbcActivity)
            rcAbc.adapter = adapter
            for(index in 0..abcItems.size-1){
                val abcItem = AbcData(
                    abcItems[index][1],
                    abcItems[index]
                   )
                adapter.addAbc(abcItem)
            }
        }
    }
}