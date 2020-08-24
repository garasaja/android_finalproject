package com.example.pproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.RetrofitHelper;
import com.example.pproject.RetrofitService;
import com.example.pproject.model.Review;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreDetailRepository {

    private static final String TAG = "StoreDetailRepository";

    Retrofit retrofit;

    private MutableLiveData <List<Store>> mutableLiveData1;
    private MutableLiveData <List<Review>> mutableLiveData2;

    public StoreDetailRepository() {
        retrofit = RetrofitHelper.getRetrofit();
        mutableLiveData1 = new MutableLiveData<>();
        mutableLiveData2 = new MutableLiveData<>();
    }

    public LiveData<List<Theme>> initData1() {
        return mutableLiveData1;
    }
    public LiveData<List<Review>> initData2() {
        return mutableLiveData2;
    }

    public void getStore(int id) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Store>> call = retrofitService.(id);

        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                List<Store> storeList = response.body();
                mutableLiveData1.setValue(storeList);
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });
    }

    public void getReview(int id) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Review>> call = retrofitService.테마디테일리뷰보기(id);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                List<Review> reviewList = response.body();
                mutableLiveData2.setValue(reviewList);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });
    }
}