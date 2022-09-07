package com.death.foodorderingprm392.data;

public class Order {
    private String id;
    private long userId;
    private String purchaseDate;
    private double total;

    public Order(String id, long userId, String purchaseDate, double total) {
        this.id = id;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
