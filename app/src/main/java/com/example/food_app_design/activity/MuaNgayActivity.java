package com.example.food_app_design.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_app_design.R;
import com.example.food_app_design.modal.Food;
import com.example.food_app_design.modal.History;
import com.example.food_app_design.modal.Notify;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MuaNgayActivity extends AppCompatActivity {
    Food food;
    ImageView img_food;
    TextView tv_Ten,tv_Sdt,tv_Dia_Chi,tv_So_Luong,tv_Gia_Tien;
    TextView tv_Huy_Bo,tv_Dat_Hang;
    SharedPreferences sharedPreferences;
    int userId;
    int quantityHistory;
    FirebaseDatabase firebaseDatabase_History;
    DatabaseReference reference_History;
    FirebaseDatabase firebaseDatabase_Notify;
    DatabaseReference reference_Notify;
    String phuongThuc,ten,sdt,diaChi;
    EditText edt_Ten,edt_Sdt,edt_Dia_Chi,edt_Phuong_Thuc;
    ImageView img_Plus,img_Minus;
    int amount=1;
    int quantityNotify;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_ngay);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        food=(Food)bundle.getSerializable("food");
        tv_Ten=findViewById(R.id.tv_ten);
        tv_Sdt=findViewById(R.id.tv_sdt);
        edt_Ten=findViewById(R.id.edt_ten);
        edt_Dia_Chi=findViewById(R.id.edt_dia_chi);
        edt_Sdt=findViewById(R.id.edt_sdt);
        tv_So_Luong=findViewById(R.id.tv_amount);
        img_Plus=findViewById(R.id.img_plus);
        img_Minus=findViewById(R.id.img_minus);
        edt_Phuong_Thuc=findViewById(R.id.edt_phuong_thuc);
        tv_Dia_Chi=findViewById(R.id.tv_dia_chi);
        tv_Gia_Tien=findViewById(R.id.tv_tong_tien_tat_ca);
        img_food=findViewById(R.id.img_food);
        tv_Dat_Hang=findViewById(R.id.tv_dat_hang_sheet);
        tv_Huy_Bo=findViewById(R.id.tv_huy_bo_sheet);
        Picasso.get().load(food.getImage()).into(img_food);
        tv_Gia_Tien.setText("Giá:"+" "+food.getPrice());
        tv_Ten.setText("Món:"+" "+food.getTitle());
        tv_So_Luong.setText(amount+"");
        img_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount++;
                tv_So_Luong.setText(amount+"");
            }
        });
        img_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount==1){
                    amount=1;
                    tv_So_Luong.setText(amount+"");
                }else {
                    amount--;
                    tv_So_Luong.setText(amount+"");

                }
            }
        });
        tv_Dat_Hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInformation();
                Toast.makeText(MuaNgayActivity.this, "Đặt Hàng Thành Công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        tv_Huy_Bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private  void getInformation(){
        getData();
        if(ten.length()==0||sdt.length()==0||diaChi.length()==0){
            Toast.makeText(this, "Bạn Cần Phải Điền Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
            return;
        }
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
        String time;
        time=("" + sdf3.format(date));
        Random random=new Random(1000000000);
        int random_Id=random.nextInt(1000000000);
            firebaseDatabase_History=FirebaseDatabase.getInstance();
            firebaseDatabase_Notify=FirebaseDatabase.getInstance();
            reference_History=firebaseDatabase_History.getReference("list history");
            reference_Notify=firebaseDatabase_Notify.getReference("list notify");
            quantityHistory++;
            quantityNotify++;
            food.setAmountBuy(amount);
            History history=new History(String.valueOf(random_Id),ten,sdt,diaChi,food.getAmountBuy(),food.getTitle(),time ,food.getPrice()*food.getAmountBuy(),phuongThuc,userId );
            reference_History.child(quantityHistory+"").setValue(history);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            Notify notify=new Notify(quantityNotify,"Bạn vừa đặt hàng"+" "+food.getTitle(),time,userId);
            editor.putInt("quantityHistory",quantityHistory);
            editor.putInt("quantityNotify",quantityNotify);
            reference_Notify.child(quantityNotify+"").setValue(notify);
            editor.apply();

    }
    private  void getData(){
        phuongThuc=edt_Phuong_Thuc.getText().toString();
        ten=edt_Ten.getText().toString().trim();
        sdt=edt_Sdt.getText().toString().trim();
        diaChi=edt_Dia_Chi.getText().toString().trim();
    }
    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        userId=sharedPreferences.getInt("userId",0);
        quantityHistory=sharedPreferences.getInt("quantityHistory",0);
        quantityNotify=sharedPreferences.getInt("quantityNotify",0);
    }
}