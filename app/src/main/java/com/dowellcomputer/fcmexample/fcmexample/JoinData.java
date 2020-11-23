package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class JoinData { //회원가입시 보내는 데이터
    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private String userId;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("patient_id")
    private String patient_id;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPhone")
    private String userPhone;

    @SerializedName("userMac")
    private String userMac;

    @SerializedName("userToken")
    private String userToken;

    public JoinData(String userName, String userId, String userPwd, String patient_id, String userEmail, String userPhone, String userMac,String userToken) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.patient_id = patient_id;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userMac = userMac;
        this.userToken=userToken;

    }
}
