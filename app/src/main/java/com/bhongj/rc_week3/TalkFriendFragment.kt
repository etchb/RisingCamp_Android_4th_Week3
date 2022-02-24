package com.bhongj.rc_week3

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bhongj.rc_week3.databinding.FragmentTalkFriendBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TalkFriendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TalkFriendFragment : Fragment() {
    private lateinit var _binding: FragmentTalkFriendBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTalkFriendBinding.inflate(layoutInflater, container, false)

        for (i in 1..5) {
            talkListItemList.add(TalkListItem(name = "박아무개", stateMessage = "박아무개 상태메시지"))
            talkListItemList.add(TalkListItem(name = "최아무개", stateMessage = "최아무개 상태메시지"))
            talkListItemList.add(TalkListItem(name = "이아무개", stateMessage = "이아무개 상태메시지"))
            talkListItemList.add(TalkListItem(name = "정아무개"))
        }

        val customAdapter = CustomAdapter(binding.root.context, talkListItemList)
        binding.listviewFriend.adapter = customAdapter

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_1, menu)
    }
}