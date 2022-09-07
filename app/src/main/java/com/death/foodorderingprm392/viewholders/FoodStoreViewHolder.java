package com.death.foodorderingprm392.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;

public class FoodStoreViewHolder extends RecyclerView.ViewHolder {

    TextView txtStoreName, txtFoodName, txtPrice;
    ImageView imgFoodImg;
    public FoodStoreViewHolder(@NonNull View itemView) {
        super(itemView);
        txtFoodName = itemView.findViewById(R.id.txtFoodStoreFoodName);
        txtStoreName = itemView.findViewById(R.id.txtFoodStoreStoreName);
        txtPrice = itemView.findViewById(R.id.txtFoodStorePrice);
        imgFoodImg = itemView.findViewById(R.id.imgFoodStoreFoodImg);
    }

    public TextView getTxtStoreName() {
        return txtStoreName;
    }

    public TextView getTxtFoodName() {
        return txtFoodName;
    }

    public TextView getTxtPrice() {
        return txtPrice;
    }

    public ImageView getImgFoodImg() {
        return imgFoodImg;
    }
}
