package com.death.foodorderingprm392.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.data.Cart;
import com.death.foodorderingprm392.data.CartItem;
import com.death.foodorderingprm392.data.FoodStore;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.viewholders.FoodViewHolder;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    Context context;
    ArrayList<FoodStore> list;

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

    public FoodAdapter(Context context, ArrayList<FoodStore> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lInf = LayoutInflater.from(parent.getContext());
        View view = lInf.inflate(R.layout.food_item, parent, false);

        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodStore fs = list.get(position);

        holder.getTxtName().setText(fs.getFood().getName());
        holder.getTxtType().setText("Loại: " + fs.getFood().getFoodTypeName());
        holder.getTxtPrice().setText(String.format("Giá: %,.0f VND", fs.getPrice()));

        Glide.with(context).load(fs.getFood().getImageSrc()).into(holder.getImageView());

        holder.getTxtChoose().setOnClickListener(view -> {

            Cart cart = StaticInstance.getCart();

            CartItem item = new CartItem(fs);

            if (cart.isSameStore(item)) {

                cart.addToCart(item);
                Toast.makeText(context, "Message: Add item successfully ! Total item: " + cart.getTotalItem(), Toast.LENGTH_SHORT).show();

            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn đã có đơn hàng ở quán khác, có muốn tạo mới đơn hàng không ?");
                builder.setPositiveButton("Đồng ý", (dialogInterface, i) -> {
                    StaticInstance.removeCart();

                    Cart newCart = StaticInstance.getCart();
                    newCart.addToCart(item);

                    Toast.makeText(context, "Message: Add item successfully ! Total item: " + newCart.getTotalItem(), Toast.LENGTH_SHORT).show();
                });

                builder.setNegativeButton("Hủy", (dialogInterface, i) -> {

                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
