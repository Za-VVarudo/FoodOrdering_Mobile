package com.death.foodorderingprm392.services;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;

import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.helpers.APIClientHelper;

import retrofit2.Retrofit;

public class BaseService {
    protected Retrofit retrofit;
    protected Context context;
    AppCompatDialog loadingDialog;

    protected BaseService(Context context) {
        this.context = context;

        loadingDialog = new AppCompatDialog(context);
        loadingDialog.setContentView(R.layout.progress_dialog_loading);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loadingDialog.setCancelable(false);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();

        retrofit = APIClientHelper.getAPIClient();
    }
    protected void displayErrorMessage(String message) {

        Toast.makeText(context, "Error: " + message, Toast.LENGTH_SHORT).show();
        loadingDialog.hide();
    }
}
