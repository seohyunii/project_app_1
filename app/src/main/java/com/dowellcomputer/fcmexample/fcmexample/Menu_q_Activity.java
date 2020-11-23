package com.dowellcomputer.fcmexample.fcmexample;


import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Menu_q_Activity extends AppCompatActivity {


    Menu_Adapter Menu_adapter;
    ViewPager viewPager;
    ImageButton menu_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_qmark);

        viewPager = (ViewPager) findViewById(R.id.view2);
        menu_back = (ImageButton) findViewById(R.id.menu_back);

        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Menu_adapter = new Menu_Adapter(this);
        viewPager.setAdapter(Menu_adapter);
    }

}

