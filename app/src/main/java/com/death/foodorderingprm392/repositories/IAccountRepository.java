package com.death.foodorderingprm392.repositories;

import com.death.foodorderingprm392.data.Cart;
import com.death.foodorderingprm392.data.User;
import com.death.foodorderingprm392.model.LoginModel;
import com.death.foodorderingprm392.model.RegisterModel;
import com.death.foodorderingprm392.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IAccountRepository {

    static final String USER_ENDPOINT = "users/";

    static final String LOGIN_ENDPOINT = "login/";

    static final String REGISTER_ENDPOINT = "register/";

    static final String SIGN_OUT_ENDPOINT = "sign-out/";

    static final String PROFILE_ENDPOINT = "profile/";

    static final String TOP_UP_ENDPOINT = "top-up/";

    static final String TEMP_CART_ENDPOINT = "temp-data/";

    @Headers("Content-Type: application/json")
    @POST(USER_ENDPOINT + LOGIN_ENDPOINT)
    Call<ResponseModel<User>> Login(@Body LoginModel model);

    @Headers("Content-Type: application/json")
    @POST(USER_ENDPOINT + REGISTER_ENDPOINT)
    Call<ResponseModel<User>> Register(@Body RegisterModel model);

    @GET(USER_ENDPOINT + SIGN_OUT_ENDPOINT)
    Call<Void> SignOut();

    @GET(USER_ENDPOINT + PROFILE_ENDPOINT)
    Call<ResponseModel<User>> Profile();

    @PUT(USER_ENDPOINT + TOP_UP_ENDPOINT)
    Call<Void> TopUp(@Body double amount);

    @Headers("Content-Type: application/json")
    @PATCH(USER_ENDPOINT + TEMP_CART_ENDPOINT)
    Call<Void> UpdateTempCart(@Body Cart cart);

    @DELETE(USER_ENDPOINT + TEMP_CART_ENDPOINT)
    Call<Void> DeleteTempCart();
}
