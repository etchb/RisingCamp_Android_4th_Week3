package com.bhongj.rc_week3

data class UserInfo(
    var userID: String,
    var userPW: String,
    var userName: String,
)

val UserInfoList = mutableListOf<UserInfo>(
    UserInfo(
        userID = "etchb1@naver.com",
        userPW = "1111",
        userName = "etchb1",
    ),
    UserInfo(
        userID = "etchb2@naver.com",
        userPW = "2222",
        userName = "etchb2",
    ),
    UserInfo(
        userID = "12345",
        userPW = "1234",
        userName = "etchb3",
    ),
)