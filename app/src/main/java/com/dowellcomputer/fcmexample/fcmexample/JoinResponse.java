package com.dowellcomputer.fcmexample.fcmexample;

import com.google.gson.annotations.SerializedName;

public class JoinResponse { //회원가입시 받는 데이터
        @SerializedName("code")
        private int code;

        @SerializedName("message")
        private String message;



        public int getCode() {
                return code;
        }

        public String getMessage() {
                return message;
        }
}
