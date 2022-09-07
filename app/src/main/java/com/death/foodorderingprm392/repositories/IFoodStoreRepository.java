package com.death.foodorderingprm392.repositories;

import com.death.foodorderingprm392.data.FoodStore;
import com.death.foodorderingprm392.model.FoodStoreFilterModel;
import com.death.foodorderingprm392.model.ResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFoodStoreRepository {
    static String FOOD_STORE_ENDPOINT = "food-stores/";

    @GET(FOOD_STORE_ENDPOINT)
    Call<ResponseModel<ArrayList<FoodStore>>> GetAllFoodStore(@Query("foodName") String foodName, @Query("foodTypeId") Integer foodTypeId);
}
