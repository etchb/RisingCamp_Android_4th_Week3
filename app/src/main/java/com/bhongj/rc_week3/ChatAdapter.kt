package com.bhongj.rc_week3

import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            date.text = chatSdf.format(talkListItem.date[position])
            // talkListItem.isMineFlag[postion]
            img.setImageResource(talkListItem.imgRsc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        binding = ChatRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        if (talkListItem.message.size > 0) {
            holder.bind(talkListItem, position)
//            Toast.makeText(binding.root.context, "DD", Toast.LENGTH_SHORT).show()
//            println(position)
            binding.talkPageMessage.setOnLongClickListener { v ->
                val builder = AlertDialog.Builder(v.context)
//                builder.setTitle("")
                builder.setMessage("해당 메시지를 삭제하시겠습니가?")
                builder.setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                    talkListItem.message.removeAt(position)
                    talkListItem.date.removeAt(position)
                    talkListItem.isMineFlag.removeAt(position)
                    if (talkListItem.message.size == 0) {
                        (binding.root.context as Activity).finish()
                    }
                    else {
                        notifyItemRemoved(position)
                    }
                }
                builder.setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->

                }
                builder.show()
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount() = talkListItem.message.size
}
