package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ModulesAdapter.Listener {

    lateinit var binding: ActivityMainBinding
    private val adapter = ModulesAdapter(this)
    private val ImIdList = listOf(
        R.drawable.plug1,
        R.drawable.plug2,
        R.drawable.plug3,
        R.drawable.plug4,
        R.drawable.plug5,
    )

    private var index = 0
    private val  lifeData: LifeData by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    override fun onResume() {
        super.onResume()
        lifeData.title.value = "Модули"
        supportFragmentManager.beginTransaction().replace(R.id.frHead, HeadFrag.newInstance()).commit()
    }

    private fun init(){
        val modTitles = resources.getStringArray(R.array.ModTitle)
        val modDesc = resources.getStringArray(R.array.ModDesc)
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
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


    fun goToProfile(view:View){
        val i = Intent(this, ProfileActivity::class.java)
        startActivity(i)
    }

    override fun onClick(module: ModulesData) {
        startActivity(Intent(this, LessonsActivity::class.java).apply {
            putExtra("numModule", module.Num)
        })
    }
}