package com.example.pproject.viewmodel.join;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.model.Store;
import com.example.pproject.model.User;
import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinRepository {

    private static final String TAG = "JoinRepository";

    Retrofit retrofit;

  //  private MutableLiveData <User> mutableLiveData;

    public JoinRepository() {
        retrofit = RetrofitHelper.getRetrofit();
      //  mutableLiveData = new MutableLiveData<>();
    }

//    public LiveData<User> initData() {
//        return mutableLiveData;
//    }

    public void getUserJoin(String username,String email,String password) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<User> call = retrofitService.회원가입하기();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
       //         mutableLiveData.setValue(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });

    }


}
