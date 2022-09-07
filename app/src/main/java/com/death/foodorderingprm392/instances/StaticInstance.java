package com.death.foodorderingprm392.instances;

import android.content.Intent;

import com.death.foodorderingprm392.data.Cart;
import com.death.foodorderingprm392.data.User;

public class StaticInstance {
    private static User user;
    private static Cart cart;
    private static Intent mainActivityIntent;

    public static void setUser(User user) {
        if (StaticInstance.user == null) StaticInstance.user = user;
    }

    public static void setCart(Cart cart) {
        if (StaticInstance.cart == null) {
            StaticInstance.cart = cart;
        }
    }

    public static User getUser() {
        return user;
    }

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        };

        return  cart;
    }

    public static Intent getMainActivityIntent() {
        return mainActivityIntent;
    }

    public static void setMainActivityIntent(Intent mainActivityIntent) {
        if (StaticInstance.mainActivityIntent == null) StaticInstance.mainActivityIntent = mainActivityIntent;
    }

    public static void removeUser() {
        StaticInstance.user = null;
    }

    public static void removeCart() {
        StaticInstance.cart = null;
    }

    public static void removeMainActivityIntent() {
        StaticInstance.mainActivityIntent = null;
    }
}
