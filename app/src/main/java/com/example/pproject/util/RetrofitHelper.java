package com.example.pproject.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
           // .baseUrl("http://192.168.0.21:8080/")
            .baseUrl("http://222.234.36.82:58004/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public RetrofitHelper() { }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
