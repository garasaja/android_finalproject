package com.example.pproject.viewmodel.theme;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;
import com.example.pproject.model.Theme;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ThemeRepository {

    private static final String TAG = "ThemeRepository";

    Retrofit retrofit;

    private MutableLiveData <List<Theme>> mutableLiveData;

    public ThemeRepository() {
        retrofit = RetrofitHelper.getRetrofit();
        mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Theme>> initData() {
        return mutableLiveData;
    }

    public void getTheme() {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Theme>> call = retrofitService.테마목록가져오기();

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
