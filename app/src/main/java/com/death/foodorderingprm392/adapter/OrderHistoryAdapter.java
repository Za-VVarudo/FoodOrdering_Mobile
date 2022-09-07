package com.death.foodorderingprm392.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.death.foodorderingprm392.R;
import com.death.foodorderingprm392.data.Order;
import com.death.foodorderingprm392.utils.StringFormatUtil;
import com.death.foodorderingprm392.viewholders.OrderHistoryViewHolder;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryViewHolder> {
    private Context context;
    private ArrayList<Order> list;

    public OrderHistoryAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }


    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lInf = LayoutInflater.from(parent.getContext());
        View view = lInf.inflate(R.layout.order_history_item, parent, false);

        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        Order order = list.get(position);

        holder.getTxtHistoryOrderId().setText("Đơn hàng:\n" + order.getId());
        holder.getTxtHistoryPurchaseDate().setText("Ngày mua:\n" + StringFormatUtil.formatJsonDate(order.getPurchaseDate()));
        holder.getTxtHistoryOrderPrice().setText(String.format("Tổng tiền:\n%,.0f VND", order.getTotal()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Order> getList() {
        return list;
    }

    public void setList(ArrayList<Order> list) {
        this.list = list;
    }
}
