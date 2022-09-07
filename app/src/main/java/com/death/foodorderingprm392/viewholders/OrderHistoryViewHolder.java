package com.death.foodorderingprm392.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

    TextView txtHistoryOrderId, txtHistoryPurchaseDate, txtHistoryOrderPrice;

    public OrderHistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        txtHistoryOrderId = itemView.findViewById(R.id.txtHistoryOrderId);
        txtHistoryPurchaseDate = itemView.findViewById(R.id.txtHistoryPurchaseDate);
        txtHistoryOrderPrice = itemView.findViewById(R.id.txtHistoryOrderPrice);
    }

    public TextView getTxtHistoryOrderId() {
        return txtHistoryOrderId;
    }

    public TextView getTxtHistoryPurchaseDate() {
        return txtHistoryPurchaseDate;
    }

    public TextView getTxtHistoryOrderPrice() {
        return txtHistoryOrderPrice;
    }
}
