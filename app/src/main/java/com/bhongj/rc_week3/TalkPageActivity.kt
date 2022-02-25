package com.bhongj.rc_week3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Selection
import android.view.FocusFinder
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhongj.rc_week3.databinding.ActivityTalkMainBinding
import com.bhongj.rc_week3.databinding.ActivityTalkPageBinding
import com.bhongj.rc_week3.databinding.ListviewItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class TalkPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTalkPageBinding
    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var adapter: ChatAdapter
    private lateinit var adapter: MutiviewAdapter
    private var sendBtnEnabled: Boolean = false
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTalkPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        setSupportActionBar(binding.tlbTalkPage)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.talkPageBtnAdd.setOnLongClickListener {
            val txt = binding.talkPageEdtMessage.text.toString()
            var num: String = try {
                txt.toInt()
                txt
            } catch (e: NumberFormatException) {
                "01012341234"
            }
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$num"))
            startActivity(intent)
            return@setOnLongClickListener true
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

            binding.talkPageEdtMessage.requestFocus()

            keyboardVisibilityUtils = KeyboardVisibilityUtils(window,
                onShowKeyboard = { keyboardHeight ->
                    binding.talkPageRcyviewChat.scrollToPosition(talkListItem.message.size-1)
                })

            binding.talkPageBtnSendSharp.setOnClickListener {
                val message = binding.talkPageEdtMessage.text.toString()
                if (message.isNotEmpty()) {
                    binding.talkPageEdtMessage.text?.clear()
                    val calendar: Calendar = Calendar.getInstance()
                    val daySdf = SimpleDateFormat("dd", Locale.KOREAN)
                    val dayNow = daySdf.format(calendar.time)

                    if (daySdf.format(talkListItem.date[adapter.itemCount-1]) == dayNow) {
                        talkListItem.message.add(message)
                        talkListItem.date.add(calendar.time)
                        talkListItem.isMineFlag.add(1)

                        adapter.notifyItemInserted(adapter.itemCount-1)
                    }
                    else {
                        talkListItem.message.add("header")
                        talkListItem.date.add(calendar.time)
                        talkListItem.isMineFlag.add(2)

                        talkListItem.message.add(message)
                        talkListItem.date.add(calendar.time)
                        talkListItem.isMineFlag.add(1)

                        adapter.notifyItemRangeInserted(adapter.itemCount-2, 2)
                    }
                    binding.talkPageRcyviewChat.scrollToPosition(adapter.itemCount-1)
                }
            }

            binding.talkPageBtnAdd.setOnClickListener {
                val message = binding.talkPageEdtMessage.text.toString()
                if (message.isNotEmpty()) {
                    val calendar: Calendar = Calendar.getInstance()
                    binding.talkPageEdtMessage.text?.clear()

                    val daySdf = SimpleDateFormat("dd", Locale.KOREAN)
                    val dayNow = daySdf.format(calendar.time)

                    if (daySdf.format(talkListItem.date[adapter.itemCount-1]) == dayNow) {
                        talkListItem.message.add(message)
                        talkListItem.date.add(calendar.time)
                        talkListItem.isMineFlag.add(0)

                        adapter.notifyItemInserted(adapter.itemCount-1)
                    }
                    else {
                        talkListItem.message.add("header")
                        talkListItem.date.add(calendar.time)
                        talkListItem.isMineFlag.add(2)

                        talkListItem.message.add(message)
                        talkListItem.date.add(calendar.time)
                        talkListItem.isMineFlag.add(0)

                        adapter.notifyItemRangeInserted(adapter.itemCount-2, 2)
                    }
                    binding.talkPageRcyviewChat.scrollToPosition(adapter.itemCount-1)
                }
            }
        }

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)
        val lastMessage = sharedPreferences.getString(name, "defaultName")
        if (lastMessage != "defaultName") {
            binding.talkPageEdtMessage.setText(lastMessage)
            binding.talkPageEdtMessage.setSelection(binding.talkPageEdtMessage.text.toString().length)
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
        val name = binding.tlbTalkPage.title.toString()
        val text = binding.talkPageEdtMessage.text.toString()
        editor.putString(name, text)
        editor.apply()
    }
}

