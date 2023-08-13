package com.example.food_app_design.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_app_design.R;

public class FeedBackFragment extends Fragment {
    EditText edt_Ten,edt_Sdt,edt_Email,edt_Binh_Luan;
    TextView tv_Gui_Phan_Hoi;
    String ten,sdt,email,binhLuan;
    ImageView img_Back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback_fragment,container,false);
        unitUi(view);
        tv_Gui_Phan_Hoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if(ten.length()==0||binhLuan.length()==0){
                    Toast.makeText(getActivity(), "Bạn Phải Điền Đẩy Đủ Thông Tin Cần Thiết", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Cảm Ơn Những Đóng Góp Của Bạn", Toast.LENGTH_SHORT).show();
                    edt_Ten.setText("");
                    edt_Sdt.setText("");
                    edt_Email.setText("");
                    edt_Binh_Luan.setText("");
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
        edt_Ten=view.findViewById(R.id.edt_ten);
        edt_Sdt=view.findViewById(R.id.edt_sdt);
        edt_Email=view.findViewById(R.id.edt_email);
        edt_Binh_Luan=view.findViewById(R.id.edt_binh_luan);
        tv_Gui_Phan_Hoi=view.findViewById(R.id.tv_gui_phan_hoi);
        img_Back=view.findViewById(R.id.img_back);
    }
    private  void getData(){
        ten=edt_Ten.getText().toString().trim();
        sdt=edt_Sdt.getText().toString().trim();
        email=edt_Email.getText().toString().trim();
        binhLuan=edt_Binh_Luan.getText().toString().trim();

    }

}
