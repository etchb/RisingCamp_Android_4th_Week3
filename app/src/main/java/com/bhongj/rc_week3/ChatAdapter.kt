package com.bhongj.rc_week3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_week3.databinding.ChatRecyclerBinding

class ChatAdapter (private val talkListItem: TalkListItem) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private lateinit var binding: ChatRecyclerBinding

    class ViewHolder(binding: ChatRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.talkPageName
        var message: TextView = binding.talkPageMessage
        var date: TextView = binding.talkPageDate
        var img: ImageView = binding.talkPageImg

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(talkListItem: TalkListItem, position: Int) {
            name.text = talkListItem.name
            message.text = talkListItem.message[position]
            date.text = talkListItem.date[position]
            img.setImageResource(talkListItem.imgRsc)

//            println("TEST name : " + talkListItem.name)
//            println("TEST position : " + position.toString())
//            println("TEST message : " + talkListItem.message[position])
//            println("TEST date : " + talkListItem.date[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        binding = ChatRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        println("TEST name : " + talkListItem.name)
        return ChatAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        if (talkListItem.message.size > 0) {
            holder.bind(talkListItem, position)
            println("TEST name : " + talkListItem.name)
//            Toast.makeText(binding.root.context, "DD", Toast.LENGTH_SHORT).show()
//            println(position)
        }
    }

    override fun getItemCount() = talkListItem.message.size
}
