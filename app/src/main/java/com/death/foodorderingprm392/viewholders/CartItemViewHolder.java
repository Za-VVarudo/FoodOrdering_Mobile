package com.death.foodorderingprm392.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;

public class CartItemViewHolder extends RecyclerView.ViewHolder {
    TextView txtCartItemName, txtCartItemQuantity, txtEditCartItem, txtCartItemTotal;

    public CartItemViewHolder(@NonNull View itemView) {
        super(itemView);

        txtCartItemName = itemView.findViewById(R.id.txtCartItemName);
        txtCartItemQuantity = itemView.findViewById(R.id.txtCartItemQuantity);
        txtEditCartItem = itemView.findViewById(R.id.txtEditCartItem);
        txtCartItemTotal = itemView.findViewById(R.id.txtCartItemTotal);
    }

    public TextView getTxtCartItemName() {
        return txtCartItemName;
    }

    public TextView getTxtCartItemQuantity() {
        return txtCartItemQuantity;
    }

    public TextView getTxtEditCartItem() {
        return txtEditCartItem;
    }

    public TextView getTxtCartItemTotal() {
        return txtCartItemTotal;
    }
}
