package com.bhongj.rc_week3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bhongj.rc_week3.databinding.ListviewItemBinding

class CustomAdapter(context: Context, private val businessCardArrayList: ArrayList<BusinessCard>):BaseAdapter() {

    private lateinit var binding: ListviewItemBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount() : Int = businessCardArrayList.size

    override fun getItem(p0: Int) : Any = businessCardArrayList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        binding = ListviewItemBinding.inflate(inflater, p2, false)

        binding.listviewTxtName.text = businessCardArrayList[p0].name

        return binding.root
    }
}