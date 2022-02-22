package com.bhongj.rc_week3

data class TalkListItem(
    var name: String = "김아무개",
    val stateMessage: String? = null,
    var message: MutableList<String> = mutableListOf<String>(),
    var date: MutableList<String> = mutableListOf<String>(),
    var isMineFlag: MutableList<Boolean> = mutableListOf<Boolean>(),
    var imgRsc: Int = R.drawable.ic_launcher_foreground,
    var isChecked: Boolean = false,
)

data class BannerListItem(
    var id: Int,
    var imgRsc: Int
)

val TalkListItemList = mutableListOf<TalkListItem>(
    TalkListItem(
        name = "토스뱅크",
        stateMessage = "No.1 인터넷뱅킹 토스뱅크",
        message = mutableListOf("안녕하세요. 토스뱅크입니다."),
        date = mutableListOf("오후 1:09"),
        isMineFlag = mutableListOf(false),
        imgRsc = R.drawable.pic1,
        isChecked = false,
    ),

    TalkListItem(
        name = "미스터피자",
        stateMessage = null,
        message = mutableListOf("고객센터 : 1500-1500"),
        date = mutableListOf("오후 1:09"),
        isMineFlag = mutableListOf(false),
        imgRsc = R.drawable.pic2,
        isChecked = false,
    ),

    TalkListItem(
        name = "교보문고",
        stateMessage = "우리들의 서점 교보문고",
        message = mutableListOf("홈페이지 : www.naver.com"),
        date = mutableListOf("오후 1:09"),
        isMineFlag = mutableListOf(false),
        imgRsc = R.drawable.pic3,
        isChecked = false,
    ),

    TalkListItem(
        name = "안드로",
        stateMessage = "안드로이드 짱",
        message = mutableListOf("안녕?"),
        date = mutableListOf("오후 1:30"),
        isMineFlag = mutableListOf(false),
        imgRsc = R.drawable.pic4,
        isChecked = false,
    ),

    TalkListItem(
        name = "로이드",
        stateMessage = null,
//        message = mutableListOf("나는 로이드다!"),
//        date = mutableListOf("오후 5:20"),
//        isMineFlag = mutableListOf(false),
        imgRsc = R.drawable.pic5,
        isChecked = false,
    ),

    TalkListItem(
        name = "엣츠비",
        stateMessage = "안드로이드 열심히 하자!",
//        message = mutableListOf("홈페이지 : www.naver.com"),
//        date = mutableListOf("오후 1:09"),
//        isMineFlag = mutableListOf(false),
        imgRsc = R.drawable.pic6,
        isChecked = false,
    ),
)
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