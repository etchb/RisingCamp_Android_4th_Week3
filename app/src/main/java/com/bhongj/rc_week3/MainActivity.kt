package com.bhongj.rc_week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhongj.rc_week3.databinding.ActivityMainBinding

data class BusinessCard(val name:String, val contents:String)

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var businessCardArrayList = ArrayList<BusinessCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for(i in 1..10) {
            businessCardArrayList.add(BusinessCard(name = "kim", contents = "010-1234-1234"))
            businessCardArrayList.add(BusinessCard(name = "park", contents = "010-4567-4567"))
        }

        val customAdapter = CustomAdapterMain(this, businessCardArrayList)
        binding.listviewMain.adapter = customAdapter
    }
}