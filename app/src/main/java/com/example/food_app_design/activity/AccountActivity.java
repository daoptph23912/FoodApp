package com.example.food_app_design.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.food_app_design.R;

public class AccountActivity extends AppCompatActivity {
    Boolean is_Login;
    TextView tv_Email,tv_Mat_Khau;
    TextView tv_Dang_Ky,tv_Dang_Nhap;
    TextView tv_Chua_Co_TK;
    TextView tv_Dang_Xuat;
    LinearLayout layout_Visiable;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String email,passWord;
    ImageView img_Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        unitUi();
    }
    private void unitUi(){
        tv_Email=findViewById(R.id.tv_email);
        tv_Mat_Khau=findViewById(R.id.tv_mat_khau);
        tv_Dang_Ky=findViewById(R.id.tv_dang_ky);
        tv_Dang_Nhap=findViewById(R.id.tv_dang_nhap);
        tv_Dang_Xuat=findViewById(R.id.tv_dang_xuat);
        layout_Visiable=findViewById(R.id.visiable_layout);
        tv_Chua_Co_TK=findViewById(R.id.tv_chua_co_tk);
        img_Back=findViewById(R.id.img_back);
    }
    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        is_Login=sharedPreferences.getBoolean("login",false);
        if(is_Login){
            email=sharedPreferences.getString("email","");
            passWord=sharedPreferences.getString("password","");
            layout_Visiable.setVisibility(View.VISIBLE);
            tv_Email.setText(email);
            tv_Mat_Khau.setText(passWord);
            tv_Dang_Ky.setVisibility(View.GONE);
            tv_Dang_Nhap.setVisibility(View.GONE);
            tv_Chua_Co_TK.setVisibility(View.GONE);
            tv_Dang_Xuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(AccountActivity.this);
                    builder.setMessage("Bạn Có Chắc Chắn Muốn Đăng Xuất Không").setTitle("Đăng Xuất Khỏi Trái Đất")
                            .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    editor.putBoolean("login",false);
                                    editor.apply();
                                    startActivity(new Intent(AccountActivity.this,ActivityIntro.class));
                                    finish();
                                }
                            });
                    builder.show();

                }
            });
        }else {
            tv_Chua_Co_TK.setVisibility(View.VISIBLE);
            layout_Visiable.setVisibility(View.GONE);
            tv_Dang_Ky.setVisibility(View.VISIBLE);
            tv_Dang_Nhap.setVisibility(View.VISIBLE);
            tv_Dang_Nhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AccountActivity.this,LoginActivity.class));

                }
            });
            tv_Dang_Ky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AccountActivity.this,SingUpActivity.class));
                }
            });

        }
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}