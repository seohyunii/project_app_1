package com.dowellcomputer.fcmexample.fcmexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //시작화면 - 로그인
    private ProgressBar mProgressView;
    private ServiceApi service;
    EditText idText;
    EditText passwordText;
    ImageButton registerBtn;
    ImageButton loginBtn;
    private String token;
    private Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM Log", "getInstanceId failed", task.getException());
                            return;
                        }
                        token = task.getResult().getToken();
                        Log.d("FCM Log", "FCM 토큰: " + token);
                        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        registerBtn = (ImageButton) findViewById(R.id.RegisterBtn);
        loginBtn = (ImageButton) findViewById(R.id.loginBtn);
        mProgressView = (ProgressBar) findViewById(R.id.join_progress);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        //회원가입 버튼
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                registerIntent.putExtra("token",token);
                MainActivity.this.startActivity(registerIntent);
            }
        });
        //로그인 버튼
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    //로그인 버튼을 눌렀을때 호출되는 함수 호출
    private void attemptLogin() {
        idText.setError(null);
        passwordText.setError(null);

        String id = idText.getText().toString();
        String password = passwordText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            passwordText.setError("비밀번호를 입력해주세요.");
            focusView = idText;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordText.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = passwordText;
            cancel = true;
        }
        // 이메일의 유효성 검사
        if (id.isEmpty()) {
            idText.setError("아이디를 입력해주세요.");
            focusView = idText;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(id, password));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        //user 서버 api 호출
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                if (result.getCode() == 200) {
                    Intent loginIntent = new Intent(MainActivity.this, MenuActivity.class);
                    loginIntent.putExtra("userid", result.getUserId());
                    loginIntent.putExtra("patient_id", result.getPatient_id());
                    System.out.println("보낼아이디"+result.getUserId());
                    MainActivity.this.startActivity(loginIntent);}
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }
    // 호출시딜레이발생시 progressbar 보여줌
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
