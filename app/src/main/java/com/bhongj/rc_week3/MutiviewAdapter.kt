package com.bhongj.rc_week3

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_week3.databinding.ChatRecyclerBinding
import com.bhongj.rc_week3.databinding.ChatRecyclerHeaderBinding
import com.bhongj.rc_week3.databinding.ChatRecyclerRightBinding
import java.text.SimpleDateFormat
import java.util.*

class MutiviewAdapter(private val context: Context, private val talkListItem: TalkListItem) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: ChatRecyclerBinding
    lateinit var bindingRight: ChatRecyclerRightBinding
    lateinit var bindingHeader: ChatRecyclerHeaderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> {
                binding = ChatRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MutiviewAdapter.MultiViewHolder1(binding)
            }
            1 -> {
                bindingRight = ChatRecyclerRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MutiviewAdapter.MultiViewHolder2(bindingRight)
            }
            else -> {
                bindingHeader = ChatRecyclerHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MutiviewAdapter.MultiViewHolder3(bindingHeader)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (talkListItem.message.size > 0) {
            when (talkListItem.isMineFlag[position]) {
                0 -> {
                    (holder as MultiViewHolder1).bind(talkListItem, position)
                    holder.setIsRecyclable(false)
                    binding.talkPageMessage.setOnLongClickListener { v ->
                        val builder = AlertDialog.Builder(v.context)
                        builder.setMessage("해당 메시지를 삭제하시겠습니가?")
                        builder.setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                            talkListItem.message.removeAt(position)
                            talkListItem.date.removeAt(position)
                            talkListItem.isMineFlag.removeAt(position)
                            if (talkListItem.message.size == 0) {
                                (binding.root.context as Activity).finish()
                            }
                            else {
                                notifyDataSetChanged()
                            }
                        }
                        builder.setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->

                        }
                        builder.show()
                        return@setOnLongClickListener true
                    }
                }
                1 -> {
                    (holder as MultiViewHolder2).bind(talkListItem, position)
                    holder.setIsRecyclable(false)
                    bindingRight.talkPageMessage.setOnLongClickListener { v ->
                        val builder = AlertDialog.Builder(v.context)
                        builder.setMessage("해당 메시지를 삭제하시겠습니가?")
                        builder.setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                            talkListItem.message.removeAt(position)
                            talkListItem.date.removeAt(position)
                            talkListItem.isMineFlag.removeAt(position)
                            if (talkListItem.message.size == 0) {
                                (bindingRight.root.context as Activity).finish()
                            }
                            else {
                                notifyDataSetChanged()
                            }
                        }
                        builder.setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->

                        }
                        builder.show()
                        return@setOnLongClickListener true
                    }
                }
                else -> {
                    (holder as MultiViewHolder3).bind(talkListItem, position)
                    holder.setIsRecyclable(false)
                }
            }
        }
    }

    class MultiViewHolder1(binding: ChatRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        var name: TextView = binding.talkPageName
        var message: TextView = binding.talkPageMessage
        var date: TextView = binding.talkPageDate
        var img: ImageView = binding.talkPageImg

        fun bind(talkListItem: TalkListItem, position: Int) {
            name.text = talkListItem.name
            message.text = talkListItem.message[position]
            date.text = chatSdf.format(talkListItem.date[position])
            // talkListItem.isMineFlag[postion]
            img.setImageResource(talkListItem.imgRsc)
        }
    }

    class MultiViewHolder2(binding: ChatRecyclerRightBinding) : RecyclerView.ViewHolder(binding.root) {

        var message: TextView = binding.talkPageMessage
        var date: TextView = binding.talkPageDate

        fun bind(talkListItem: TalkListItem, position: Int) {
            message.text = talkListItem.message[position]
            date.text = chatSdf.format(talkListItem.date[position])
        }
    }

    class MultiViewHolder3(binding: ChatRecyclerHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdfHeader = SimpleDateFormat("YYYY년 MMM dd일 EEEE", Locale.KOREAN)

        var header: TextView = binding.talkPageTxtHeader

        fun bind(talkListItem: TalkListItem, position: Int) {
            header.text = sdfHeader.format(talkListItem.date[position])
        }
    }

    override fun getItemCount(): Int {
        return talkListItem.message.size
    }

    override fun getItemViewType(position: Int): Int {
        return talkListItem.isMineFlag[position]
    }
}