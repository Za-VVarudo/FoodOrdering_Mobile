package com.death.foodorderingprm392;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.death.foodorderingprm392.adapter.FoodTypeAdapter;
import com.death.foodorderingprm392.adapter.StoreAdapter;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.model.FoodStoreFilterModel;
import com.death.foodorderingprm392.services.AccountService;
import com.death.foodorderingprm392.services.FoodTypeService;
import com.death.foodorderingprm392.services.StoreService;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class MainActivity extends AppCompatActivity {

    ImageView imgProfile;
    ImageView imgCart;
    RecyclerView foodTypeView;
    RecyclerView storeView;
    FoodTypeAdapter foodTypeAdapter;
    StoreAdapter storeAdapter;
    EditText txtMainSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StaticInstance.setMainActivityIntent(getIntent());

        setTxtMainSearch();
        setImgCart();
        displayFoodTypeView();
        displayStore();
        displayProfile();

    }

    private void setTxtMainSearch() {
        txtMainSearch = findViewById(R.id.txtMainSearch);
        txtMainSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {

            String foodName = textView.getText().toString();

            if (actionId == EditorInfo.IME_ACTION_DONE && foodName.length() > 0) {

                Intent search = new Intent(this, SearchActivity.class);
                search.putExtra("FOOD_STORE_FILTER", new FoodStoreFilterModel(foodName, null));
                startActivity(search);
                return true;
            }
            return false;
        });
    }

    private void displayFoodTypeView() {
        foodTypeView = findViewById(R.id.recyclerViewFoodType);
        foodTypeAdapter = new FoodTypeAdapter(this, null);
        foodTypeView.setAdapter(foodTypeAdapter);
        foodTypeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FoodTypeService service = new FoodTypeService(foodTypeAdapter);
        service.getAllFoodType();
    }

    private void displayStore() {
        storeView = findViewById(R.id.recyclerViewStore);
        storeAdapter = new StoreAdapter(this, null);
        storeView.setAdapter(storeAdapter);
        storeView.setLayoutManager(new LinearLayoutManager(this));
        StoreService service = new StoreService(storeAdapter);
        service.getAllStore();
    }

    private void displayProfile() {
        imgProfile = findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(view -> {
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        });
    }

    private void setImgCart() {
        imgCart = findViewById(R.id.imgCart);
        imgCart.setOnClickListener(view -> {
            Intent cart = new Intent(this, CartActivity.class);
            startActivity(cart);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "On destroy main", Toast.LENGTH_SHORT).show();
        AccountService service = new AccountService(this, null);
        if (StaticInstance.getCart().getTotalItem() > 0)  {
            service.updateTempCart();
        } else {
            service.deleteTempCart();
        }
    }
}