package com.death.foodorderingprm392;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.services.AccountService;
import com.death.foodorderingprm392.utils.AppBarUtil;
import com.google.android.material.textfield.TextInputEditText;

public class TopUpActivity extends AppCompatActivity {

    ImageView btnBack;
    TextView txtTopUpCurrentAmount;
    TextInputEditText txtTopUpAmount;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        setBtnBack();
        setTxtTopUpCurrentAmount();
        setBtnConfirm();
    }

    private void setBtnBack() {
        btnBack = findViewById(R.id.btnBackTopUp);
        btnBack.setOnClickListener(view -> {
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
            this.finish();
        });
    }

    private void setTxtTopUpCurrentAmount() {
        txtTopUpCurrentAmount = findViewById(R.id.txtTopUpCurrentAmount);
        txtTopUpCurrentAmount.setText(String.format("%,.0f VND", StaticInstance.getUser().getWalletAmount()));
    }

    private void setBtnConfirm() {
        txtTopUpAmount = findViewById(R.id.txtTopUpAmount);

        btnConfirm = findViewById(R.id.btnTopUpConfirm);
        btnConfirm.setOnClickListener(view -> {
            double amount = Double.parseDouble(txtTopUpAmount.getEditableText().toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            if (amount < 1000) {
                builder.setMessage("Số tiền nạp tối thiểu là 1000 VND");
                builder.setPositiveButton("OK", (dialogInterface, i) -> {});
                builder.show();
                return;
            }

            builder.setMessage(String.format("Xác nhận nạp %,.0f VND vào ví ?", amount));
            builder.setPositiveButton("Xác nhận", (dialogInterface, i) -> {
                AccountService service = new AccountService(this, null);
                service.topUp(amount);
            });
            builder.setNegativeButton("Hủy", (dialogInterface, i) -> {

            });
            builder.show();
        });
    }
}