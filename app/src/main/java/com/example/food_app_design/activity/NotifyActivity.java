package com.example.food_app_design.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_app_design.R;
import com.example.food_app_design.adapter.NotifyAdapter;
import com.example.food_app_design.modal.History;
import com.example.food_app_design.modal.Notify;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotifyActivity extends AppCompatActivity {
    RecyclerView rcv_Notify;
    NotifyAdapter notifyAdapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<Notify>list=new ArrayList<>();
    int userIdd;
    SharedPreferences sharedPreferences;
    TextView tv_Empty;
    public long diff, diffSeconds ,diffMinutes,diffHours;
    public String timeEnd;
    ImageView img_Back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        rcv_Notify=findViewById(R.id.rcv_notify);
        linearLayoutManager=new LinearLayoutManager(this);
        rcv_Notify.setLayoutManager(linearLayoutManager);
        notifyAdapter=new NotifyAdapter(this);
        tv_Empty=findViewById(R.id.tv_empty);
        rcv_Notify.setAdapter(notifyAdapter);
        img_Back=findViewById(R.id.img_back);
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        firebaseDatabase= FirebaseDatabase.getInstance();
        if(list.size()==0){
            tv_Empty.setVisibility(View.VISIBLE);
        }
        reference=firebaseDatabase.getReference("list notify");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Notify notify = snapshot.getValue(Notify.class);

                if (notify == null || list == null || notifyAdapter == null) {
                    return;
                }
                if(userIdd==(notify.getUserId())){
                    list.add(0, notify);
                }else {
                    return;
                }
                if(list.size()>0){
                    tv_Empty.setVisibility(View.GONE);

                }else {
                    tv_Empty.setVisibility(View.VISIBLE);
                }
                notifyAdapter.setData(list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        userIdd=sharedPreferences.getInt("userId",0);
    }

    public void handlerTimes(String timeStart){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
//        timeStart=format.format(timeStart);
        timeEnd=  format.format(date);
    //        Custom date format
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(timeStart);
            d2 = format.parse(timeEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Get msec from each, and subtract.
         diff = d2.getTime() - d1.getTime();
         diffSeconds = diff / 1000 % 60;
         diffMinutes = diff / (60 * 1000) % 60;
         diffHours = diff / (60 * 60 * 1000);
    }
}




