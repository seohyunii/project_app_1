package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;
//약 데이터 요청시 전송
public class MednameData {
    @SerializedName("medName")
    String medName;

    @SerializedName("medTime")
    String medTime;

    @SerializedName("medNum")
    String medNum;

    @SerializedName("patientName")
    String patientName;

    @SerializedName("patient_id")
    String patient_id;

    public MednameData(String medName, String medTime, String medNum, String patientName, String patient_id) {
        this.medName = medName;
        this.medTime = medTime;
        this.medNum = medNum;
        this.patientName = patientName;
        this.patient_id = patient_id;
    }
}
