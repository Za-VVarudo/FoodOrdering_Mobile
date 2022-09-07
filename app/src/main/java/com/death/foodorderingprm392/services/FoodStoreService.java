package com.death.foodorderingprm392.services;

import android.content.Context;

import com.death.foodorderingprm392.adapter.FoodStoreAdapter;
import com.death.foodorderingprm392.data.FoodStore;
import com.death.foodorderingprm392.helpers.APIClientHelper;
import com.death.foodorderingprm392.model.FoodStoreFilterModel;
import com.death.foodorderingprm392.model.ResponseModel;
import com.death.foodorderingprm392.repositories.IFoodStoreRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodStoreService extends BaseService{
    private FoodStoreAdapter foodStoreAdapter;
    private IFoodStoreRepository foodStoreRepository;

    public FoodStoreService(FoodStoreAdapter foodStoreAdapter) {
        super(foodStoreAdapter.getContext());
        this.foodStoreAdapter = foodStoreAdapter;
        foodStoreRepository = APIClientHelper.getAPIClient().create(IFoodStoreRepository.class);
    }

    public void getAllFoodStore(FoodStoreFilterModel model) {
        Call<ResponseModel<ArrayList<FoodStore>>> call  = foodStoreRepository.GetAllFoodStore(model.getFoodName(), model.getFoodTypeId());
        call.enqueue(new Callback<ResponseModel<ArrayList<FoodStore>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<FoodStore>>> call, Response<ResponseModel<ArrayList<FoodStore>>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }

                foodStoreAdapter.setList(response.body().getResult());
                foodStoreAdapter.notifyDataSetChanged();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<FoodStore>>> call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
    }
}
