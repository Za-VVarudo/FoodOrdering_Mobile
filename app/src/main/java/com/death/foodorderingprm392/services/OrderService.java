package com.death.foodorderingprm392.services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.death.foodorderingprm392.PaymentCompleteActivity;
import com.death.foodorderingprm392.adapter.OrderHistoryAdapter;
import com.death.foodorderingprm392.data.Order;
import com.death.foodorderingprm392.data.User;
import com.death.foodorderingprm392.helpers.APIClientHelper;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.model.OrderCreateModel;
import com.death.foodorderingprm392.model.ResponseModel;
import com.death.foodorderingprm392.repositories.IOrderRepository;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderService extends BaseService{
    private IOrderRepository orderRepository = APIClientHelper.getAPIClient().create(IOrderRepository.class);
    OrderCreateModel createModel;
    private OrderHistoryAdapter orderHistoryAdapter;

    public OrderService(Context context, OrderCreateModel createModel) {
        super(context);
        this.createModel = createModel;
    }

    public OrderService(OrderHistoryAdapter orderHistoryAdapter) {
        super(orderHistoryAdapter.getContext());
        this.orderHistoryAdapter = orderHistoryAdapter;
    }

    public void createOrder() {
        
        Call call = orderRepository.createOrder(createModel.getOrderDetailList());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("Your wallet amount is insufficient");
                    return;
                }

                User user = StaticInstance.getUser();
                StaticInstance.removeCart();
                user.setWalletAmount(user.getWalletAmount() - createModel.getTotal());
                Intent payment = new Intent(context, PaymentCompleteActivity.class);
                context.startActivity(payment);
                ((AppCompatActivity)context).finish();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
    }

    public void getOrderHistory() {
        Call<ResponseModel<ArrayList<Order>>> call = orderRepository.getOrderHistory();
        call.enqueue(new Callback<ResponseModel<ArrayList<Order>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<Order>>> call, Response<ResponseModel<ArrayList<Order>>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }

                orderHistoryAdapter.setList(response.body().getResult());
                orderHistoryAdapter.notifyDataSetChanged();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<Order>>> call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
    }
}
