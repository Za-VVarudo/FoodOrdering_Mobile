package com.death.foodorderingprm392.data;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class CartItem implements Serializable {
    @JsonSetter("FoodStore")
    private FoodStore FoodStore;
    @JsonSetter("Quantity")
    private int Quantity;

    public CartItem() {
    }

    public CartItem(com.death.foodorderingprm392.data.FoodStore foodStore, int quantity) {
        FoodStore = foodStore;
        Quantity = quantity;
    }

    public CartItem(com.death.foodorderingprm392.data.FoodStore foodStore) {
        FoodStore = foodStore;
        Quantity = 1;
    }

    public com.death.foodorderingprm392.data.FoodStore getFoodStore() {
        return FoodStore;
    }

    public void setFoodStore(com.death.foodorderingprm392.data.FoodStore foodStore) {
        FoodStore = foodStore;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getFoodStoreId() {
        return FoodStore.getId();
    }

    public long getStoreId() {
        return FoodStore.getStoreId();
    }

    public double getTotal() { return FoodStore.getPrice() * Quantity; };
}
