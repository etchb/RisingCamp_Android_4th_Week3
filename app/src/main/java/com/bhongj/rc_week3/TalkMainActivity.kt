package com.bhongj.rc_week3

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bhongj.rc_week3.databinding.ActivityTalkMainBinding

var bannerIdx = 0

class TalkMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityTalkMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    var initCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTalkMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        setSupportActionBar(binding.tlbTalkList)

        binding.bottomNav.itemIconTintList = null

        initNavigationBar()
    }

    private fun initNavigationBar() {
        val talkFriendFragment = TalkFriendFragment()
        val talkListFragment = TalkListFragment()
        val talkViewFragment = TalkViewFragment()
        val talkShopFragment = TalkShopFragment()
        val talkSettingFragment = TalkSettingFragment()

        binding.bottomNav.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bttmenu_friend -> {
                        changeFragment(talkFriendFragment)
                        binding.tlbTalkList.title = "친구"
                        binding.bottomNav.menu
                    }
                    R.id.bttmenu_talk_list -> {
                        changeFragment(talkListFragment)
                        binding.tlbTalkList.title = "채팅"
                    }
                    R.id.bttmenu_view_page -> {
                        changeFragment(talkViewFragment)
                        binding.tlbTalkList.title = "View"
                    }
                    R.id.bttmenu_shop_page -> {
                        changeFragment(talkShopFragment)
                        binding.tlbTalkList.title = "Shop"
                    }
                    R.id.bttmenu_setting -> {
                        changeFragment(talkSettingFragment)
                        binding.tlbTalkList.title = "Setting"
                        initCheck = true
//                        Toast.makeText(context, "init sharedPreferences", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            selectedItemId = R.id.bttmenu_friend
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frmlay_talk_main, fragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestart() {
        super.onRestart()
//        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()

        bannerIdx++
        if (bannerIdx >= BannerListItemList.size) {
            bannerIdx = 0
        }
    }

    override fun onStop() {
        super.onStop()
//        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()

        /* init setting */
        if (initCheck) {
            sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("isAutoLogin", false)
            editor.putString("userName", "defaultUserName")
            editor.apply()
        }
        /* init setting */
    }
}
