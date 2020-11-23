package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class LoginData { //로그인 요청시 보낼 데이터
    @SerializedName("userId")
    String userId;

    @SerializedName("userPwd")
    String userPwd;

    public LoginData(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }
}
