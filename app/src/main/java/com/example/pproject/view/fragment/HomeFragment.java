package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.RetrofitService;
import com.example.pproject.model.Store;
import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.model.Hometheme;
import com.example.pproject.adapter.HomethemeAdapter;
import com.example.pproject.R;
import com.example.pproject.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private static final String TAG = "Home";
    private RecyclerView rvHomeStore,rvHometheme;
    private HomeStoreAdapter homeStoreAdapter;
    private CarouselView carouselView;

    private List<Store> storeList = new ArrayList<>();
   // private ImageView storeIntro;

    int[] carrouselImage = {R.drawable.main1, R.drawable.main2, R.drawable.main3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.home,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);
        rvHometheme = rootView.findViewById(R.id.rv_home_theme);
        carouselView = (CarouselView) rootView.findViewById(R.id.carouselView);



//        homeStoreAdapter.addItem(new Store(R.drawable.cafe1));
//        homeStoreAdapter.addItem(new Store(R.drawable.main2));
//        homeStoreAdapter.addItem(new Store(R.drawable.main3));
//        homeStoreAdapter.addItem(new Store(R.drawable.main1));
//        homeStoreAdapter.addItem(new Store(R.drawable.main2));
//        homeStoreAdapter.addItem(new Store(R.drawable.main3));


        //rvHomeStore.setAdapter(homeStoreAdapter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.21:8080/")
                //.baseUrl("http://222.234.36.82:58003/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        final Call <List<Store>> call = retrofitService.스토어목록가져오기();

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
        homeStoreAdapter = new HomeStoreAdapter();
        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        homeStoreAdapter.addItems(storeList);
        rvHomeStore.setAdapter(homeStoreAdapter);
        Log.d(TAG, "onResponse: rvHomeStore" + rvHomeStore);

        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(0).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(1).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(2).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(3).getStoreImg());
        Log.d(TAG, "onCreateView: storelist의 이미지 : " + storeList.get(4).getStoreImg());

//        try {
//            storeList = call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "onCreateView: 스레드진입");
//                call.enqueue(new Callback<List<Store>>() {
//                    @Override
//                    public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
//                        Log.d(TAG, "onResponse: 진입함 "+response.isSuccessful());
//                        if (response.isSuccessful()) {
//                            List<Store> storeList = response.body();
//
//                            Log.d(TAG, "onResponse: "+storeList.get(0).getName());
//                            Log.d(TAG, "onResponse: "+storeList.get(1).getName());
//
//                    //리사이클러뷰에 연결
//                    homeStoreAdapter = new HomeStoreAdapter();
//                    rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//                    homeStoreAdapter.addItems(storeList);
//                    rvHomeStore.setAdapter(homeStoreAdapter);
//                    Log.d(TAG, "onResponse: rvHomeStore" + rvHomeStore);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Store>> call, Throwable t) {
//                        t.getStackTrace();
//                        Log.d(TAG, "onFailure: "+t.getMessage());
//                    }
//                });
//            }
//        }).start();

//        Log.d(TAG, "onCreateView: asdf");
//        call.enqueue(new Callback<List<Store>>() {
//            @Override
//            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
//                Log.d(TAG, "onResponse: 진입함 "+response.isSuccessful());
//                if (response.isSuccessful()) {
//                    List<Store> storeList = response.body();
//
//                    Log.d(TAG, "onResponse: "+storeList.get(0).getHomepage());
////                    for (Store store : storeList) {
////                        storeList.add(store);
////                    }
////                    //리사이클러뷰에 연결
////                    homeStoreAdapter = new HomeStoreAdapter();
////                    rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
////                    homeStoreAdapter.addItems(storeList);
////                    rvHomeStore.setAdapter(homeStoreAdapter);
////                    Log.d(TAG, "onResponse: rvHomeStore" + rvHomeStore);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Store>> call, Throwable t) {
//                t.getStackTrace();
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });

//        //리사이클러뷰에 연결
//        homeStoreAdapter = new HomeStoreAdapter();
//        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        homeStoreAdapter.addItems(storeList);
//        rvHomeStore.setAdapter(homeStoreAdapter);
//        Log.d(TAG, "onResponse: rvHomeStore" + rvHomeStore);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        HomethemeAdapter homethemeAdapter = new HomethemeAdapter();
        homethemeAdapter.addItem(new Hometheme(R.drawable.cafe1));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main2));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main3));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main1));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main2));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main3));

        rvHometheme.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHometheme.setAdapter(homethemeAdapter);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        carouselView.setPageCount(carrouselImage.length);

        carouselView.setImageListener(imageListener);




        return rootView;
    } // oncreate view 끝나는곳

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(carrouselImage[position]);
        }
    };


}
