package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.death.foodorderingprm392.data.Store;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class StoreLocationActivity extends AppCompatActivity {

    Store store;
    ImageView btnBack;
    TextView txtLocationStoreName, txtLocationStoreAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_location);

        setBtnBack();
        loadMap();
    }

    private void setBtnBack() {
        btnBack = findViewById(R.id.btnBackLocation);
        btnBack.setOnClickListener(view -> {
            this.finish();
        });
    }

    private void loadMap() {
        store = (Store) getIntent().getSerializableExtra("STORE");

        txtLocationStoreName = findViewById(R.id.txtLocationStoreName);
        txtLocationStoreAddress = findViewById(R.id.txtLocationStoreAddress);

        txtLocationStoreName.setText(store.getName());
        txtLocationStoreAddress.setText(store.getAddress());

        Fragment fragment = new MapsFragment(store, this);

        // Open fragment
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frameLayoutMap,fragment)
                .commit();
    }
}