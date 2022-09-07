package com.death.foodorderingprm392;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.services.AccountService;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class ProfileActivity extends AppCompatActivity {

    ImageView btnBack;
    Button btnLogout;
    TextView txtTopUp;
    TextView txtHistory;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        displayUserInfo();
        displayBackButton();
        displayLogoutButton();
        setTxtTopUp();
        setTxtHistory();
    }

    private void displayBackButton() {
        btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(view -> {
            Intent main = StaticInstance.getMainActivityIntent();
            startActivity(main);
            this.finish();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayLogoutButton() {
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> {

            AccountService accountService = new AccountService(this, null);

            if (StaticInstance.getCart().getTotalItem() > 0)  {
                accountService.updateTempCart();
            } else {
                accountService.deleteTempCart();
            }

            StaticInstance.removeUser();
            StaticInstance.removeCart();
            StaticInstance.removeMainActivityIntent();

            accountService.signOut();

            NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.deleteNotificationChannel("Death222");

            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            this.finish();
        });
    }

    private void displayUserInfo() {
        AccountService accountService = new AccountService(this, null);
        accountService.getProfile();
    }

    private void setTxtTopUp() {
        txtTopUp = findViewById(R.id.txtTopUp);

        txtTopUp.setOnClickListener(view -> {
            Intent topup = new Intent(this, TopUpActivity.class);
            startActivity(topup);
            this.finish();
        });
    }

    private void setTxtHistory() {
        txtHistory = findViewById(R.id.txtPaymentHistory);
        txtHistory.setOnClickListener(view -> {
            Intent history = new Intent(this, OrderHistoryActivity.class);
            startActivity(history);
        });
    }
}