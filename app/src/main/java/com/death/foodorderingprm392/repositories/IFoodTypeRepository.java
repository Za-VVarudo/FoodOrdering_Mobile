package com.death.foodorderingprm392.repositories;

import com.death.foodorderingprm392.data.FoodType;
import com.death.foodorderingprm392.model.ResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IFoodTypeRepository {
    static final String FODD_TYPE_ENDPOINT = "food-types/";

    @GET(FODD_TYPE_ENDPOINT)
    Call<ResponseModel<ArrayList<FoodType>>> getAllFoodType();
}
