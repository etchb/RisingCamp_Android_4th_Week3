package com.bhongj.rc_week3

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhongj.rc_week3.databinding.ActivityTalkPageBinding
import java.text.SimpleDateFormat
import java.util.*

class TalkPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTalkPageBinding
    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var adapter: ChatAdapter
    private lateinit var adapter: MutiviewAdapter
    private var sendBtnEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTalkPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        setSupportActionBar(binding.tlbTalkPage)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.talkPageBtnAdd.setOnClickListener {
            val txt = binding.talkPageEdtMessage.text.toString()
            var num: String = try {
                txt.toInt()
                txt
            } catch (e: NumberFormatException) {
                "01012341234"
            }
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$num"))
            startActivity(intent)
        }

        binding.talkPageEdtMessage.addTextChangedListener {
            sendBtnEnabled = binding.talkPageEdtMessage.text.toString().isNotEmpty()
            if (sendBtnEnabled) {
                binding.talkPageBtnSendSharp.setImageResource(R.drawable.btn_send)
                binding.talkPageBtnSendSharp.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.talkPageBtnSendSharp.isClickable = true
            }
            else {
                binding.talkPageBtnSendSharp.setImageResource(R.drawable.talk_sharp)
                binding.talkPageBtnSendSharp.scaleType = ImageView.ScaleType.CENTER_INSIDE
                binding.talkPageBtnSendSharp.isClickable = false
            }
        }
    }

    override fun onStart() {
        super.onStart()
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()

        val name = intent.getStringExtra("name")
        binding.tlbTalkPage.title = name

        val talkListItemListFilt : List<TalkListItem> = talkListItemList.filter { it.name == name}
        binding.talkPageRcyviewChat.layoutManager = LinearLayoutManager(this)
        if (talkListItemListFilt.isNotEmpty()) {
            val talkListItem : TalkListItem = talkListItemListFilt[0]
//            adapter = ChatAdapter(talkListItem)
            adapter = MutiviewAdapter(this, talkListItem)
            binding.talkPageRcyviewChat.adapter = adapter

            binding.talkPageBtnSendSharp.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val sdf = SimpleDateFormat("a hh:mm", Locale.KOREAN)
                val date = sdf.format(calendar.time)
                val message = binding.talkPageEdtMessage.text.toString()
                binding.talkPageEdtMessage.text?.clear()

                talkListItem.message.add(message)
                talkListItem.date.add(date)
                talkListItem.isMineFlag.add(1)

                adapter.notifyItemInserted(talkListItem.message.size-1)
            }
        }

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)
        val lastMessage = sharedPreferences.getString(name, "defaultName")
        if (lastMessage != "defaultName") {
            binding.talkPageEdtMessage.setText(lastMessage)
        }
    }

    fun deleteItem() {
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
//        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        val name = binding.talkPageName.text.toString()
        val text = binding.talkPageEdtMessage.text.toString()
//        editor.putString(name, text)
        editor.apply()
    }
}