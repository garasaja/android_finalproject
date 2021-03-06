package com.example.pproject.viewmodel.themedetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.model.dto.ThemeDetailRespDto;
import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;
import com.example.pproject.model.Theme;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ThemeDetailRepository {

    private static final String TAG = "ThemeDetailRepository";

    Retrofit retrofit;

    private MutableLiveData <ThemeDetailRespDto> mutableLiveData;

    public ThemeDetailRepository() {
        retrofit = RetrofitHelper.getRetrofit();
        mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<ThemeDetailRespDto> initData() {
        return mutableLiveData;
    }

    public void getTheme(int id) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ThemeDetailRespDto> call = retrofitService.테마디테일보기(id);

        call.enqueue(new Callback<ThemeDetailRespDto>() {
            @Override
            public void onResponse(Call<ThemeDetailRespDto> call, Response<ThemeDetailRespDto> response) {
                ThemeDetailRespDto themeDetailRespDto = response.body();
                mutableLiveData.setValue(themeDetailRespDto);
            }

            @Override
            public void onFailure(Call<ThemeDetailRespDto> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });

//        call.enqueue(new Callback<List<Theme>>() {
//            @Override
//            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
//                List<Theme> themeList = response.body();
//                mutableLiveData.setValue(themeList);
//            }
//
//            @Override
//            public void onFailure(Call<List<Theme>> call, Throwable t) {
//                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
//            }
//        });
    }
}
