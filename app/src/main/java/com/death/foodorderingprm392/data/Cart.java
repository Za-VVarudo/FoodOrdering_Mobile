package com.death.foodorderingprm392.data;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Hashtable;
import java.util.Iterator;

public class Cart {
    @JsonSetter("OrderList")
    private Hashtable<String, CartItem> OrderList = new Hashtable<>();;

    public Cart() {

    }

    public Hashtable<String, CartItem> getOrderList() {
        return OrderList;
    }

    public void setOrderList(Hashtable<String, CartItem> orderList) {
        OrderList = orderList;
    }

    public boolean isSameStore(CartItem item) {
        if (OrderList.isEmpty()) return true;

        return item.getStoreId() == OrderList.values().stream().findFirst().get().getStoreId();
    }

    public void addToCart(CartItem item) {
        
        if (OrderList.containsKey(item.getFoodStoreId())) {
            
            CartItem value = OrderList.get(item.getFoodStoreId());

            int newQuantity = item.getQuantity() + value.getQuantity();

            value.setQuantity(newQuantity);
                    
        } else {
            
            OrderList.put(item.getFoodStoreId(), item);
        }
    }

    public void removeFromCart(String key) {
        OrderList.remove(key);
    }

    public void updateQuantity(String key, int quantity) {
        CartItem value = OrderList.get(key);

        if (value == null) return;

        if (quantity == 0) {
            OrderList.remove(value.getFoodStoreId());
            return;
        }

        value.setQuantity(quantity);
    }

    public double calculateTotalAmount() {

        Iterator<String> iterator = OrderList.keySet().iterator();

        double total = 0;

        while(iterator.hasNext()) {
            total += OrderList.get(iterator.next()).getTotal();
        }

        return total;
    }

    public int getTotalItem() {

        int total = 0;
        Iterator<String> iterator = OrderList.keySet().iterator();

        while(iterator.hasNext()) {
            total += OrderList.get(iterator.next()).getQuantity();
        }

        return total;
    }

    public Hashtable<String, Integer> toOrderDetailList() {
        if (OrderList == null) {
            return new Hashtable<>();
        }

        Hashtable<String, Integer> detailList = new Hashtable<>();

        OrderList.values().stream().forEach(t -> {
            detailList.put(t.getFoodStoreId(), t.getQuantity());
        });

        return detailList;
    }
}
