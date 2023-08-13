package com.example.food_app_design.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.food_app_design.MainActivity;
import com.example.food_app_design.R;

public class ActivityIntro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        TextView tv_Bat_Dau =findViewById(R.id.tv_bat_dau);
        tv_Bat_Dau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityIntro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}