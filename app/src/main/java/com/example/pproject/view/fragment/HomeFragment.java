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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.model.Store;
import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.model.Hometheme;
import com.example.pproject.adapter.HomeThemeAdapter;
import com.example.pproject.R;
import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.IndexRespDto;
import com.example.pproject.viewmodel.HomeViewModel;
import com.example.pproject.viewmodel.StoreViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "Home";
    private RecyclerView rvHomeStore,rvHometheme;
    private HomeStoreAdapter homeStoreAdapter;
    private HomeThemeAdapter homeThemeAdapter;
    private CarouselView carouselView;
    private HomeViewModel homeViewModel1 ,homeViewModel2 , homeViewModel3;

    private List<Store> storeList = new ArrayList<>();
    private List<Theme> themeList = new ArrayList<>();
    private List<IndexRespDto> indexRespDtoList = new ArrayList<>();
   // private ImageView storeIntro;

    int[] carrouselImage = {R.drawable.main1, R.drawable.main2, R.drawable.main3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.home,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);
        rvHometheme = rootView.findViewById(R.id.rv_home_theme);
        carouselView = rootView.findViewById(R.id.carouselView);

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

        homeStoreAdapter = new HomeStoreAdapter();
        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHomeStore.setAdapter(homeStoreAdapter);
        homeStoreAdapter.addItems(new ArrayList<Store>());


        homeThemeAdapter = new HomeThemeAdapter();
        rvHometheme.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHometheme.setAdapter(homeThemeAdapter);
        homeThemeAdapter.addItems(new ArrayList<Theme>());

        homeViewModel3 = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel3.subscribe3().observe(this, new Observer<IndexRespDto>() {
            @Override
            public void onChanged(IndexRespDto indexRespDto) {

//                for (Theme theme : indexRespDto.getThemes()){
//                    Log.d(TAG, "onChanged: "+theme.getName());
//                }

                homeStoreAdapter.addItems(indexRespDto.getStores());
                homeStoreAdapter.notifyDataSetChanged();
                homeThemeAdapter.addItems(indexRespDto.getThemes());
                homeThemeAdapter.notifyDataSetChanged();
            }
        });
        homeViewModel3.initLiveData3();

//        //리사이클러뷰에 연결
//        homeStoreAdapter = new HomeStoreAdapter();
//        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        rvHomeStore.setAdapter(homeStoreAdapter);
//
//        homeViewModel1 = ViewModelProviders.of(this).get(HomeViewModel.class);
//
//        homeViewModel1.subscribe1().observe(this, new Observer<List<Store>>() {
//            @Override
//            public void onChanged(List<Store> storeList) {
//                homeStoreAdapter.addItems(storeList);
//                homeStoreAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        homeViewModel1.initLiveData1();
//
//        //리사이클러뷰에 연결
//        homeThemeAdapter = new HomeThemeAdapter();
//        rvHometheme.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        rvHometheme.setAdapter(homeThemeAdapter);
//
//        homeViewModel2 = ViewModelProviders.of(this).get(HomeViewModel.class);
//
//        homeViewModel2.subscribe2().observe(this, new Observer<List<Theme>>() {
//            @Override
//            public void onChanged(List<Theme> themeList) {
//                homeThemeAdapter.addItems(themeList);
//                homeThemeAdapter.notifyDataSetChanged();
//            }
//        });
//
//        homeViewModel2.initLiveData2();

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
