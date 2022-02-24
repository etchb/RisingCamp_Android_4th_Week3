package com.bhongj.rc_week3

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhongj.rc_week3.databinding.ActivityTalkPageBinding

class TalkPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTalkPageBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: ChatAdapter

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
    }

    override fun onStart() {
        super.onStart()
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()

        val name = intent.getStringExtra("name")
        binding.tlbTalkPage.title = name

        val talkListItemListFilt : List<TalkListItem> = TalkListItemList.filter { it.name == name}
        binding.talkPageRcyviewChat.layoutManager = LinearLayoutManager(this)
        if (talkListItemListFilt.isNotEmpty()) {
            val talkListItem : TalkListItem = talkListItemListFilt[0]
            adapter = ChatAdapter(talkListItem)
            binding.talkPageRcyviewChat.adapter = adapter
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