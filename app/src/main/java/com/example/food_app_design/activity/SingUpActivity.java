package com.example.food_app_design.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_app_design.R;
import com.example.food_app_design.database.UserDatabase;
import com.example.food_app_design.modal.User;

public class SingUpActivity extends AppCompatActivity {
    EditText edt_Email,edt_Mat_Khau;
    TextView tv_Dang_Ky;
    String email,matKhau;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        img_back=findViewById(R.id.img_back);
        edt_Email=findViewById(R.id.edt_email);
        edt_Mat_Khau=findViewById(R.id.edt_mat_khau);
        tv_Dang_Ky=findViewById(R.id.tv_dang_ky);
        tv_Dang_Ky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if(matKhau.length()==0||email.length()==0){
                    Toast.makeText(SingUpActivity.this,"Vui Lòng Nhập Đầy Đủ Thông Tin",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(SingUpActivity.this,"Đăng Ký Tài Khoản Thành Công",Toast.LENGTH_LONG).show();
                edt_Email.setText("");
                edt_Mat_Khau.setText("");
                User user=new User(email,matKhau);
                UserDatabase.getInstance(getApplicationContext()).userDAO().insert(user);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private  void getData(){
        email=edt_Email.getText().toString().trim();
        matKhau=edt_Mat_Khau.getText().toString().trim();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}