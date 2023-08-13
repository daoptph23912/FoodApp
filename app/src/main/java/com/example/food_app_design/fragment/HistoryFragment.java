package com.example.food_app_design.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.adapter.HistoryAdapter;
import com.example.food_app_design.modal.Food;
import com.example.food_app_design.modal.History;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    RecyclerView rcv_History;
    HistoryAdapter historyAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<History>list=new ArrayList<>();
    SharedPreferences sharedPreferences;
    ImageView img_Back;
    int userIdd;
    TextView tv_Empty;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.history_fragment,container,false);
        rcv_History=view.findViewById(R.id.rcv_history);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rcv_History.setLayoutManager(linearLayoutManager);
        historyAdapter=new HistoryAdapter();
        img_Back=view.findViewById(R.id.img_back);
        tv_Empty=view.findViewById(R.id.tv_empty);
        if(list.isEmpty()){
            tv_Empty.setVisibility(View.VISIBLE);
        }
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        rcv_History.setAdapter(historyAdapter);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("list history");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                History userId = snapshot.getValue(History.class);
                if (userId == null || list == null || historyAdapter == null) {
                    return;
                }
                if(userIdd==(userId.getUserId())){
                    list.add(0, userId);
                    tv_Empty.setVisibility(View.GONE);
                }else {
                    return;
                }
                historyAdapter.setData(list);
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

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences=getActivity().getSharedPreferences("info",getActivity().MODE_PRIVATE);
        userIdd=sharedPreferences.getInt("userId",0);
    }
}
