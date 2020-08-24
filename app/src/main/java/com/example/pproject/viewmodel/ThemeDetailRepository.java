package com.example.pproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.RetrofitHelper;
import com.example.pproject.RetrofitService;
import com.example.pproject.model.Theme;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ThemeDetailRepository {

    private static final String TAG = "HomeRepository";

    Retrofit retrofit;

    private MutableLiveData <List<Theme>> mutableLiveData;

    public ThemeDetailRepository() {
        retrofit = RetrofitHelper.getRetrofit();
        mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Theme>> initData() {
        return mutableLiveData;
    }

    public void getTheme(int id) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Theme>> call = retrofitService.테마디테일목록보기(id);

        call.enqueue(new Callback<List<Theme>>() {
            @Override
            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
                List<Theme> themeList = response.body();
                mutableLiveData.setValue(themeList);
            }

            @Override
            public void onFailure(Call<List<Theme>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });
    }
}