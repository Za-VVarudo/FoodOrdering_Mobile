package com.death.foodorderingprm392.repositories;

import com.death.foodorderingprm392.data.Store;
import com.death.foodorderingprm392.model.ResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IStoreRepository {
    static final String STORE_ENDPOINT = "stores/";

    @GET(STORE_ENDPOINT)
    Call<ResponseModel<ArrayList<Store>>> getAllStore();

    @GET(STORE_ENDPOINT + "{id}")
    Call<ResponseModel<Store>> getStoreById(@Path("id") long id);
}
