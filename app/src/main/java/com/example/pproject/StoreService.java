package com.example.pproject;

import com.example.pproject.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StoreService {

    @GET("list_movies.json")
    Call <List<Store>> 스토어목록가져오기(
            @Query("sort_by") String sort_by, // query안에 sortby가 key 뒤에가 value
            @Query("limit") int limit

    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://yts.mx/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
