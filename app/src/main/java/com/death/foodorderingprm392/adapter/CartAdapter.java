package com.death.foodorderingprm392.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.ItemDetailActivity;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.data.CartItem;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.viewholders.CartItemViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    private Context context;
    private ArrayList<CartItem> list;

    public CartAdapter(Context context, ArrayList<CartItem> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lInf = LayoutInflater.from(parent.getContext());

        View view = lInf.inflate(R.layout.cart_item, parent, false);

        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {

        CartItem cartItem = list.get(position);

        holder.getTxtCartItemName().setText(cartItem.getFoodStore().getFood().getName());
        holder.getTxtCartItemQuantity().setText(cartItem.getQuantity() + "x");
        holder.getTxtCartItemTotal().setText(String.format("%,.0f VND", cartItem.getTotal()));

        holder.getTxtEditCartItem().setOnClickListener(view -> {
            Intent itemDetail = new Intent(context, ItemDetailActivity.class);
            itemDetail.putExtra("CART_ITEM_DETAIL", cartItem);
            context.startActivity(itemDetail);
            ((AppCompatActivity) context).finish();
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

    public ArrayList<CartItem> getList() {
        return list;
    }

    public void setList(ArrayList<CartItem> list) {
        this.list = list;
    }
}
