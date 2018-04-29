package com.niksan.completerecyclerview.webservise;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofiBiulder {

    private static final String URL = "http://192.168.43.200";
    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retrofit;
    }
}
