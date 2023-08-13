package com.example.food_app_design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.modal.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHoler> {
    List<History>list;
    public void setData(List<History>historyList){
        this.list=historyList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public HistoryViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_history,parent,false);

        return new HistoryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHoler holder, int position) {
        History history=list.get(position);
        if(history==null){
            return;
        }
        holder.tv_Ma_Don_Hang.setText(history.getMaDonHang());
        holder.tv_Ten.setText(history.getHoTen());
        holder.tv_Thanh_Toan.setText(history.getThanhToan());
        holder.tv_Tong_Tien.setText(history.getTongTien()+"");
        holder.tv_Ngay_Dat_Hang.setText(history.getNgayDatHang());
        holder.tv_Dia_Chi.setText(history.getDiaChiNhan());
        holder.tv_Sdt.setText(history.getSoDienThoai());
        holder.tv_Thuc_Don.setText(history.getThucDon());
        holder.tv_So_Luong.setText(history.getSoLuongMua()+"");
    }
    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public  class HistoryViewHoler extends RecyclerView.ViewHolder{
        TextView tv_Ma_Don_Hang,tv_Ten,tv_Sdt,tv_Dia_Chi,tv_Thuc_Don,tv_Ngay_Dat_Hang,
        tv_Tong_Tien,tv_Thanh_Toan,tv_So_Luong;
        public HistoryViewHoler(@NonNull View itemView) {
            super(itemView);
            tv_Dia_Chi=itemView.findViewById(R.id.tv_dia_chi);
            tv_Sdt=itemView.findViewById(R.id.tv_sdt);
            tv_Thuc_Don=itemView.findViewById(R.id.tv_thuc_don);
            tv_Ngay_Dat_Hang=itemView.findViewById(R.id.tv_ngay_dat_hang);
            tv_Ma_Don_Hang=itemView.findViewById(R.id.tv_ma_don_hang);
            tv_Tong_Tien=itemView.findViewById(R.id.tv_tong_tien);
            tv_Thanh_Toan=itemView.findViewById(R.id.tv_thanh_toan);
            tv_Ten=itemView.findViewById(R.id.tv_ten);
            tv_So_Luong=itemView.findViewById(R.id.tv_so_luong);

        }
    }

}
