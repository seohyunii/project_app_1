package com.dowellcomputer.fcmexample.fcmexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    //메뉴 액티비티
    String userid;
    String patient_id;
    ServiceApi service;
    TextView feedbackView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageButton timesettingBtn = (ImageButton) findViewById(R.id.timesettigBtn);
        ImageButton qmark_menu = (ImageButton) findViewById(R.id.qmark_menu);
        ImageButton patientimgBtn = (ImageButton) findViewById(R.id.patientimgBtn);
        ImageButton logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        feedbackView = (TextView) findViewById(R.id.feedbackView);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        patient_id = intent.getStringExtra("patient_id");
        System.out.println("아이디"+userid);
        if(patient_id != "") { //아이디를 전송받으면 getFeed함수(게시판) 호출
            System.out.println(patient_id+"!!!");
            getFeed(new BoardGetData(patient_id));
        }

        qmark_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu_q_Intent = new Intent(MenuActivity.this, Menu_q_Activity.class);
                Menu_q_Intent.putExtra("user_id", userid);
                MenuActivity.this.startActivity(Menu_q_Intent);
            }
        });

        //로그아웃 버튼
        logoutBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "로그아웃 되셨습니다", Toast.LENGTH_LONG).show();
                Intent logoutIntent = new Intent(MenuActivity.this, MainActivity.class);
                MenuActivity.this.startActivity(logoutIntent);
            }
        });
        //약 주기 설정 메뉴 이동 버튼
        timesettingBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent timesettingIntent = new Intent(MenuActivity.this, TimeSettingActivity.class);
                timesettingIntent.putExtra("userid", userid);
                timesettingIntent.putExtra("patient_id",patient_id);
                System.out.println("메인에서보낼아이디"+patient_id+userid);
                MenuActivity.this.startActivity(timesettingIntent);
            }
        });
        //환자 상태 확인 메뉴 버튼
        patientimgBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent patientimgIntent = new Intent(MenuActivity.this, PatientImgActivity.class);
                patientimgIntent.putExtra("userid", userid);
                patientimgIntent.putExtra("patient_id",patient_id);
                System.out.println("메인에서보낼아이디"+patient_id+userid);
                MenuActivity.this.startActivity(patientimgIntent);
            }
        });
    }
    public void getFeed(BoardGetData data) { //게시판 정보 가져오기 api 호출
        service.userBoardGet(data).enqueue(new Callback<BoardGetResponse>() {
            @Override
            public void onResponse(Call<BoardGetResponse> call, Response<BoardGetResponse> response) {
                BoardGetResponse result = response.body();
                Toast.makeText(MenuActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                feedbackView.setText(result.getContents()); //저장된 게시판 내용 가져오면 app게시판에 출력
            }
            @Override
            public void onFailure(Call<BoardGetResponse> call, Throwable t) {
                Toast.makeText(MenuActivity.this, "이름 check 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("이름 check 에러 발생", t.getMessage());
            }
        });
    }
}
