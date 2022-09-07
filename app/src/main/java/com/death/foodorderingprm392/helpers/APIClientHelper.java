package com.death.foodorderingprm392.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientHelper {
    //private static final String API_URL = "http://10.0.2.2:5000/api/";

    private static final String API_URL = "http://foodordering.somee.com/api/";
    private static Retrofit retrofit;

    public static Retrofit getAPIClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(new OkHttpClient()
                            .newBuilder()
                            .cookieJar(new SessionCookieJar())
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    private static class SessionCookieJar implements CookieJar {

        private List<Cookie> cookies = new ArrayList<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            this.cookies.addAll(cookies);
        }


        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            if (!url.encodedPath().endsWith("login") && cookies != null) {
                return cookies;
            }
            return Collections.emptyList();
        }
    }
}

