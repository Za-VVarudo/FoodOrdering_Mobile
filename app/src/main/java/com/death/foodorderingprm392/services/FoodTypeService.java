package com.death.foodorderingprm392.services;

import com.death.foodorderingprm392.adapter.FoodTypeAdapter;
import com.death.foodorderingprm392.data.FoodType;
import com.death.foodorderingprm392.model.ResponseModel;
import com.death.foodorderingprm392.repositories.IFoodTypeRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodTypeService extends BaseService{
    private IFoodTypeRepository foodTypeRepository;
    private FoodTypeAdapter adapter;

    public FoodTypeService(FoodTypeAdapter adapter) {
        super(adapter.getContext());
        this.adapter = adapter;
        foodTypeRepository = retrofit.create(IFoodTypeRepository.class);
    }

    public void getAllFoodType() {
        
        Call<ResponseModel<ArrayList<FoodType>>> call = foodTypeRepository.getAllFoodType();
        call.enqueue(new Callback<ResponseModel<ArrayList<FoodType>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<FoodType>>> call, Response<ResponseModel<ArrayList<FoodType>>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occur");
                    return;
                }

                adapter.setList(response.body().getResult());
                adapter.notifyDataSetChanged();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<FoodType>>> call, Throwable t) {
                displayErrorMessage("An error has occur");
            }
        });
    }
}
