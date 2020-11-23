package com.dowellcomputer.fcmexample.fcmexample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Med_q_Activity extends AppCompatActivity {


    Med_Adapter Med_Adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_qmark);

        viewPager = (ViewPager) findViewById(R.id.view3);

        Med_Adapter = new Med_Adapter(this);
        viewPager.setAdapter(Med_Adapter);
    }

}