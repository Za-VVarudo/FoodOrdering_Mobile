package com.death.foodorderingprm392.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;

public class FoodTypeViewHolder extends RecyclerView.ViewHolder {

    private TextView txtName;
    private ImageView imgFoodType;

    public FoodTypeViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txtFoodTypeName);
        imgFoodType = itemView.findViewById(R.id.imgFoodType);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public ImageView getImgFoodType() {
        return imgFoodType;
    }
}
