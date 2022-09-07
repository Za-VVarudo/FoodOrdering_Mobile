package com.death.foodorderingprm392;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.death.foodorderingprm392.adapter.CartAdapter;
import com.death.foodorderingprm392.data.Cart;
import com.death.foodorderingprm392.data.CartItem;
import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.model.OrderCreateModel;
import com.death.foodorderingprm392.services.OrderService;
import com.death.foodorderingprm392.utils.AppBarUtil;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    Button btnCheckOut;
    TextView txtCartWalletAmount, txtCartTotal;
    ImageView btnBack;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.deleteNotificationChannel("Death222");

        setCartItemView();
        setBtnCheckOut();
        setCartInformation();
        setBtnBack();
    }

    private void setCartItemView() {
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        ArrayList<CartItem> list = new ArrayList<>();

        list.addAll(StaticInstance.getCart().getOrderList().values());

        CartAdapter adapter = new CartAdapter(this, list);

        recyclerViewCart.setAdapter(adapter);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setBtnCheckOut() {
        btnCheckOut = findViewById(R.id.btnCheckOut);

        if (StaticInstance.getCart().getTotalItem() == 0) {
            btnCheckOut.setEnabled(false);
        }

        btnCheckOut.setOnClickListener(view -> {
            double walletAmount = StaticInstance.getUser().getWalletAmount();
            Cart cart = StaticInstance.getCart();
            double total = cart.calculateTotalAmount();

            if (walletAmount < total) {
                AlertDialog.Builder builer = new AlertDialog.Builder(this);
                builer.setMessage("Ví của bạn hiện không đủ để thanh toán");

                builer.setPositiveButton("Xác nhận", (dialogInterface, i) -> {

                });
                builer.show();

                return;
            }

            OrderCreateModel model = new OrderCreateModel(cart.toOrderDetailList(), total);
            OrderService service = new OrderService(this, model);
            service.createOrder();

        });
    }

    private void setCartInformation() {
        txtCartWalletAmount = findViewById(R.id.txtCartWalletAmount);
        txtCartTotal = findViewById(R.id.txtCartTotal);

        txtCartTotal.setText(String.format("%,.0f VND", StaticInstance.getCart().calculateTotalAmount()));
        txtCartWalletAmount.setText(String.format("%,.0f VND", StaticInstance.getUser().getWalletAmount()));
    }

    private void setBtnBack() {
        btnBack = findViewById(R.id.btnBackCart);
        btnBack.setOnClickListener(view -> {
            Intent main = StaticInstance.getMainActivityIntent();
            startActivity(main);
            this.finish();
        });
    }
}