package com.example.pproject.viewmodel.store;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;
import com.example.pproject.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreRepository {

    private static final String TAG = "StoreRepository";

    Retrofit retrofit;

    private MutableLiveData <List<Store>> mutableLiveData;

    public StoreRepository() {
        retrofit = RetrofitHelper.getRetrofit();
        mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Store>> initData() {
        return mutableLiveData;
    }

    public void getStore() {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Store>> call = retrofitService.스토어목록가져오기();

        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                List<Store> storeList = response.body();
                mutableLiveData.setValue(storeList);
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });
    }
}
