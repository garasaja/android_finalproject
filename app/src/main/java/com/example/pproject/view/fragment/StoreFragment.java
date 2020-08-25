package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.RetrofitService;
//import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.R;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.model.Store;
import com.example.pproject.viewmodel.StoreViewModel;
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
    private StoreViewModel storeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.store,container,false);

        rvStore = rootView.findViewById(R.id.rv_store);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    storeList = call.execute().body();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //리사이클러뷰에 연결
        storeAdapter = new StoreAdapter();
        rvStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvStore.setAdapter(storeAdapter);

        storeViewModel = ViewModelProviders.of(this).get(StoreViewModel.class);

        storeViewModel.subscribe().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> storeList) {
                storeAdapter.addItems(storeList);
                storeAdapter.notifyDataSetChanged();
            }
        });

        storeViewModel.initLiveData();

//        Log.d(TAG, "onResponse: rvStore" + rvStore);
//
//        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(0).getStoreImg());
//        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(1).getStoreImg());
//        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(2).getStoreImg());
//        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(3).getStoreImg());
//        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(4).getStoreImg());


        return  rootView;
    }
}
