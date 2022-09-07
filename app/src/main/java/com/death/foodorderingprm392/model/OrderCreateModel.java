package com.death.foodorderingprm392.model;

import java.util.Hashtable;

public class OrderCreateModel {
    private Hashtable<String, Integer> orderDetailList;
    private double total;

    public OrderCreateModel(Hashtable<String, Integer> orderDetailList, double total) {
        this.orderDetailList = orderDetailList;
        this.total = total;
    }

    public Hashtable<String, Integer> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(Hashtable<String, Integer> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
