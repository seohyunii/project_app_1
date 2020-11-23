package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class TimeGetResponse {  //약 주기 가져올때 받을 데이터
    @SerializedName("code")
    private int code;

    @SerializedName("medTimeApp")
    private String[] medTimeApp;

    @SerializedName("medNameApp")
    private String[] medNameApp;

    @SerializedName("medNumApp")
    private String[] medNumApp;

    @SerializedName("length")
    private int length;


    public int getCode() {
        return code;
    }
    public String[] medTimeApp() {
        return medTimeApp;
    }
    public String[] medNameApp() {
        return medNameApp;
    }
    public String[] medNumApp() {
        return medNumApp;
    }

    public int getLength() {
        return length;
    }


}
