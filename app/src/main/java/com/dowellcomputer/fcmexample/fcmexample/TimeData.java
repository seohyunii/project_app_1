package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class TimeData { //약주기 설정시 보낼 데이터
    @SerializedName("medName")
    String medName;

    @SerializedName("medTime")
    String medTime;

    @SerializedName("medNum")
    String medNum;

    @SerializedName("patientname")
    String patientname;

    @SerializedName("patient_id")
    String patient_id;


    public TimeData(String medName, String medTime, String medNum, String patientname, String patient_id) {
        this.medName = medName;
        this.medTime = medTime;
        this.medNum = medNum;
        this.patientname = patientname;
        this.patient_id = patient_id;
    }
}
