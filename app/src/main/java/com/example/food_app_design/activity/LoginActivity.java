package com.example.food_app_design.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app_design.R;
import com.example.food_app_design.database.UserDatabase;
import com.example.food_app_design.modal.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_Email,edt_PassWord;
    ImageView img_Show_Hide;
    String email,passWord;
    TextView tv_Dang_Nhap;
    ImageView img_Back;
    List<User>list;
    int count=0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        list= UserDatabase.getInstance(this).userDAO().getList();
        unitUi();
        img_Show_Hide.setOnClickListener(this);
        tv_Dang_Nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if(list.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Đăng Nhập Thất Bại",Toast.LENGTH_LONG).show();
                    return;
                }
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getEmail().equals(email)&&list.get(i).getPassWord().equals(passWord)){
                        Toast.makeText(LoginActivity.this,"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();
                        editor.putString("email",email);
                        editor.putString("password",passWord);
                        editor.putBoolean("login",true);
                        editor.putInt("userId",list.get(i).getId());
                        editor.apply();
                        finish();
                        return;
                    }
                    else {
                        count++;
                    }
                }
                if(count!=0){
                    Toast.makeText(LoginActivity.this, "Tài Khoản Hoặc Mật Khẩu Không Đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private  void unitUi(){
        edt_Email=findViewById(R.id.edt_email);
        edt_PassWord=findViewById(R.id.edt_mat_khau);
        img_Show_Hide=findViewById(R.id.img_show_hide);
        tv_Dang_Nhap=findViewById(R.id.tv_dang_nhap);
        img_Back=findViewById(R.id.img_back);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }
    private  void getData(){
        email=edt_Email.getText().toString().trim();
        passWord=edt_PassWord.getText().toString().trim();
    }
    private  void  showAndHide(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        editor=sharedPreferences.edit();
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