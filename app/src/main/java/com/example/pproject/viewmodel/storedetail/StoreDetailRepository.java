package com.example.pproject.viewmodel.storedetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.model.dto.StoreDetailRespDto;
import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;
import com.example.pproject.model.Store;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreDetailRepository {

    private static final String TAG = "StoreDetailRepository";

    Retrofit retrofit;

    private MutableLiveData <StoreDetailRespDto> mutableLiveData1;
    //private MutableLiveData <List<Review>> mutableLiveData2;

    public StoreDetailRepository() {
        retrofit = RetrofitHelper.getRetrofit();
        mutableLiveData1 = new MutableLiveData<>();
        //mutableLiveData2 = new MutableLiveData<>();
    }

    public LiveData<StoreDetailRespDto> initData1() {
        return mutableLiveData1;
    }
//    public LiveData<List<Review>> initData2() {
//        return mutableLiveData2;
//    }

    public void getStoreDetail(int id) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<StoreDetailRespDto> call = retrofitService.스토어디테일보기(id);

        call.enqueue(new Callback<StoreDetailRespDto>() {
            @Override
            public void onResponse(Call<StoreDetailRespDto> call, Response<StoreDetailRespDto> response) {
                StoreDetailRespDto storeDetailRespDto = response.body();
                mutableLiveData1.setValue(storeDetailRespDto);
            }

            @Override
            public void onFailure(Call<StoreDetailRespDto> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
            }
        });

    }

//    public void getReview(int id) {
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        Call<List<Review>> call = retrofitService.테마디테일리뷰보기(id);
//
//        call.enqueue(new Callback<List<Review>>() {
//            @Override
//            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
//                List<Review> reviewList = response.body();
//                mutableLiveData2.setValue(reviewList);
//            }
//
//            @Override
//            public void onFailure(Call<List<Review>> call, Throwable t) {
//                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
//            }
//        });
//    }
}
