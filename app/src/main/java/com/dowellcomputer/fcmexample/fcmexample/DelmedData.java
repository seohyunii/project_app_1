package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class DelmedData { //약 삭제 요청시 보내는 데이터

    @SerializedName("pos")
    private int pos;

    @SerializedName("patientname")
    private String patientname;


    public DelmedData(int pos, String patientname) {

        this.pos = pos;
        this.patientname=patientname;
    }
}
