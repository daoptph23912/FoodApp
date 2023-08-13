package com.example.food_app_design.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_app_design.R;
import com.example.food_app_design.modal.Food;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActicity extends AppCompatActivity  {
    ImageView img_Food;
    TextView tv_Discount,tv_Title,tv_Price_Discount,tv_Price_Root,tv_Description;
    TextView tv_Mua_Ngay,tv_Them_Vao_Gio_Hang,tv_Huy_Bo,tv_Them_Vao_Gio_Hang_Sheet;
    Food food;
    int discount;
    private BottomSheetBehavior bottomSheetBehavior;
    RelativeLayout layout_Bottom_Sheet;
    TextView tv_Title_Food,tv_Price_Food,tv_Amount;
    ImageView img_Food_Detail,img_Plus,img_Minus;
    int amount=1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences2;
    int amount_List_Cart;
    int idUser;
    boolean isLogin;
    ImageView img_Back,img_Cart;
    int idDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acticity);
        untitUi();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        food=(Food)bundle.getSerializable("food");
        bottomSheetBehavior=BottomSheetBehavior.from(layout_Bottom_Sheet);
        setData();
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showInfor();
            }
        });

        tv_Them_Vao_Gio_Hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           showInfor();
            }
        });
        tv_Mua_Ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    Intent muaNgay=new Intent(DetailActicity.this,MuaNgayActivity.class);
                    Bundle bundle_MuaNgay=new Bundle();
                    bundle_MuaNgay.putSerializable("food",food);
                    muaNgay.putExtras(bundle_MuaNgay);
                    startActivity(muaNgay);
                }else {
                    Toast.makeText(getApplicationContext(),"Bạn Cần Phải Đăng Nhập",Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });


    }
    private  void untitUi(){
        layout_Bottom_Sheet=findViewById(R.id.layout_bottom_sheet);
        img_Food=findViewById(R.id.img_food);
        tv_Discount=findViewById(R.id.tv_discount);
        tv_Title=findViewById(R.id.tv_title);
        tv_Price_Discount=findViewById(R.id.tv_price_discount);
        tv_Price_Root=findViewById(R.id.tv_price_root);
        tv_Description=findViewById(R.id.tv_description);
        tv_Mua_Ngay=findViewById(R.id.tv_mua_ngay);
        tv_Them_Vao_Gio_Hang=findViewById(R.id.tv_them_vao_gio_hang);
        tv_Title_Food=findViewById(R.id.tv_title_food);
        tv_Price_Food=findViewById(R.id.tv_price_food);
        img_Food_Detail=findViewById(R.id.img_food_detail);
        tv_Huy_Bo=findViewById(R.id.tv_huy_bo);
        tv_Them_Vao_Gio_Hang_Sheet=findViewById(R.id.tv_them_vao_gio_hang_sheet);
        img_Minus=findViewById(R.id.img_minus);
        img_Plus=findViewById(R.id.img_plus);
        tv_Amount=findViewById(R.id.tv_amount);
        img_Back=findViewById(R.id.img_back);
        img_Cart=findViewById(R.id.img_cart);
    }
    private  void setData(){
        discount=food.getDiscount();
        Picasso.get().load(food.getImage()).into(img_Food);
        tv_Description.setText(food.getDescription());
        tv_Title.setText(food.getTitle());
        if(discount!=0){
            tv_Discount.setVisibility(View.VISIBLE);
            tv_Discount.setText("Giảm"+" "+food.getDiscount()+"%");
            tv_Price_Discount.setVisibility(View.VISIBLE);
            tv_Price_Root.setText(food.getPrice()+"VND");
            tv_Price_Root.setTextColor(getResources().getColor(R.color.black));
            tv_Price_Root.setPaintFlags(tv_Price_Root.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            tv_Price_Discount.setText("Gía"+" "+food.totalMoney()+"VND");
            tv_Price_Discount.setTextSize((int)17);
            String title = tv_Price_Discount.getText().toString();
            Spannable ss=new SpannableString(title);
            ForegroundColorSpan fcsRed1=new ForegroundColorSpan(getResources().getColor(R.color.del_text));
            ForegroundColorSpan fcsRed2=new ForegroundColorSpan(getResources().getColor(R.color.no_del));
            ss.setSpan(fcsRed1,0,2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(fcsRed2,3,title.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_Price_Discount.setText(ss);
            return;
        }
        tv_Price_Root.setText("Gía"+" "+food.getPrice()+"VND");
        String title = tv_Price_Root.getText().toString();
        Spannable ss=new SpannableString(title);
        ForegroundColorSpan fcsRed1=new ForegroundColorSpan(getResources().getColor(R.color.del_text));
        ForegroundColorSpan fcsRed2=new ForegroundColorSpan(getResources().getColor(R.color.no_del));
        ss.setSpan(fcsRed1,0,2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsRed2,3,title.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_Price_Root.setText(ss);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences=getSharedPreferences("amount_list_cart",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        amount_List_Cart=sharedPreferences.getInt("list_cart",0);
        sharedPreferences2=getSharedPreferences("info",MODE_PRIVATE);
        isLogin=sharedPreferences2.getBoolean("login",false);
        idDelete=sharedPreferences.getInt("idDelete",0);
    }
    private  void showInfor(){
        if(bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            if(discount!=0){
                tv_Price_Food.setText(food.totalMoney()+"VND");
            }else {
                tv_Price_Food.setText(food.getPrice()+"VND");
            }
            tv_Title_Food.setText(food.getTitle());
            Picasso.get().load(food.getImage()).into(img_Food_Detail);
            img_Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(amount==1){
                        amount=1;
                        tv_Amount.setText(amount+"");
                    }else {
                        amount--;
                        tv_Amount.setText(amount+"");

                    }

                }
            });
            img_Plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount++;
                    tv_Amount.setText(amount+"");

                }
            });



            tv_Them_Vao_Gio_Hang_Sheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isLogin){
                        idUser=sharedPreferences2.getInt("userId",0);
                        amount_List_Cart++;
                        idDelete++;
                        editor.putInt("list_cart",amount_List_Cart);
                        editor.putInt("idDelete",idDelete);
                        editor.apply();
                        tv_Them_Vao_Gio_Hang.setBackground(getResources().getDrawable(R.drawable.cs_huy_bo));
                        tv_Them_Vao_Gio_Hang.setClickable(false);
                        firebaseDatabase=FirebaseDatabase.getInstance();
                        reference=firebaseDatabase.getReference("list cart");
                        food.setAmountBuy(amount);
                        food.setUserId(idUser);
                        food.setIdDelete(idDelete);
                        reference.child(amount_List_Cart+"").setValue(food);

                        Toast.makeText(DetailActicity.this, "Thêm Thành Công !!!", Toast.LENGTH_SHORT).show();
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }   else {
                        Toast.makeText(DetailActicity.this, "Bạn Cần Phải Đăng Nhập", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
            tv_Huy_Bo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });


        }else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}