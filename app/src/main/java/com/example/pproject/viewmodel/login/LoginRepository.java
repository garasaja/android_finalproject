package com.example.pproject.viewmodel.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.model.User;
import com.example.pproject.model.dto.LoginRequestDto;
import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {

    private static final String TAG = "JoinRepository";

    Retrofit retrofit;

    private MutableLiveData <User> mutableLiveData;

    public LoginRepository() {
        retrofit = RetrofitHelper.getRetrofit();
       // mutableLiveData = new MutableLiveData<>();
    }

//    public LiveData<User> initData() {
//        return mutableLiveData;
//    }

    public void getUser(String username,String password) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<LoginRequestDto> call = retrofitService.로그인하기(username,password);

        call.enqueue(new Callback<LoginRequestDto>() {
            @Override
            public void onResponse(Call<LoginRequestDto> call, Response<LoginRequestDto> response) {
                LoginRequestDto loginRequestDto = response.body();
           //     mutableLiveData.setValue(loginRequestDto);
            }

            @Override
            public void onFailure(Call<LoginRequestDto> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });

    }


}
