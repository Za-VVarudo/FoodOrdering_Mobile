package com.death.foodorderingprm392.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;

public class FoodViewHolder extends RecyclerView.ViewHolder {
    TextView txtName, txtType, txtPrice, txtChoose;
    ImageView imageView;


    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txtStoreFoodName);
        txtType = itemView.findViewById(R.id.txtStoreFoodType);
        txtPrice = itemView.findViewById(R.id.txtStoreFoodPrice);
        imageView = itemView.findViewById(R.id.imgStoreFood);
        txtChoose = itemView.findViewById(R.id.txtStoreChooseFood);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public TextView getTxtType() {
        return txtType;
    }

    public TextView getTxtPrice() {
        return txtPrice;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTxtChoose() {
        return txtChoose;
    }
}
