package com.example.food_app_design.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.activity.AccountActivity;
import com.example.food_app_design.activity.ActivityIntro;
import com.example.food_app_design.adapter.CartAdapter;
import com.example.food_app_design.modal.Food;
import com.example.food_app_design.modal.History;
import com.example.food_app_design.modal.Notify;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CartFragment extends Fragment {
    CartAdapter cartAdapter;
    RecyclerView rcv_Cart;
    List<Food>list=new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase_History;
    DatabaseReference reference_History;
    FirebaseDatabase firebaseDatabase_Notify;
    DatabaseReference reference_Notify;
    TextView tv_Tong_Tien,tv_Dat_Hang,tv_Empty;
    int userId;
    SharedPreferences sharedPreferences;
    int total=0;
    private BottomSheetBehavior bottomSheetBehavior;
    RelativeLayout layout_Bottom_Sheet;
    TextView tv_Ten,tv_So_Luong,tv_Tong_Tien_Tat_Ca;
    TextView tv_Dat_Hang_Sheet,tv_Huy_Bo_Sheet;
    EditText edt_Ten,edt_Sdt,edt_Dia_Chi,edt_Phuong_Thuc;
    String phuongThuc,ten,sdt,diaChi;
    int quantityHistory;
    int quantityNotify;
    ImageView img_Back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cart_fragment,container,false);
        unitUi(view);
        checkData();
        loadData();
        tv_Dat_Hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    tv_Tong_Tien_Tat_Ca.setText(total+" "+"VND");
                    String title="";
                    String quantity="";
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getTitle().length()>25){
                            title+=list.get(i).getTitle()+"("+list.get(i).getPrice()+")"+"\n";
                            quantity+="- Số Lượng:"+" "+list.get(i).getAmountBuy()+"\n"+"\n";
                        }else {
                            title+=list.get(i).getTitle()+"("+list.get(i).getPrice()+")"+"\n";
                            quantity+="- Số Lượng:"+" "+list.get(i).getAmountBuy()+"\n";
                        }
                    }
                    tv_Ten.setText(title);
                    tv_So_Luong.setText(quantity);
                    tv_Dat_Hang_Sheet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getData();
                            if(sdt.length()==0||diaChi.length()==0){
                                Toast.makeText(getActivity(), "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            firebaseDatabase_History=FirebaseDatabase.getInstance();
                            reference_History=firebaseDatabase_History.getReference("list history");
                            getInformation();
                            Toast.makeText(getActivity(),"Đặt Hàng Thành Công Vui Lòng Kiểm Tra Trong Lịch Sử",Toast.LENGTH_LONG).show();
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            //code pay here
                            for(int i=0;i<list.size();i++){
                                reference.child(list.get(i).getIdDelete()+"").removeValue();
                            }
                            list.clear();
                            cartAdapter.notifyDataSetChanged();
                            tv_Tong_Tien.setText("000000 VND");
                            tv_Dat_Hang.setEnabled(false);
                            tv_Empty.setVisibility(View.VISIBLE);
                            tv_Dat_Hang.setBackground(getResources().getDrawable(R.drawable.cs_huy_bo));
                        }
                    });
                    tv_Huy_Bo_Sheet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    });
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }
    private  void unitUi(View view){
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("list cart");
        cartAdapter=new CartAdapter(new CartAdapter.IClick() {
           @Override
           public void delete(Food food, int position) {
               AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
               builder.setMessage("Bạn Có Muốn Chắc Chắn Xóa Sản Phẩm "+" "+food.getTitle()+"Này Không").setTitle("Xóa")
                       .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                           }
                       })
                       .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               //code delete solution
                               reference.child(food.getIdDelete()+"").removeValue();
                               list.remove(position);
                               cartAdapter.notifyDataSetChanged();
                               total=total-(food.getPrice()*food.getAmountBuy());
                               tv_Tong_Tien.setText("Tổng Tiền"+" "+total+"VND");
                               if(list.isEmpty()){
                                   tv_Empty.setVisibility(View.VISIBLE);
                               }
                               if(total==0){
                                   tv_Tong_Tien.setText("000000VND");
                                   tv_Dat_Hang.setEnabled(false);
                                   tv_Dat_Hang.setBackground(getResources().getDrawable(R.drawable.cs_huy_bo));
                               }
                               Toast.makeText(getActivity(), "Xóa Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                           }
                       });
               builder.show();
           }
       });
        rcv_Cart=view.findViewById(R.id.rcv_cart);
        tv_Dat_Hang=view.findViewById(R.id.tv_dat_hang);
        layout_Bottom_Sheet=view.findViewById(R.id.layout_bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(layout_Bottom_Sheet);
        tv_Tong_Tien=view.findViewById(R.id.tv_tong_tien);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rcv_Cart.setLayoutManager(linearLayoutManager);
        rcv_Cart.setAdapter(cartAdapter);
        tv_Ten=view.findViewById(R.id.tv_ten);
        tv_So_Luong=view.findViewById(R.id.tv_so_luong);
        tv_Tong_Tien_Tat_Ca=view.findViewById(R.id.tv_tong_tien_tat_ca);
        tv_Huy_Bo_Sheet=view.findViewById(R.id.tv_huy_bo_sheet);
        tv_Dat_Hang_Sheet=view.findViewById(R.id.tv_dat_hang_sheet);
        edt_Ten=view.findViewById(R.id.edt_ten);
        edt_Dia_Chi=view.findViewById(R.id.edt_dia_chi);
        edt_Sdt=view.findViewById(R.id.edt_sdt);
        edt_Phuong_Thuc=view.findViewById(R.id.edt_phuong_thuc);
        tv_Empty=view.findViewById(R.id.tv_empty);
        img_Back=view.findViewById(R.id.img_back);
    }
    private  void getData(){
        phuongThuc=edt_Phuong_Thuc.getText().toString();
        ten=edt_Ten.getText().toString().trim();
        sdt=edt_Sdt.getText().toString().trim();
        diaChi=edt_Dia_Chi.getText().toString().trim();
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    private  void getInformation(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
        String time;
        time=("" + sdf3.format(date));
        Random random=new Random(1000000000);
        for(int i=0;i<list.size();i++){
          int random_Id=random.nextInt(1000000000);
          quantityHistory++;
          quantityNotify++;
          firebaseDatabase_Notify=FirebaseDatabase.getInstance();
          reference_Notify=firebaseDatabase_Notify.getReference("list notify");
          History history=new History(String.valueOf(random_Id),ten,sdt,diaChi,list.get(i).getAmountBuy(),list.get(i).getTitle(),time ,list.get(i).getPrice()*list.get(i).getAmountBuy(),phuongThuc,userId );
          reference_History.child(quantityHistory+"").setValue(history);
          SharedPreferences.Editor editor= sharedPreferences.edit();
          editor.putInt("quantityHistory",quantityHistory);
          editor.putInt("quantityNotify",quantityNotify);
          Notify notify=new Notify(quantityNotify,"Bạn vừa đặt hàng"+" "+list.get(i).getTitle(),time,userId);
          reference_Notify.child(quantityNotify+"").setValue(notify);
          editor.apply();
      }
    }
    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences=getActivity().getSharedPreferences("info",getActivity().MODE_PRIVATE);
        userId=sharedPreferences.getInt("userId",0);
        quantityHistory=sharedPreferences.getInt("quantityHistory",0);
        quantityNotify=sharedPreferences.getInt("quantityNotify",0);
    }
    private  void loadData(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Food food = dataSnapshot.getValue(Food.class);
                if (food == null || list == null || cartAdapter == null) {
                    return;
                }
                if(food.getUserId()==userId){
                    list.add(0, food);
                    if(food.getDiscount()!=0){
                        total+=food.totalMoney()*food.getAmountBuy();
                    }else {
                        total+=food.getPrice()*food.getAmountBuy();
                    }
                    tv_Tong_Tien.setText("Tổng Tiền"+" "+total);
                    tv_Dat_Hang.setEnabled(true);
                    tv_Dat_Hang.setClickable(true);
                    tv_Dat_Hang.setFocusable(true);
                    tv_Dat_Hang.setBackground(getResources().getDrawable(R.drawable.cs_them_gio_hang));
                    tv_Empty.setVisibility(View.GONE);
                    cartAdapter.setData(list);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                Food food = dataSnapshot.getValue(Food.class);
//                if (food == null || list == null || list.isEmpty() || cartAdapter == null) {
//                    return;
//                }
//                for (Food foodDelete : list) {
//                    if (food.getId() == foodDelete.getId()) {
//                        list.remove(foodDelete);
//                        break;
//                    }
//                }
             //   cartAdapter.setData(list);


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private  void checkData(){
        if(list.size()==0){
            tv_Tong_Tien.setText("000000 VND");
            tv_Dat_Hang.setBackground(getResources().getDrawable(R.drawable.cs_huy_bo));
            tv_Dat_Hang.setClickable(false);
            tv_Dat_Hang.setFocusable(false);
            tv_Dat_Hang.setEnabled(false);
            tv_Empty.setVisibility(View.VISIBLE);
        }

    }
}
