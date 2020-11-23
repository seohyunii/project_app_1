package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class BoardGetData { //게시판 요청시 보내는 데이터

    @SerializedName("patient_id")
    private String patient_id;

    public BoardGetData(String patient_id) {

        this.patient_id=patient_id;
    }
}

