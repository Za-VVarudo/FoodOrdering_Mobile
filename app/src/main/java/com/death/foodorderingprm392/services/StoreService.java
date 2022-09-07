package com.death.foodorderingprm392.services;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.StoreLocationActivity;
import com.death.foodorderingprm392.adapter.FoodAdapter;
import com.death.foodorderingprm392.adapter.StoreAdapter;
import com.death.foodorderingprm392.data.Store;
import com.death.foodorderingprm392.helpers.APIClientHelper;
import com.death.foodorderingprm392.model.ResponseModel;
import com.death.foodorderingprm392.repositories.IStoreRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreService extends BaseService{
    private IStoreRepository storeRepository;
    private StoreAdapter storeAdapter;
    private FoodAdapter foodAdapter;

    public StoreService(StoreAdapter adapter) {
        super(adapter.getContext());
        this.storeAdapter = adapter;
        storeRepository = APIClientHelper.getAPIClient().create(IStoreRepository.class);
    }

    public StoreService(FoodAdapter adapter) {
        super(adapter.getContext());
        this.foodAdapter = adapter;
        storeRepository = APIClientHelper.getAPIClient().create(IStoreRepository.class);
    }

    public void getAllStore() {
        
        Call<ResponseModel<ArrayList<Store>>> call = storeRepository.getAllStore();
        call.enqueue(new Callback<ResponseModel<ArrayList<Store>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<Store>>> call, Response<ResponseModel<ArrayList<Store>>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }

                storeAdapter.setList(response.body().getResult());
                storeAdapter.notifyDataSetChanged();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<Store>>> call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
    }

    public void getStoreById(long storeId) {

        Call<ResponseModel<Store>> call = storeRepository.getStoreById(storeId);
        call.enqueue(new Callback<ResponseModel<Store>>() {
            @Override
            public void onResponse(Call<ResponseModel<Store>> call, Response<ResponseModel<Store>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }

                Store store = response.body().getResult();
                AppCompatActivity activity = (AppCompatActivity) context;

                TextView txtStoreName = activity.findViewById(R.id.txtStoreName);
                TextView txtStoreAddress = activity.findViewById(R.id.txtStoreAddress);
                LinearLayout layoutAddress = activity.findViewById(R.id.layoutStoreLocation);
                ImageView imgStoreBackground = activity.findViewById(R.id.imgStoreBackground);

                Glide.with(context).load(store.getImageSrc()).into(imgStoreBackground);


                txtStoreName.setText(store.getName());
                txtStoreAddress.setText(store.getAddress());

                layoutAddress.setOnClickListener(view -> {
                    Intent location = new Intent(context, StoreLocationActivity.class);
                    location.putExtra("STORE", store);
                    context.startActivity(location);
                });

                foodAdapter.setList(store.getFoodStores());
                foodAdapter.notifyDataSetChanged();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<Store>> call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
    }
}
