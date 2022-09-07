package com.death.foodorderingprm392.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.SearchActivity;
import com.death.foodorderingprm392.data.FoodType;
import com.death.foodorderingprm392.model.FoodStoreFilterModel;
import com.death.foodorderingprm392.viewholders.FoodTypeViewHolder;

import java.util.ArrayList;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeViewHolder> {
    private Context context;

    private ArrayList<FoodType> list;

    public FoodTypeAdapter(Context context, ArrayList<FoodType> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }


    @NonNull
    @Override
    public FoodTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lInf = LayoutInflater.from(parent.getContext());

        View view = lInf.inflate(R.layout.food_type_item, parent, false);

        return  new FoodTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTypeViewHolder holder, int position) {

        FoodType foodType = list.get(position);

        holder.getTxtName().setText(foodType.getName());

        Glide.with(context).load(foodType.getImgSrc()).into(holder.getImgFoodType());

        holder.getImgFoodType().setOnClickListener(view -> {
            Intent search = new Intent(context, SearchActivity.class);
            search.putExtra("FOOD_STORE_FILTER", new FoodStoreFilterModel(null, foodType.getId()));
            context.startActivity(search);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<FoodType> getList() {
        return list;
    }

    public void setList(ArrayList<FoodType> list) {
        this.list = list;
    }
}
