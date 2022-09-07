package com.death.foodorderingprm392.data;

public class OrderDetail {
    private String orderId;
    private Order order;
    private String foodStoreId;
    private Food food;
    private double price;
    private int quantity;
    private double total;

    public OrderDetail(String orderId, Order order, String foodStoreId, Food food, double price, int quantity, double total) {
        this.orderId = orderId;
        this.order = order;
        this.foodStoreId = foodStoreId;
        this.food = food;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getFoodStoreId() {
        return foodStoreId;
    }

    public void setFoodStoreId(String foodStoreId) {
        this.foodStoreId = foodStoreId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
