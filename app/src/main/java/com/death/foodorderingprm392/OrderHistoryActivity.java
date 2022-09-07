package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.death.foodorderingprm392.adapter.OrderHistoryAdapter;
import com.death.foodorderingprm392.services.OrderService;
import com.death.foodorderingprm392.utils.AppBarUtil;

public class OrderHistoryActivity extends AppCompatActivity {

    ImageView btnBack;
    RecyclerView recyclerViewOrderHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        setBtnBack();
        setOrderHistoryInfo();
    }

    private void setBtnBack() {
        btnBack = findViewById(R.id.btnBackHistory);
        btnBack.setOnClickListener(view -> {
            this.finish();
        });
    }

    private void setOrderHistoryInfo() {
        recyclerViewOrderHistory = findViewById(R.id.recyclerViewOrderHistory);
        OrderHistoryAdapter adapter = new OrderHistoryAdapter(this, null);
        recyclerViewOrderHistory.setAdapter(adapter);
        recyclerViewOrderHistory.setLayoutManager(new LinearLayoutManager(this));
        OrderService service = new OrderService(adapter);
        service.getOrderHistory();
    }
}