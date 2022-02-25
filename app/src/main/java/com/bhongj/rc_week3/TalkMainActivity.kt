package com.bhongj.rc_week3

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bhongj.rc_week3.databinding.ActivityTalkMainBinding
import java.util.*

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

        initData()
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

    private fun initData() {
        val cal: Calendar = Calendar.getInstance()
        cal.set(2022,2,24)

        talkListItemList.add(TalkListItem(
            name = "레오",
            stateMessage = "안드로이드 B반",
            message = mutableListOf("안녕하세요. 레오입니다."),
            date = mutableListOf(cal.time),
            isMineFlag = mutableListOf(0),
            imgRsc = R.drawable.pic1,
            isChecked = false,))

        talkListItemList.add(TalkListItem(
                name = "루비",
                stateMessage = null,
                message = mutableListOf("연락처 : 1500-1500"),
                date = mutableListOf(cal.time),
                isMineFlag = mutableListOf(0),
                imgRsc = R.drawable.pic2,
                isChecked = false,))

        talkListItemList.add(TalkListItem(
                name = "실버",
                stateMessage = "Silver",
                message = mutableListOf("홈페이지 : www.naver.com"),
                date = mutableListOf(cal.time),
                isMineFlag = mutableListOf(0),
                imgRsc = R.drawable.pic3,
                isChecked = false))

        talkListItemList.add(TalkListItem(
                name = "안드로",
                stateMessage = "안드로이드 짱",
                message = mutableListOf("안녕?", "나는 안드로라고 해", "안녕하세요.", "뭐해?"),
                date = mutableListOf(cal.time, cal.time, cal.time, cal.time),
                isMineFlag = mutableListOf(0, 0, 1, 0),
                imgRsc = R.drawable.pic4,
                isChecked = false))

        talkListItemList.add(TalkListItem(
                name = "덴고",
                stateMessage = null,
                //        message = mutableListOf("나는 로이드다!"),
                //        date = mutableListOf("오후 5:20"),
                //        isMineFlag = mutableListOf(false),
                imgRsc = R.drawable.pic5,
                isChecked = false))

        talkListItemList.add(TalkListItem(
                name = "엣츠비",
                stateMessage = "안드로이드 열심히 하자!",
                //        message = mutableListOf("홈페이지 : www.naver.com"),
                //        date = mutableListOf("오후 1:09"),
                //        isMineFlag = mutableListOf(false),
                imgRsc = R.drawable.pic6,
                isChecked = false))
    }
}
