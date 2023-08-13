package com.example.food_app_design.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.MyApplication;
import com.example.food_app_design.R;
import com.example.food_app_design.activity.AccountActivity;
import com.example.food_app_design.activity.DetailActicity;
import com.example.food_app_design.activity.NotifyActivity;
import com.example.food_app_design.adapter.FoodAdapter;
import com.example.food_app_design.modal.Food;
import com.example.food_app_design.utils.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    RecyclerView rcv_Home;
    FoodAdapter foodAdapter;
    List<Food>list;
    GridLayoutManager gridLayoutManager;
    ImageView imgSearch;
    EditText edt_Search;
    TextView tv_Tat_Ca,tv_Tra_Sua,tv_Banh_Mi,tv_Pizza,tv_Empty;
    ImageView img_NotifyCation,img_Account;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_fragment,container,false);
         unitUi(view);
         gridLayoutManager = new GridLayoutManager(getActivity(), 2);
         rcv_Home.setLayoutManager(gridLayoutManager);
         list = new ArrayList<>();
         //gửi dữ liệu sang bên DetailActivity
         foodAdapter=new FoodAdapter(new FoodAdapter.IclickDetail() {
             @Override
             public void detailFood(Food food) {
                 Intent intent=new Intent(getActivity(), DetailActicity.class);
                 Bundle bundle=new Bundle();
                 bundle.putSerializable("food",food);
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });
         rcv_Home.setAdapter(foodAdapter);
         getListFoods("");

         imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood();
            }
        });
        edt_Search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchFood();
                    return true;
                }
                return false;
            }
        });
        edt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do nothing
            }
            @Override
            public void afterTextChanged(Editable s) {
                String strKey = s.toString().trim();
                if (strKey.equals("") || strKey.length() == 0) {
                    list.clear();
                    if(tv_Tat_Ca.isFocusable()){
                        getListFoods("");

                    }else  if(tv_Tra_Sua.isFocusable()){
                        getListFoods("trà");

                    }else if(tv_Banh_Mi.isFocusable()){
                        getListFoods("banh");

                    }else {
                        getListFoods("pizza");
                    }


                }
            }
        });
        tv_Tat_Ca.setFocusable(true);
        if(tv_Tat_Ca.isFocusable()){
            tv_Tat_Ca.setBackground(getResources().getDrawable(R.drawable.cs_category_checked));
        }
        tv_Tat_Ca.setOnClickListener(this::onClick);
        tv_Tra_Sua.setOnClickListener(this::onClick);
        tv_Banh_Mi.setOnClickListener(this::onClick);
        tv_Pizza.setOnClickListener(this::onClick);
        img_NotifyCation.setOnClickListener(this);
        img_Account.setOnClickListener(this);
        return view;
    }
    private  void unitUi(View view){
        imgSearch = view.findViewById(R.id.img_search);
        edt_Search=view.findViewById(R.id.edt_search_name);
        rcv_Home=view.findViewById(R.id.rcv_home);
        tv_Banh_Mi=view.findViewById(R.id.tv_banh_mi);
        tv_Pizza=view.findViewById(R.id.tv_pizza);
        tv_Tat_Ca=view.findViewById(R.id.tv_tat_ca);
        tv_Tra_Sua=view.findViewById(R.id.tv_tra_sua);
        tv_Empty=view.findViewById(R.id.tv_empty);
        img_Account=view.findViewById(R.id.img_account);
        img_NotifyCation=view.findViewById(R.id.img_notifycation);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_tat_ca:
                tv_Tra_Sua.setFocusable(false);
                tv_Banh_Mi.setFocusable(false);
                tv_Pizza.setFocusable(false);
                if(!tv_Tra_Sua.isFocusable()||tv_Pizza.isFocusable()||tv_Banh_Mi.isFocusable()){
                    tv_Tra_Sua.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Banh_Mi.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Pizza.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                }
                tv_Tat_Ca.setFocusable(true);
                if(tv_Tat_Ca.isFocusable()){
                    tv_Tat_Ca.setBackground(getResources().getDrawable(R.drawable.cs_category_checked));
                }
                getListFoods("");
                break;
            case R.id.tv_tra_sua:
                tv_Tat_Ca.setFocusable(false);
                tv_Banh_Mi.setFocusable(false);
                tv_Pizza.setFocusable(false);
                if(!tv_Tat_Ca.isFocusable()||tv_Pizza.isFocusable()||tv_Banh_Mi.isFocusable()){
                    tv_Tat_Ca.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Banh_Mi.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Pizza.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                }


                tv_Tra_Sua.setFocusable(true);
                if(tv_Tra_Sua.isFocusable()){
                   tv_Tra_Sua.setBackground(getResources().getDrawable(R.drawable.cs_category_checked));
                }
                getListFoods("Trà ");
                break;
            case R.id.tv_pizza:
                tv_Tat_Ca.setFocusable(false);
                tv_Banh_Mi.setFocusable(false);
                tv_Tra_Sua.setFocusable(false);
                if(!tv_Tra_Sua.isFocusable()||tv_Tat_Ca.isFocusable()||tv_Banh_Mi.isFocusable()){
                    tv_Tra_Sua.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Banh_Mi.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Tat_Ca.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                }

                tv_Pizza.setFocusable(true);
                if(tv_Pizza.isFocusable()){
                    tv_Pizza.setBackground(getResources().getDrawable(R.drawable.cs_category_checked));
                }
                getListFoods("pizza");
                break;
            case R.id.tv_banh_mi:
                tv_Tat_Ca.setFocusable(false);
                tv_Tra_Sua.setFocusable(false);
                tv_Pizza.setFocusable(false);
                if(!tv_Tra_Sua.isFocusable()||tv_Pizza.isFocusable()||tv_Tat_Ca.isFocusable()){
                    tv_Tra_Sua.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Tat_Ca.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                    tv_Pizza.setBackground(getResources().getDrawable(R.drawable.cs_category_no_checked));
                }

                tv_Banh_Mi.setFocusable(true);
                if(tv_Banh_Mi.isFocusable()){
                    tv_Banh_Mi.setBackground(getResources().getDrawable(R.drawable.cs_category_checked));
                }
                getListFoods("bánh");
                break;
            case R.id.img_notifycation:
                startActivity(new Intent(getActivity(), NotifyActivity.class));
                break;
            case R.id.img_account:
                Intent intent=new Intent(getActivity(),AccountActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void getListFoods(String key) {
        
//        lấy dữ liệu từ trên server 
        list.clear();
        MyApplication.get(getActivity()).getDatabaseReference()
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                        Food food = dataSnapshot.getValue(Food.class);
                        if (food == null || list == null || foodAdapter == null) {
                            return;
                        }

                        if (key == null || key.equals("")) {
                            tv_Empty.setVisibility(View.GONE);
                            list.add(0, food);
                        } else {
                            if (food.getTitle().toLowerCase().trim().contains(key.toLowerCase().trim())) {
                                tv_Empty.setVisibility(View.GONE);
                                list.add(0, food);
                            }
                        }
                        if(list.size()==0){
                            tv_Empty.setVisibility(View.VISIBLE);

                        }
                        foodAdapter.setData(list);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        Food food = dataSnapshot.getValue(Food.class);
                        if (food == null || list == null || list.isEmpty() || foodAdapter == null) {
                            return;
                        }
                        for (Food foodDelete : list) {
                            if (food.getId() == foodDelete.getId()) {
                                list.remove(foodDelete);
                                break;
                            }
                        }
                        foodAdapter.setData(list);
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
    private void searchFood() {
        String strKey = edt_Search.getText().toString().trim();
        list.clear();
        getListFoods(strKey);
        Utils.hideSoftKeyboard(getActivity());
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy");
    }
}
