package com.mdd.foodies.newWork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdd.foodies.R;
import com.mdd.foodies.newWork.modal.ShopsModel;

import java.util.ArrayList;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.viewHolderr> {

    ArrayList<ShopsModel> list;

    public ShopsAdapter(ArrayList<ShopsModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ShopsAdapter.viewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_shop,parent,false);
        return new viewHolderr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopsAdapter.viewHolderr holder, int position) {
        holder.ShopPic.setImageBitmap(list.get(position).getImg());
        holder.ShopName.setText(list.get(position).getTextView());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolderr extends RecyclerView.ViewHolder{

        ImageView ShopPic;
        TextView ShopName;
        public viewHolderr(@NonNull View itemView) {
            super(itemView);

            ShopPic = itemView.findViewById(R.id.shop_icon);
            ShopName = itemView.findViewById(R.id.shop_name);

        }
    }

}
