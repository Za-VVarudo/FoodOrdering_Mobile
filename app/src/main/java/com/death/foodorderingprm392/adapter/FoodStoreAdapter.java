package com.death.foodorderingprm392.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.StoreDetailActivity;
import com.death.foodorderingprm392.data.FoodStore;
import com.death.foodorderingprm392.viewholders.FoodStoreViewHolder;

import java.util.ArrayList;

public class FoodStoreAdapter extends RecyclerView.Adapter<FoodStoreViewHolder> {
    private Context context;
    private ArrayList<FoodStore> list;

    public FoodStoreAdapter(Context context, ArrayList<FoodStore> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<FoodStore> getList() {
        return list;
    }

    public void setList(ArrayList<FoodStore> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lInf = LayoutInflater.from(parent.getContext());

        View view = lInf.inflate(R.layout.food_store_item, parent, false);

        return new FoodStoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodStoreViewHolder holder, int position) {

        FoodStore fs = list.get(position);

        holder.getTxtFoodName().setText(fs.getFood().getName());
        holder.getTxtPrice().setText(String.format("Giá: %,.0f VND", fs.getPrice()));
        holder.getTxtStoreName().setText(fs.getStore().getName());

        Glide.with(context).load(fs.getFood().getImageSrc()).into(holder.getImgFoodImg());

        holder.itemView.setOnClickListener(view -> {
            Intent storeDetail = new Intent(context, StoreDetailActivity.class);
            storeDetail.putExtra("STORE_ID", fs.getStoreId());
            context.startActivity(storeDetail);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
