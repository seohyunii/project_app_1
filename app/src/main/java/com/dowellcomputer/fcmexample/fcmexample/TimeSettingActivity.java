package com.dowellcomputer.fcmexample.fcmexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeSettingActivity extends AppCompatActivity {
    ImageButton returnBtn;
    ImageButton saveBtn;
    ImageButton delBtn;
    ImageButton logoutBtn;
    ImageButton checkmedBtn;
    ImageButton qmark_med;
    ImageButton mednumBtn;
    EditText mednameText;
    EditText medtimeText;
    TextView invisiblebtn;
    private ServiceApi service;
    String[] medNameApp;
    String[] medTimeApp;
    String[] medNumApp;
    String userid;
    String patient_id;
    ListView timenameText;
    public Object a ;
    int code=0;
    String mednum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);
        returnBtn = (ImageButton) findViewById(R.id.returnBtn);
        saveBtn = (ImageButton) findViewById(R.id.saveBtn);
        mednameText = (EditText)findViewById(R.id.mednameText);
        medtimeText = (EditText)findViewById(R.id.medtimeText);
        timenameText = (ListView) findViewById(R.id.timenameText);
        checkmedBtn = (ImageButton)findViewById(R.id.checkmedBtn);
        mednumBtn = (ImageButton)findViewById(R.id.mednumBtn);
        delBtn = (ImageButton) findViewById(R.id.delBtn);
        qmark_med = (ImageButton) findViewById(R.id.qmark_med);
        logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        patient_id = intent.getStringExtra("patient_id");
        System.out.println("받은 아이디"+userid);
        GetTimeSetting(new TimeGetData(userid));

        qmark_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Med_q_Intent = new Intent(TimeSettingActivity.this, Med_q_Activity.class);
                Med_q_Intent.putExtra("user_id", userid);
                TimeSettingActivity.this.startActivity(Med_q_Intent);
            }
        });

        mednumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Med_num_Intent = new Intent(TimeSettingActivity.this, MednumInfo.class);
                Med_num_Intent.putExtra("user_id", userid);
                TimeSettingActivity.this.startActivity(Med_num_Intent);
            }
        });

        Spinner med_spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter med_Adapter = ArrayAdapter.createFromResource(this,
                R.array.mednum, android.R.layout.simple_spinner_item);
        med_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        med_spinner.setAdapter(med_Adapter);

        med_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object i = parent.getItemAtPosition(position);
                mednum = i.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //약이름, 주기 입력 버튼
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeSetting();
            }
        });
        //로그아웃 버튼
        logoutBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Toast.makeText(TimeSettingActivity.this, "로그아웃 되셨습니다", Toast.LENGTH_LONG).show();
                Intent logoutIntent = new Intent(TimeSettingActivity.this, MainActivity.class);
                TimeSettingActivity.this.startActivity(logoutIntent);
            }
        });
        //뒤로가기 버튼
        returnBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent ReturnIntent = new Intent(TimeSettingActivity.this, MenuActivity.class);
                ReturnIntent.putExtra("userid", userid);
                ReturnIntent.putExtra("patient_id", patient_id);
                TimeSettingActivity.this.startActivity(ReturnIntent);
            }
        });
        //복용 약 확인 버튼
        checkmedBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent ReturnIntent = new Intent(TimeSettingActivity.this, MedCheckActivity.class);
                ReturnIntent.putExtra("userid", userid);
                ReturnIntent.putExtra("patient_id", patient_id);
                TimeSettingActivity.this.startActivity(ReturnIntent);
            }
        });
    }
    private void timeSetting() {
        mednameText.setError(null);
        medtimeText.setError(null);
        String mname = mednameText.getText().toString();
        String mtime = medtimeText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 약이름의 유효성 검사
        if (mname.isEmpty()) {
            mednameText.setError("약 이름을 입력해주세요.");
            focusView = mednameText;
            cancel = true;
        }

        // 약주기의 유효성 검사
        if (mtime.isEmpty()) {
            medtimeText.setError("약 주기를 입력해주세요.");
            focusView = medtimeText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            System.out.println("약이름"+mname+"약주기"+mtime);
            CheckTimeSetting(new MednameData(mname,mtime,mednum,userid,patient_id));

        }

    }

    //약 주기 등록
    private void StartTimeSetting(TimeData data) {
        service.userTime(data).enqueue(new Callback<TimeResponse>() {
            @Override
            public void onResponse(Call<TimeResponse> call, Response<TimeResponse> response) {
                TimeResponse result = response.body();
                Toast.makeText(TimeSettingActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                GetTimeSetting(new TimeGetData(userid));

            }
            @Override
            public void onFailure(Call<TimeResponse> call, Throwable t) {
                Toast.makeText(TimeSettingActivity.this, "등록 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("등록 에러 발생", t.getMessage());
            }
        });
    }
    // 이미 저장된 동일한 약이름 있는지 체크 후 있으며 삭제하기 요청
    private void CheckTimeSetting(MednameData data) {
        service.userTime(data).enqueue(new Callback<MednameResponse>() {
            @Override
            public void onResponse(Call<MednameResponse> call, Response<MednameResponse> response) {
                MednameResponse result = response.body();
                Toast.makeText(TimeSettingActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                StartTimeSetting(new TimeData(result.getMedName(),result.getMedTime(),mednum, userid, patient_id));
            }
            @Override
            public void onFailure(Call<MednameResponse> call, Throwable t) {
                Toast.makeText(TimeSettingActivity.this, "이름 check 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("이름 check 에러 발생", t.getMessage());
            }
        });
    }
    // 약 주기 삭제 요청
    private void Delmedtable(DelmedData data) {
        service.userTime(data).enqueue(new Callback<DelmedResponse>() {
            @Override
            public void onResponse(Call<DelmedResponse> call, Response<DelmedResponse> response) {
                DelmedResponse result = response.body();
                Toast.makeText(TimeSettingActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("삭제");
            }
            @Override
            public void onFailure(Call<DelmedResponse> call, Throwable t) {
                Toast.makeText(TimeSettingActivity.this, "이름 check 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("삭제 에러 발생", t.getMessage());
            }
        });
    }

    // 저장한 약 주기 가져오기
    private void GetTimeSetting(TimeGetData data) {
        service.userGetTime(data).enqueue(new Callback<TimeGetResponse>() {
            @Override
            public void onResponse(Call<TimeGetResponse> call, Response<TimeGetResponse> response) {
                final List<String> data = new ArrayList<>();
                final ArrayAdapter<String> adp = new ArrayAdapter<>( getApplicationContext(),android.R.layout.simple_list_item_single_choice,data);
                timenameText.setAdapter(adp);

                TimeGetResponse result = response.body();
                for(int i=0; i<result.getLength();i++){ //받은 데이터 만큼 for문을 돌려서 배열에 하나씩 저장 후 listview로 출력
                    medNameApp = result.medNameApp();
                    String medname = medNameApp[i];
                    medTimeApp = result.medTimeApp();
                    String medtime = medTimeApp[i];
                    medNumApp = result.medNumApp();
                    String mednum1 = medNumApp[i];
                    code =result.getCode();
                    System.out.println(medtime);
                    System.out.println(medname);
                    //System.out.println(mednum1);
                    data.add("약 이름: "+medname+"  약 주기: "+medtime + "  칸 위치 : "+mednum1);
                }

                timenameText.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object vo = (Object)timenameText.getAdapter().getItem(position);
                        a = vo;
                    }
                });

                delBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        int pos = timenameText.getCheckedItemPosition();
                        if(pos != ListView.INVALID_POSITION){
                            data.remove(pos);
                            System.out.println("pos값"+pos+userid);
                            timenameText.clearChoices();

                            adp.notifyDataSetChanged();
                            Delmedtable(new DelmedData(pos, userid));
                        }
                    }
                });

            }
            @Override
            public void onFailure(Call<TimeGetResponse> call, Throwable t) {
                Toast.makeText(TimeSettingActivity.this, "찾기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("찾기 에러 발생", t.getMessage());
            }
        });

    }

}
