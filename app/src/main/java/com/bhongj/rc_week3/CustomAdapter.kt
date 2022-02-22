package com.bhongj.rc_week3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bhongj.rc_week3.databinding.ListviewItemBinding
import java.text.SimpleDateFormat
import java.util.*

class CustomAdapter(context: Context, private val TalkListItemList: MutableList<TalkListItem>):BaseAdapter() {

    private lateinit var binding: ListviewItemBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount() : Int = TalkListItemList.size

    override fun getItem(p0: Int) : Any = TalkListItemList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        binding = ListviewItemBinding.inflate(inflater, p2, false)

        binding.listviewTxtName.text = TalkListItemList[p0].name
        if (TalkListItemList[p0].stateMessage == null) {
            binding.listviewStateMessage.visibility = View.GONE
        }
        else {
            binding.listviewStateMessage.text = TalkListItemList[p0].stateMessage
            binding.listviewStateMessage.visibility = View.VISIBLE
        }
        binding.talkFriendImg.setImageResource(TalkListItemList[p0].imgRsc)
        binding.talkFriendSwitch.isChecked = TalkListItemList[p0].isChecked

        binding.talkFriendSwitch.setOnCheckedChangeListener { compoundButton, b ->
            TalkListItemList[p0].isChecked = b
        }

        binding.layoutLinear.setOnClickListener {
            val calendar:Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("a hh:mm", Locale.KOREAN)
            val date = sdf.format(calendar.time)

            val intent = Intent(binding.root.context, TalkPageActivity::class.java)
            if (TalkListItemList[p0].message.size == 0) {
                TalkListItemList[p0].message.add("채팅을 시작합니다.")
                TalkListItemList[p0].date.add(date)
            }
            intent.putExtra("name", TalkListItemList[p0].name)
            ContextCompat.startActivity(binding.root.context, intent, null)
        }

        return binding.root
    }
}