package com.bhongj.rc_week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_week3.databinding.FragmentTalkSettingBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TalkSettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TalkSettingFragment : Fragment() {
    private lateinit var _binding: FragmentTalkSettingBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTalkSettingBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}