package com.death.foodorderingprm392.utils;

public class StringFormatUtil {
    public static String formatJsonDate(String jsonDate) {
        String result = "";

        result = jsonDate.replace('T', ' ');
        int dotPos = result.lastIndexOf('.');

        return result.substring(0, dotPos);
    }
}
