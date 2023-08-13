package com.example.food_app_design.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.adapter.ContactLogoAdapter;
import com.example.food_app_design.modal.ContactLogo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {
    TextView tv_Slogan;
    List<ContactLogo>list;
    GridLayoutManager gridLayoutManager;
    RecyclerView rcv_Contact;
    ContactLogoAdapter contactLogoAdapter;
    ImageView img_Back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.contact_fragment,container,false);
        list=new ArrayList<>();
        contactLogoAdapter=new ContactLogoAdapter();
        rcv_Contact=view.findViewById(R.id.rcv_contact);
        tv_Slogan=view.findViewById(R.id.tv_slogan);
        tv_Slogan.setText("Highly committed to quality  products & excellent services");
        gridLayoutManager=new GridLayoutManager(getActivity(),3);
        rcv_Contact.setLayoutManager(gridLayoutManager);
        rcv_Contact.setAdapter(contactLogoAdapter);
        img_Back=view.findViewById(R.id.img_back);
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        setData();
        return view;
    }
    private  void setData(){
        if(list.size()>0){
            list.clear();
        }
        list.add(new ContactLogo(R.drawable.face,"Facebook"));
        list.add(new ContactLogo(R.drawable.call_phone,"Call Phone"));
        list.add(new ContactLogo(R.drawable.gmail,"Gmail"));
        list.add(new ContactLogo(R.drawable.skyper,"Skyper"));
        list.add(new ContactLogo(R.drawable.ytb,"Youtuber"));
        list.add(new ContactLogo(R.drawable.zalo,"Zalo"));
        contactLogoAdapter.setData(list);

    }

}
