package com.example.pproject.util;

import com.example.pproject.model.NoticeBoard;
import com.example.pproject.model.Review;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.IndexRespDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import lombok.Getter;
import lombok.Value;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("/index")
    Call<IndexRespDto> 홈스토어테마리스트가져오기();
//    Call<List<Store>> 홈스토어목록가져오기(
////            @Query("sort_by") String sort_by, // query안에 sortby가 key 뒤에가 value
////            @Query("limit") int limit
//    );
//    Call<List<Theme>> 홈테마목록가져오기();




    @GET("store")
    Call <List<Store>> 스토어목록가져오기();

    @GET("theme")
    Call <List<Theme>> 테마목록가져오기();

    @GET("store/{id}")
    Call<Store> 스토어디테일보기(@Path(value = "id", encoded = true) int id);

    @GET("store/{id}")
    Call<List<Review>> 스토어디테일리뷰보기(@Path(value = "id", encoded = true) int id);

    @GET("theme/{id}")
    Call<Theme> 테마디테일보기(@Path(value = "id", encoded = true) int id);

    @GET("theme/{id}")
    Call<List<Review>> 테마디테일리뷰보기(@Path(value = "id", encoded = true) int id);

    @GET("notice")
    Call<List<NoticeBoard>> 공지사항보기();



}
