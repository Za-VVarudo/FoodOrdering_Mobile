package com.death.foodorderingprm392.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;

public class StoreViewHolder extends RecyclerView.ViewHolder {
    TextView txtStoreName;
    TextView txtStoreAddress;
    ImageView imageStoreImage;
    View view;

    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);

        this.view = itemView;
        txtStoreName = itemView.findViewById(R.id.txtStoreItemName);
        txtStoreAddress = itemView.findViewById(R.id.txtStoreItemAddress);
        imageStoreImage = itemView.findViewById(R.id.imageStoreItemImage);

    }

    public ImageView getImageStoreImage() {
        return imageStoreImage;
    }

    public TextView getTxtStoreName() {
        return txtStoreName;
    }

    public TextView getTxtStoreAddress() {
        return txtStoreAddress;
    }

    public View getView() {
        return view;
    }
}
