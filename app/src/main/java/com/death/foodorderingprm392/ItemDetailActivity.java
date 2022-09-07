package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.death.foodorderingprm392.data.CartItem;
import com.death.foodorderingprm392.data.FoodStore;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class ItemDetailActivity extends AppCompatActivity {

    ImageView btnBackItemDetail;
    ImageView imgItemDetail;
    TextView txtItemName, txtItemPrice, txtItemType;
    ImageView imgItemDetailMinus, imgItemDetailPlus;
    TextView txtItemQuantity;
    Button btnItemDetailUpdate;

    CartItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        setBtnBackItemDetail();
        itemDetailInfo();
        setQuantityButton();
        setBtnItemDetailUpdate();
    }

    private void setBtnBackItemDetail() {
        btnBackItemDetail = findViewById(R.id.btnBackItemDetail);
        btnBackItemDetail.setOnClickListener(view -> {
            Intent cart = new Intent(this, CartActivity.class);
            startActivity(cart);
            this.finish();
        });
    }

    private void itemDetailInfo() {

        item = (CartItem) getIntent().getSerializableExtra("CART_ITEM_DETAIL");

        FoodStore fs = item.getFoodStore();

        imgItemDetail = findViewById(R.id.imgItemDetail);
        txtItemName = findViewById(R.id.txtItemName);
        txtItemPrice = findViewById(R.id.txtItemPrice);
        txtItemType = findViewById(R.id.txtItemType);
        txtItemQuantity = findViewById(R.id.txtItemQuantity);

        Glide.with(this).load(fs.getFood().getImageSrc()).into(imgItemDetail);
        txtItemName.setText(fs.getFood().getName());
        txtItemType.setText(String.format("Loại: %s", fs.getFood().getFoodTypeName()));
        txtItemPrice.setText(String.format("%,.0f VND", fs.getPrice()));
        txtItemQuantity.setText(item.getQuantity() + "");
    }

    private void setQuantityButton() {
        imgItemDetailMinus = findViewById(R.id.imgItemDetailMinus);
        imgItemDetailPlus = findViewById(R.id.imgItemDetailPlus);

        imgItemDetailMinus.setOnClickListener(view -> {
            int quantity = item.getQuantity();
            if (quantity > 0) {
                item.setQuantity(--quantity);
                txtItemQuantity.setText(quantity + "");
            }
        });

        imgItemDetailPlus.setOnClickListener(view -> {
            int quantity = item.getQuantity();
            item.setQuantity(++quantity);
            txtItemQuantity.setText(quantity + "");
        });
    }

    private void setBtnItemDetailUpdate() {
        btnItemDetailUpdate = findViewById(R.id.btnItemDetailUpdate);
        btnItemDetailUpdate.setOnClickListener(view -> {
            StaticInstance.getCart().updateQuantity(item.getFoodStoreId(), item.getQuantity());

            Toast.makeText(this, "Message: Update successfully", Toast.LENGTH_SHORT).show();
        });
    }
}