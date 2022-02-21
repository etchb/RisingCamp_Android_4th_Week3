package com.bhongj.rc_week3

import android.os.Bundle
import android.view.*
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
    private var businessCardArrayList = ArrayList<BusinessCard>()

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

        for(i in 1..20) {
            businessCardArrayList.add(BusinessCard(name = "김아무개", contents = "010-1234-1234"))
            businessCardArrayList.add(BusinessCard(name = "박아무개", contents = "010-4567-4567"))
        }

        val customAdapter = CustomAdapter(binding.root.context, businessCardArrayList)
        binding.listviewFriend.adapter = customAdapter

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_1, menu)
    }
}