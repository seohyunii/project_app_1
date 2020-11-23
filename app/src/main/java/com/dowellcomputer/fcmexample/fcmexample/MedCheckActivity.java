package com.dowellcomputer.fcmexample.fcmexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedCheckActivity extends AppCompatActivity {
    private ServiceApi service;
    String userid, patient_id;
    ImageButton returnBtn, logoutBtn, showBtn;
    EditText yearText, monthText, dateText;
    String senddate;
    ListView timenameText;
    String[] medNameApp;
    String[] medTimeApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_check);

        returnBtn = (ImageButton)findViewById(R.id.returnBtn);
        logoutBtn = (ImageButton)findViewById(R.id.logoutBtn);
        showBtn = (ImageButton)findViewById(R.id.showBtn);
        yearText = (EditText)findViewById(R.id.yearText);
        monthText = (EditText)findViewById(R.id.monthText);
        dateText = (EditText)findViewById(R.id.dateText);
        timenameText = (ListView) findViewById(R.id.timenameText);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        patient_id = intent.getStringExtra("patient_id");
        System.out.println("받은 아이디"+userid);
        // 약 확인 버튼 눌렀을시
        showBtn.setOnClickListener(new View.OnClickListener(){

            @Override //showMed함수 호출
            public void onClick(View view){
                showMed();
            }
        });
        //로그아웃 버튼 눌렀을시
        logoutBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Toast.makeText(MedCheckActivity.this, "로그아웃 되셨습니다", Toast.LENGTH_LONG).show();
                Intent logoutIntent = new Intent(MedCheckActivity.this, MainActivity.class);
                MedCheckActivity.this.startActivity(logoutIntent);
            }
        });
        //뒤로가기 버튼 눌렀을시
        returnBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent ReturnIntent = new Intent(MedCheckActivity.this, MenuActivity.class);
                ReturnIntent.putExtra("userid", userid);
                ReturnIntent.putExtra("patient_id", patient_id);
                MedCheckActivity.this.startActivity(ReturnIntent);
            }
        });
    }

    private void showMed() {
        dateText.setError(null);
        yearText.setError(null);
        monthText.setError(null);
        String year = yearText.getText().toString();
        String month = monthText.getText().toString();
        String date = dateText.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // 년도 유효성 검사
        if (year.isEmpty()) {
            yearText.setError("년도를 입력해주세요.");
            focusView = yearText;
            cancel = true;
        }

        // 월 유효성 검사
        if (month.isEmpty()) {
            monthText.setError("월을 입력해주세요.");
            focusView = monthText;
            cancel = true;
        }

        // 날짜 유효성 검사
        if (date.isEmpty()) {
            dateText.setError("날짜를 입력해주세요.");
            focusView = dateText;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else { //10월 미만일시 앞에 0을 붙임
            if(Integer.parseInt(month)>0 && Integer.parseInt(month)<10){
                month = "0"+month;
                System.out.println(month+"월");
            }
            // 10일 미만일시 앞에 0을 붙임
            if(Integer.parseInt(date)>0 && Integer.parseInt(date)<10){
                date = "0"+date;
                System.out.println(date+"일");
            }
            //2020-06-29형태로 저장. 뒤에 % 붙이는 이유는 서버에서 데이터베이스 sql문에서 날짜로 시작하는 모든 데이터를 가져오기 위해
            senddate = year+"-"+month+"-"+date+"%";
            System.out.println(senddate+"비교할 날짜");
            Getmedtakentime(new ShowData(senddate, Integer.parseInt(patient_id)));
        }


    }
    // 약 정보 가져오기 api 호출
    private void Getmedtakentime(ShowData data) {
        service.userGetMedTime(data).enqueue(new Callback<ShowResponse>() {
            @Override
            public void onResponse(Call<ShowResponse> call, Response<ShowResponse> response) {
                final List<String> data = new ArrayList<>();
                final ArrayAdapter<String> adp = new ArrayAdapter<>( getApplicationContext(),android.R.layout.simple_list_item_1,data);
                timenameText.setAdapter(adp);

                ShowResponse result = response.body();

                //받은 약 정보의 개수를 for문으로 반복하여 배열에 하나씩 저장 후 listview에 출력
                for(int i=0; i<result.getLength();i++){
                    medNameApp = result.medNameApp();
                    String medname = medNameApp[i];
                    medTimeApp = result.medTimeApp();
                    String medtime = medTimeApp[i];
                    System.out.println(medtime);
                    System.out.println(medname);

                    data.add("약 이름: "+medname+" 복용시각: "+medtime);}

            }
            @Override
            public void onFailure(Call<ShowResponse> call, Throwable t) {
                Toast.makeText(MedCheckActivity.this, "찾기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("찾기 에러 발생", t.getMessage());
            }
        });

    }
}