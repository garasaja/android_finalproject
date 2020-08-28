package com.example.pproject.util;

import com.example.pproject.model.NoticeBoard;
import com.example.pproject.model.Review;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;
import com.example.pproject.model.User;
import com.example.pproject.model.dto.IndexRespDto;
import com.example.pproject.model.dto.LoginRequestDto;
import com.example.pproject.model.dto.StoreDetailRespDto;
import com.example.pproject.model.dto.ThemeDetailRespDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import lombok.Getter;
import lombok.Value;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("index")
    Call<IndexRespDto> 홈스토어테마리스트가져오기(
        @Query("limit") int limit
    );

    @GET("store")
    Call <List<Store>> 스토어목록가져오기();

    @GET("theme")
    Call <List<Theme>> 테마목록가져오기();

    @GET("store/{id}")
    Call<StoreDetailRespDto> 스토어디테일보기(@Path(value = "id", encoded = true) int id);

    @GET("theme/{id}")
    Call<ThemeDetailRespDto> 테마디테일보기(@Path(value = "id", encoded = true) int id);

    @GET("notice")
    Call<List<NoticeBoard>> 공지사항보기();

    @POST("join")
    Call<User> 회원가입하기();

    @POST("login")
    Call<LoginRequestDto> 로그인하기(String username,String password);




}


