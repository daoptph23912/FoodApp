package com.example.food_app_design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.activity.NotifyActivity;
import com.example.food_app_design.modal.Notify;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder> {
    List<Notify> list;
    NotifyActivity notifyActivity;

    public NotifyAdapter(NotifyActivity notifyActivity) {
        this.notifyActivity = notifyActivity;
    }

    public void setData(List<Notify>notifyList){
        this.list=notifyList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notify,parent,false);

        return new NotifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {
        Notify notify=list.get(position);
        if(notify==null){
            return;
        }
        holder.tv_notify.setText(notify.getTitle());
        notifyActivity.handlerTimes(notify.getTimes());
        holder.tv_times.setText(notifyActivity.diffMinutes+" "+ " Phút Trước");
    }
    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }
    public  class  NotifyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_notify,tv_times;
        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notify=itemView.findViewById(R.id.tv_notify);
            tv_times=itemView.findViewById(R.id.tv_times);

        }
    }
}
