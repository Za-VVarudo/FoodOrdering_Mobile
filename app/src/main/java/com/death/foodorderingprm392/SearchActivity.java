package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.death.foodorderingprm392.adapter.FoodStoreAdapter;
import com.death.foodorderingprm392.model.FoodStoreFilterModel;
import com.death.foodorderingprm392.services.FoodStoreService;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class SearchActivity extends AppCompatActivity {

    ImageView imgProfile;
    ImageView imgCart;
    EditText txtSearch;
    RecyclerView recyclerViewFoodStore;
    FoodStoreAdapter adapter;
    FoodStoreFilterModel model;
    TextView txtAppTitleSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        model = (FoodStoreFilterModel) getIntent().getSerializableExtra("FOOD_STORE_FILTER");

        adapter = new FoodStoreAdapter(this, null);

        setImgCart();
        setImgProfile();
        setTxtAppTitleSearch();
        setTxtSearch();
        setRecyclerViewFoodStore();

    }

    private void setImgCart() {
        imgCart = findViewById(R.id.imgSearchCart);
        imgCart.setOnClickListener(view -> {
            Intent cart = new Intent(this, CartActivity.class);
            startActivity(cart);
        });
    }

    private void setImgProfile() {
        imgProfile = findViewById(R.id.imgSearchProfile);
        imgProfile.setOnClickListener(view -> {
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        });
    }

    private void setRecyclerViewFoodStore() {
        recyclerViewFoodStore = findViewById(R.id.recyclerViewFoodStoreSearch);
        recyclerViewFoodStore.setAdapter(adapter);
        recyclerViewFoodStore.setLayoutManager(new LinearLayoutManager(this));

        if (model.getFoodName() != null) {
            txtSearch.setText(model.getFoodName());
        }

        FoodStoreService service = new FoodStoreService(adapter);
        service.getAllFoodStore(model);
    }

    private void setTxtAppTitleSearch() {
        txtAppTitleSearch = findViewById(R.id.txtAppTitleSearch);
        txtAppTitleSearch.setOnClickListener(view -> {
            this.finish();
        });
    }

    private void setTxtSearch() {
        txtSearch = findViewById(R.id.txtSearch);
        txtSearch.setOnEditorActionListener((textView, actionId, keyEvent) ->  {

            String foodName = textView.getText().toString();

            if (actionId == EditorInfo.IME_ACTION_DONE && foodName.length() > 0) {
                FoodStoreService service = new FoodStoreService(adapter);
                service.getAllFoodStore(new FoodStoreFilterModel(foodName, null));
                return true;
            }

            return false;
        });
    }
}