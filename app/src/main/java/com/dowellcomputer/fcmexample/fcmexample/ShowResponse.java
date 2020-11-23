package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class ShowResponse { //약복용데이터 가져올시 가져올 데이터
    @SerializedName("code")
    private int code;

    @SerializedName("medTimeApp")
    private String[] medTimeApp;

    @SerializedName("medNameApp")
    private String[] medNameApp;

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

    public int getLength() {
        return length;
    }

}
