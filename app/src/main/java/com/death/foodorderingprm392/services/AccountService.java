package com.death.foodorderingprm392.services;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.death.foodorderingprm392.CartActivity;
import com.death.foodorderingprm392.LoginActivity;
import com.death.foodorderingprm392.MainActivity;
import com.death.foodorderingprm392.PaymentCompleteActivity;
import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.data.Cart;
import com.death.foodorderingprm392.data.User;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.model.LoginModel;
import com.death.foodorderingprm392.model.RegisterModel;
import com.death.foodorderingprm392.model.ResponseModel;
import com.death.foodorderingprm392.repositories.IAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService<T> extends BaseService{
    private IAccountRepository accountRepository;
    private T model;

    public AccountService(Context context, T model) {
        super(context);
        this.model = model;
        accountRepository = retrofit.create(IAccountRepository.class);
    }

    public void login() {

        if (StaticInstance.getUser() != null) return;

        Call<ResponseModel<User>> call = accountRepository.Login((LoginModel) model);
        call.enqueue(new Callback<ResponseModel<User>>() {
            @Override
            public void onResponse(Call<ResponseModel<User>> call, Response<ResponseModel<User>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("Incorrect email or password");
                    return;
                }

                Toast.makeText(context, "Message: Login successfully", Toast.LENGTH_SHORT).show();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();

                User user = response.body().getResult();
                StaticInstance.setUser(user);
                Intent main = new Intent(context, MainActivity.class);
                context.startActivity(main);

                createNotification(user);

                ((Activity) context).finish();
            }

            @Override
            public void onFailure(Call<ResponseModel<User>> call, Throwable t) {
                displayErrorMessage("Incorrect email or password");
            }
        });
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
    }

    public void register() {
        
        Call<ResponseModel<User>> call = accountRepository.Register((RegisterModel) model);
        call.enqueue(new Callback<ResponseModel<User>>() {
            @Override
            public void onResponse(Call<ResponseModel<User>> call, Response<ResponseModel<User>> response) {
                if (!response.isSuccessful()) {

                    try {
                        if (response.errorBody().string().contains("duplicate")) {
                            displayErrorMessage("Email is registered");
                        } else {
                            displayErrorMessage("Invalid email format");
                        }
                    } catch (IOException e) {
                        displayErrorMessage("An error has occurred");
                    }

                    return;
                }

                Toast.makeText(context, "Message: Register successfully", Toast.LENGTH_SHORT).show();

                Intent login = new Intent(context, LoginActivity.class);
                context.startActivity(login);
                ((Activity) context).finish();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<User>> call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
    }

    public void signOut() {
        
        Call call = accountRepository.SignOut();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
    }

    public void getProfile() {
        
        Call<ResponseModel<User>> call = accountRepository.Profile();
        call.enqueue(new Callback<ResponseModel<User>>() {
            @Override
            public void onResponse(Call<ResponseModel<User>> call, Response<ResponseModel<User>> response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }

                User user = response.body().getResult();

                StaticInstance.getUser().setWalletAmount(user.getWalletAmount());

                AppCompatActivity activity = (AppCompatActivity) context;

                TextView txtEmail = activity.findViewById(R.id.txtProfileEmail);
                TextView txtName = activity.findViewById(R.id.txtProfileName);
                TextView txtPhone = activity.findViewById(R.id.txtProfilePhone);
                TextView txtWalletAmount = activity.findViewById(R.id.txtWalletAmountProfile);

                txtEmail.setText(user.getEmail());
                txtName.setText(user.getName());
                txtPhone.setText(user.getPhone());
                txtWalletAmount.setText(String.format("%,.0f VND", user.getWalletAmount()));
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel<User>> call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });

    }

    public void topUp(double amount) {
        
        Call call = accountRepository.TopUp(amount);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    displayErrorMessage("An error has occurred");
                    return;
                }

                User user = StaticInstance.getUser();
                user.setWalletAmount(user.getWalletAmount() + amount);
                Intent payment = new Intent(context, PaymentCompleteActivity.class);
                payment.putExtra("RETURN_INTENT", "TOP_UP");
                context.startActivity(payment);
                ((AppCompatActivity) context).finish();
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                displayErrorMessage("An error has occurred");
            }
        });
    }

    public void updateTempCart() {
        if (StaticInstance.getCart() == null) return;

        Cart cart = StaticInstance.getCart();

        Call call = accountRepository.UpdateTempCart(cart);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error: An error has occurred", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Error: An error has occurred", Toast.LENGTH_SHORT).show();
            }
        });
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
    }

    public void deleteTempCart() {

        Call call = accountRepository.DeleteTempCart();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error: An error has occurred", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Error: An error has occurred", Toast.LENGTH_SHORT).show();
            }
        });
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
    }

    private void createNotification(User user) {
        if (user.getTempCartMeta() != null && user.getTempCartMeta().length() > 0) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                Cart tempCart = mapper.readValue(user.getTempCartMeta(), Cart.class);
                StaticInstance.setCart(tempCart);
                StaticInstance.getUser().setTempCartMeta(null);
                if (tempCart.getTotalItem() == 0) return;

            } catch (Exception e) {
                return;
            }

            NotificationCompat.Builder builder;
            String channelId = "Death222";
            Intent cart = new Intent(context, CartActivity.class);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, cart,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannel("Death222");
                NotificationChannel channel = new NotificationChannel(channelId, "Food Ordering Notification", NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(context, channelId);
            } else {
                builder = new NotificationCompat.Builder(context);
                builder.setPriority(NotificationManager.IMPORTANCE_LOW);
            }

            builder.setSmallIcon(R.drawable.ic_cart)
                    .setContentTitle("Bạn đang có đơn hàng chưa thanh toán")
                    .setContentText("Click để vào giỏ hàng của bạn !");

            Notification notification = builder
                    .setContentIntent(pendingIntent)
                    .build();

            manager.notify(0, notification);
        }
    }
}
