package com.death.foodorderingprm392.repositories;

import com.death.foodorderingprm392.data.Order;
import com.death.foodorderingprm392.model.ResponseModel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IOrderRepository {
    static String ORDER_ENDPOINT = "orders/";
    static String ORDER_HISTORY = "history/";

    @GET(ORDER_ENDPOINT + "{id}")
    Call<ResponseModel<Order>> getOrderById(@Path("id") String id);

    @POST(ORDER_ENDPOINT)
    Call<Void> createOrder(@Body Hashtable<String, Integer> detailList);

    @GET(ORDER_ENDPOINT + ORDER_HISTORY)
    Call<ResponseModel<ArrayList<Order>>> getOrderHistory();
}
