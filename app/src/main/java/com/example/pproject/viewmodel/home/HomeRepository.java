package com.example.pproject.viewmodel.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pproject.util.RetrofitHelper;
import com.example.pproject.util.RetrofitService;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.IndexRespDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeRepository {

    private static final String TAG = "HomeRepository";

    Retrofit retrofit;

//    private MutableLiveData <List<Store>> mutableLiveData1;
//    private MutableLiveData <List<Theme>> mutableLiveData2;
    private MutableLiveData <IndexRespDto> mutableLiveData3;

    public HomeRepository() {
        retrofit = RetrofitHelper.getRetrofit();
//        mutableLiveData1 = new MutableLiveData<>();
//        mutableLiveData2 = new MutableLiveData<>();
        mutableLiveData3 = new MutableLiveData<>();
    }

//    public LiveData<List<Store>> initStoreData() {
//        return mutableLiveData1;
//    }
//
//    public LiveData<List<Theme>> initThemeData() {
//        return mutableLiveData2;
//    }

    public LiveData<IndexRespDto> initIndexRespDtoData() {
        return mutableLiveData3;
    }

    public void getHome() {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<IndexRespDto> call = retrofitService.홈스토어테마리스트가져오기(5);

        call.enqueue(new Callback<IndexRespDto>() {
            @Override
            public void onResponse(Call<IndexRespDto> call, Response<IndexRespDto> response) {
                IndexRespDto indexRespDto = response.body();
                mutableLiveData3.setValue(indexRespDto);
            }

            @Override
            public void onFailure(Call<IndexRespDto> call, Throwable t) {

            }
        });
    }

//    public void getStore() {
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        Call<List<Store>> call = retrofitService.스토어목록가져오기();
//
//        call.enqueue(new Callback<List<Store>>() {
//            @Override
//            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
//                List<Store> storeList = response.body();
//                mutableLiveData1.setValue(storeList);
//            }
//
//            @Override
//            public void onFailure(Call<List<Store>> call, Throwable t) {
//                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
//            }
//        });
//    }
//
//    public void getTheme() {
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        Call<List<Theme>> call = retrofitService.테마목록가져오기();
//
//        call.enqueue(new Callback<List<Theme>>() {
//            @Override
//            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
//                List<Theme> themeList = response.body();
//                mutableLiveData2.setValue(themeList);
//            }
//
//            @Override
//            public void onFailure(Call<List<Theme>> call, Throwable t) {
//                Log.d(TAG, "onFailure: 실패오류 원인은 : " + t.getMessage());
//            }
//        });
//    }
}
