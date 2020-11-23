package com.dowellcomputer.fcmexample.fcmexample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login") //로그인
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join") //회원가입
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/time") //주기설정
    Call<TimeResponse> userTime(@Body TimeData data);

    @POST("/user/mednamecheck") //동일약 이름 저장 여부 확인
    Call<MednameResponse> userTime(@Body MednameData data);

    @POST("/user/delmeddata") //약 삭제
    Call<DelmedResponse> userTime(@Body DelmedData data);

    @POST("/user/gettime") //저장된 약주기 가져오기
    Call<TimeGetResponse> userGetTime(@Body TimeGetData data);

    @POST("/user/getmedtakentime") //복용한 약 시각 가져오기
    Call<ShowResponse> userGetMedTime(@Body ShowData data);

    @POST("/user/getboard") //게시판 가져오기
    Call<BoardGetResponse> userBoardGet(@Body BoardGetData data);
}