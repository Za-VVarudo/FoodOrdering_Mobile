package com.death.foodorderingprm392.utils;

import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class AppBarUtil {

    public static void hideAppBar (AppCompatActivity app) {

        app.requestWindowFeature(Window.FEATURE_NO_TITLE);
        app.getSupportActionBar().hide();

    }
}
