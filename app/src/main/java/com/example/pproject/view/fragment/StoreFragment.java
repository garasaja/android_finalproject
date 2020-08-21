package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.RetrofitService;
import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.R;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.model.Store;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoreFragment extends Fragment {
    private static final String TAG = "StoreFragment";
    private RecyclerView rvStore;
    private StoreAdapter storeAdapter;
    private List<Store> storeList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.store,container,false);

        rvStore = rootView.findViewById(R.id.rv_store);

        /////////////////////////////
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.21:8080/")
                //.baseUrl("http://222.234.36.82:58003/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        final Call<List<Store>> call = retrofitService.스토어목록가져오기();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storeList = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //리사이클러뷰에 연결
        storeAdapter = new StoreAdapter();
        rvStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        storeAdapter.addItems(storeList);
        rvStore.setAdapter(storeAdapter);
        Log.d(TAG, "onResponse: rvStore" + rvStore);

        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(0).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(1).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(2).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(3).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(4).getStoreImg());
        /////////////////////////////




        return  rootView;
    }
}
