package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.death.foodorderingprm392.utils.AppBarUtil;

public class PaymentCompleteActivity extends AppCompatActivity {

    Button btnOk;
    String returnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_complete);

        returnIntent = getIntent().getStringExtra("RETURN_INTENT");

        btnOk = findViewById(R.id.btnPaymentOk);
        btnOk.setOnClickListener(view -> {
            if (returnIntent == "TOP_UP") {
                Intent topUp = new Intent(this, TopUpActivity.class);
                startActivity(topUp);
            }
            this.finish();
        });
    }
}