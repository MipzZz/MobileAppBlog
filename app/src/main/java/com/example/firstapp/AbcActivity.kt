package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityAbcBinding

class AbcActivity : AppCompatActivity() {
    lateinit var binding: ActivityAbcBinding
    private val adapter = AbcAdapter()
    private val  lifeData: LifeData by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imBackAbc.setOnClickListener {
            finish()
        }
        init()
    }

    override fun onResume() {
        super.onResume()
        lifeData.title.value = "Азбука визажа"
        supportFragmentManager.beginTransaction().replace(R.id.frHeadAbc, HeadFrag.newInstance()).commit()

    }
    private fun init(){
        val abcItems = resources.getStringArray(R.array.Abc)
        binding.apply {
            rcAbc.layoutManager = LinearLayoutManager(this@AbcActivity)
            rcAbc.adapter = adapter
            for(index in 0..abcItems.size-1){
                val abcItem = AbcData(
                    abcItems[index][0],
                    abcItems[index]
                   )
                adapter.addAbc(abcItem)
            }
        }
    }
}