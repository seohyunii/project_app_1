package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;
// 약 데이터 요청시 받는 데이터
public class MednameResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("medName")
    private String medName;

    @SerializedName("medTime")
    private String medTime;

    public int getCode() {
        return code;
    }
    public String getMedName() {
        return medName;
    }
    public String getMedTime() {
        return medTime;
    }

    public String getMessage() {
        return message;
    }
}
