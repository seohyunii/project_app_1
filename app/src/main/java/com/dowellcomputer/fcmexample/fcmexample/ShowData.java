package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class ShowData { //약복용데이터가져올시 보낼 데이터

    @SerializedName("senddate")
    String senddate;

    @SerializedName("patient_id")
    Integer patient_id;

    public ShowData(String senddate, int patient_id) {
        this.senddate = senddate;
        this.patient_id = patient_id;
    }
}
