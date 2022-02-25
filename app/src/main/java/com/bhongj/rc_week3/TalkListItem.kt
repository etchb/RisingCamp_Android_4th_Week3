package com.bhongj.rc_week3

import java.text.SimpleDateFormat
import java.util.*

data class TalkListItem(
    var name: String = "김아무개",
    var stateMessage: String? = null,
    var message: MutableList<String> = mutableListOf<String>(),
    var date: MutableList<Date> = mutableListOf<Date>(),
    var isMineFlag: MutableList<Int> = mutableListOf<Int>(),
    var imgRsc: Int = R.drawable.ic_launcher_foreground,
    var isChecked: Boolean = false,
)

data class BannerListItem(
    var id: Int,
    var imgRsc: Int
)

val chatSdf = SimpleDateFormat("a hh:mm", Locale.KOREAN)

var talkListItemList = mutableListOf<TalkListItem>()

val BannerListItemList = mutableListOf<BannerListItem>(
    BannerListItem(
        id = 0,
        imgRsc = R.drawable.banner1
    ),
    BannerListItem(
        id = 1,
        imgRsc = R.drawable.banner2
    ),
    BannerListItem(
        id = 2,
        imgRsc = R.drawable.banner3
    ),
)