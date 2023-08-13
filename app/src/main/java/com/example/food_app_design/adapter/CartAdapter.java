package com.example.food_app_design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_design.R;
import com.example.food_app_design.modal.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<Food>list;

    public IClick clickItem;
    int amount;
    public CartAdapter(IClick clickItem) {
        this.clickItem = clickItem;
    }

    public  interface IClick{
        void delete(Food food,int position);
    }
    public  void  setData(List<Food>getList){
        this.list=getList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Food food=list.get(position);
        if(food==null){
            return;
        }
        int discount=food.getDiscount();
        if(discount==0){
            Picasso.get().load(food.getImage()).into(holder.img_Food);
            holder.tv_Amount.setText(food.getAmountBuy()+"");
            holder.tv_Price.setText(food.getPrice()*food.getAmountBuy()+"");
            holder.tv_Title.setText(food.getTitle());
        }else {
            Picasso.get().load(food.getImage()).into(holder.img_Food);
            holder.tv_Amount.setText(food.getAmountBuy()+"");
            holder.tv_Price.setText(food.totalMoney()+"");
            holder.tv_Title.setText(food.getTitle());
        }
        amount=food.getAmountBuy();
        holder.tv_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.delete(food,holder.getAdapterPosition());

            }
        });



    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return  list.size();
        }
        return 0;
    }

    public  class  CartViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Title,tv_Price,tv_Amount,tv_Xoa;
        ImageView img_Food;
        ImageView img_Plus,img_Minus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Price=itemView.findViewById(R.id.tv_price_food);
            tv_Title=itemView.findViewById(R.id.tv_title_food);
            tv_Amount=itemView.findViewById(R.id.tv_amount);
            img_Food=itemView.findViewById(R.id.img_food_detail);
            tv_Xoa=itemView.findViewById(R.id.tv_xoa);
            img_Minus=itemView.findViewById(R.id.img_minus);
            img_Plus=itemView.findViewById(R.id.img_plus);
        }
    }
}
