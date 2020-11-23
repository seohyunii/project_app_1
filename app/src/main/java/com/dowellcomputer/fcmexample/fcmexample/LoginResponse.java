package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class LoginResponse { //로그인 요청시 받을 데이터
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userid")
    private String userid;

    @SerializedName("patient_id")
    private String patient_id;

    public int getCode() {
        return code;
    }

    public String getPatient_id() { return patient_id; }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userid;
    }
}
