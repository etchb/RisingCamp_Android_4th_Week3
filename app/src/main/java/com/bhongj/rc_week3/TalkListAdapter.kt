package com.bhongj.rc_week3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_week3.databinding.ItemRecyclerBinding
import com.bhongj.rc_week3.databinding.ListviewItemBinding

class TalkListAdapter(private val dataList: MutableList<TalkListItem>) :
    RecyclerView.Adapter<TalkListAdapter.ViewHolder>() {
    private lateinit var binding: ItemRecyclerBinding
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.rcyTxtName
        var message: TextView = binding.rcyTxtMessage
        var img: ImageView = binding.rcyImg

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(item: TalkListItem) {
            name.text = item.name
            message.text = item.message[item.message.size-1]
            img.setImageResource(item.imgRsc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_recycler, parent, false)

//        return ViewHolder(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        if (item.message.size > 0) {
            holder.bind(item)

            holder.itemView.setOnClickListener { v ->
                val intent = Intent(v.context, TalkPageActivity::class.java)
                intent.putExtra("name", item.name)
//                intent.putExtra("message", item.message[item.message.size-1])
//                intent.putExtra("imgRsc", item.imgRsc)
                ContextCompat.startActivity(v.context, intent, null)
            }
        }

    }

    override fun getItemCount() = dataList.size

}