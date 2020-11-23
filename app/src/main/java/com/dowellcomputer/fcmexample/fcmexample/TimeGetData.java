package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class TimeGetData { // 약 주기 가져올때 보낼 데이터
    @SerializedName("patientname")
    String patientname;

    public TimeGetData(String patientname) {
        this.patientname = patientname;
    }
}
