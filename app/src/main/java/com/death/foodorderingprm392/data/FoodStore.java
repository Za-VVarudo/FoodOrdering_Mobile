package com.death.foodorderingprm392.data;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class FoodStore implements Serializable {
    @JsonSetter("Id")
    private String id;
    @JsonSetter("StoreId")
    private long storeId;
    @JsonSetter("Store")
    private Store store;
    @JsonSetter("FoodId")
    private long foodId;
    @JsonSetter("Food")
    private Food food;
    @JsonSetter("Price")
    private double price;

    public FoodStore() {
    }

    public FoodStore(String id, long storeId, Store store, long foodId, Food food, double price) {
        this.id = id;
        this.storeId = storeId;
        this.store = store;
        this.foodId = foodId;
        this.food = food;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
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
}
