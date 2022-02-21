package com.bhongj.rc_week3

data class TalkListItem(
    var name: String,
    var message: String,
    var imgRsc: Int
)

data class BannerListItem(
    var id: Int,
    var imgRsc: Int
)

val TalkListItemList = mutableListOf<TalkListItem>(
    TalkListItem(
        name = "토스뱅크",
        message = "안녕하세요. 토스뱅크입니다.",
        imgRsc = R.drawable.ic_toss
    ),

    TalkListItem(
        name = "미스터피자",
        message = "고객센터 : 1500-1500",
        imgRsc = R.drawable.ic_mrpizza
    ),
    TalkListItem(
        name = "교보문고",
        message = "홈페이지 : www.naver.com",
        imgRsc = R.drawable.ic_gyobo
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