package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class BoardGetResponse { //게시판요청에서 받는 데이터
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("contents")
    private String contents;

    public int getCode() {
        return code;
    }

    public String getContents() {
        return contents;
    }

    public String getMessage() {
        return message;
    }
}
