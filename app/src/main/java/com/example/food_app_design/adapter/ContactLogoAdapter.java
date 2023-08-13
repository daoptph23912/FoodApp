package com.example.food_app_design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.modal.ContactLogo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactLogoAdapter extends RecyclerView.Adapter<ContactLogoAdapter.ContactLogoViewHolder> {
    List<ContactLogo>list;
    public void  setData(List<ContactLogo>logoList){
        this.list=logoList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ContactLogoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contact,parent,false);

        return new ContactLogoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactLogoViewHolder holder, int position) {
    ContactLogo contactLogo=list.get(position);
    if(contactLogo==null){
        return;
    }
    holder.img_Logo.setImageResource(contactLogo.getImage());
    holder.tv_Logo_Name.setText(contactLogo.getLogoName());

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public  class ContactLogoViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img_Logo;
        TextView tv_Logo_Name;
        public ContactLogoViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_Logo_Name=itemView.findViewById(R.id.tv_logo_name);
            img_Logo=itemView.findViewById(R.id.img_logo);
        }
    }

}
