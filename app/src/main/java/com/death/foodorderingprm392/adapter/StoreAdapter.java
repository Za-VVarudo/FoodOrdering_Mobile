package com.death.foodorderingprm392.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.StoreDetailActivity;
import com.death.foodorderingprm392.data.Store;
import com.death.foodorderingprm392.viewholders.StoreViewHolder;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {
    private Context context;
    private ArrayList<Store> list;

    public StoreAdapter(Context context, ArrayList<Store> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }


    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lInf = LayoutInflater.from(parent.getContext());

        View view = lInf.inflate(R.layout.store_item, parent, false);

        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        Store store = list.get(position);

        holder.getTxtStoreName().setText(store.getName());
        holder.getTxtStoreAddress().setText(store.getAddress());

        Glide.with(context).load(store.getImageSrc()).into(holder.getImageStoreImage());

        holder.getView().setOnClickListener(view -> {
            Intent storeDetail = new Intent(context, StoreDetailActivity.class);
            storeDetail.putExtra("STORE_ID", store.getId());
            context.startActivity(storeDetail);
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

    public ArrayList<Store> getList() {
        return list;
    }

    public void setList(ArrayList<Store> list) {
        this.list = list;
    }
}
