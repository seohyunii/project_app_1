package com.dowellcomputer.fcmexample.fcmexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PatientImgActivity extends AppCompatActivity {
    String userid, patient_id;
    //환자 상태 확인 메뉴 엑티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_img);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        patient_id = intent.getStringExtra("patient_id");
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);
        ImageButton logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        TextView img_text = (TextView) findViewById(R.id.img_text);

        WebView webView = (WebView)findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.setBackgroundColor(255);

        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);

        //영상 스트리밍이 실행되고 있는 주소
        String piAddr = "http://192.168.0.162:8090/?action=stream";
        //webviw를 이용해 주소에 띄워진 영상을 보여줌
        webView.loadUrl(piAddr);


        //로그아웃 버튼
        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(PatientImgActivity.this, "로그아웃 되셨습니다", Toast.LENGTH_LONG).show();
                Intent logoutIntent = new Intent(PatientImgActivity.this, MainActivity.class);
                PatientImgActivity.this.startActivity(logoutIntent);
            }
    });
    //뒤로가기 버튼
        returnBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent ReturnIntent = new Intent(PatientImgActivity.this, MenuActivity.class);
            ReturnIntent.putExtra("userid", userid);
            ReturnIntent.putExtra("patient_id",patient_id);
            PatientImgActivity.this.startActivity(ReturnIntent);
        }
    });

    String inDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

        System.out.println(inDate+"!!!");
        img_text.setText(inDate+"\n"+userid+" 환자의 상태 입니다");

    }
}

