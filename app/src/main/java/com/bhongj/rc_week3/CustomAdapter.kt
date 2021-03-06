package com.bhongj.rc_week3

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.bhongj.rc_week3.databinding.AlertdialogEdtBinding
import com.bhongj.rc_week3.databinding.ListviewItemBinding
import java.util.*

class CustomAdapter(context: Context, private val talkListItemList: MutableList<TalkListItem>):BaseAdapter() {

    private lateinit var binding: ListviewItemBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount() : Int = talkListItemList.size

    override fun getItem(p0: Int) : Any = talkListItemList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        binding = ListviewItemBinding.inflate(inflater, p2, false)

        binding.listviewTxtName.text = talkListItemList[p0].name
        if (talkListItemList[p0].stateMessage == null) {
            binding.listviewStateMessage.visibility = View.GONE
        }
        else {
            binding.listviewStateMessage.text = talkListItemList[p0].stateMessage
            binding.listviewStateMessage.visibility = View.VISIBLE
        }
        binding.talkFriendImg.setImageResource(talkListItemList[p0].imgRsc)
        binding.talkFriendSwitch.isChecked = talkListItemList[p0].isChecked

        binding.talkFriendSwitch.setOnCheckedChangeListener { compoundButton, b ->
            talkListItemList[p0].isChecked = b
        }

        binding.layoutLinear.setOnClickListener {
            val intent = Intent(binding.root.context, TalkPageActivity::class.java)
            val calendar: Calendar = Calendar.getInstance()
            if (talkListItemList[p0].message.size == 0) {
                talkListItemList[p0].message.add("????????? ???????????????.")
                talkListItemList[p0].date.add(calendar.time)
                talkListItemList[p0].isMineFlag.add(0)
            }
            intent.putExtra("name", talkListItemList[p0].name)

            ContextCompat.startActivity(binding.root.context, intent, null)
        }

        binding.layoutLinear.setOnLongClickListener {
            showDialog(p0)
            return@setOnLongClickListener false
        }

        return binding.root
    }

    private fun showDialog(pos: Int) {
        val builder = AlertDialog.Builder(binding.root.context)
        val builderItem = AlertdialogEdtBinding.inflate(inflater)
        val editText = builderItem.editText
        with(builder){
            setTitle(talkListItemList[pos].name)
            setMessage("????????? ?????????????????? ???????????????.")
            setView(builderItem.root)
            setPositiveButton("??????"){ dialogInterface: DialogInterface, i: Int ->
                if(editText.text != null) {
                    talkListItemList[pos].stateMessage = editText.text.toString()
                }
            }
            setNegativeButton("??????"){ dialogInterface: DialogInterface, i: Int ->
            }
            show()
        }
    }
}