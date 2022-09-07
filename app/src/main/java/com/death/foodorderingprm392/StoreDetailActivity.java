package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.death.foodorderingprm392.adapter.FoodAdapter;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.services.StoreService;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class StoreDetailActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtAddress;
    long storeId;
    RecyclerView foodView;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        storeId = getIntent().getLongExtra("STORE_ID", -1);
        setBtnBack();
        setStoreData();
    }

    private void setStoreData() {
        FoodAdapter foodAdapter = new FoodAdapter(this, null);

        foodView = findViewById(R.id.recyclerViewStoreFood);
        foodView.setAdapter(foodAdapter);
        foodView.setLayoutManager(new LinearLayoutManager(this));

        StoreService service = new StoreService(foodAdapter);
        service.getStoreById(storeId);
    }

    private void setBtnBack() {
        btnBack = findViewById(R.id.btnBackStore);

        btnBack.setOnClickListener(view -> {
            Intent main = StaticInstance.getMainActivityIntent();
            startActivity(main);
            this.finish();
        });

    }
}