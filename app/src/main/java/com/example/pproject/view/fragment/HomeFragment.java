package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.StoreService;
import com.example.pproject.model.Store;
import com.example.pproject.HomeStoreAdapter;
import com.example.pproject.model.Hometheme;
import com.example.pproject.HomethemeAdapter;
import com.example.pproject.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "Home";
    private RecyclerView rvHomeStore,rvHometheme;
    private HomeStoreAdapter homeStoreAdapter;
    CarouselView carouselView;
   // private ImageView storeIntro;

    int[] sampleImages = {R.drawable.main1, R.drawable.main2, R.drawable.main3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.home,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);
        rvHometheme = rootView.findViewById(R.id.rv_home_theme);
        //storeIntro = rootView.findViewById(R.id.store_intro);
        carouselView = (CarouselView) rootView.findViewById(R.id.carouselView);


        homeStoreAdapter = new HomeStoreAdapter();
//        homeStoreAdapter.addItem(new Store(R.drawable.cafe1));
//        homeStoreAdapter.addItem(new Store(R.drawable.main2));
//        homeStoreAdapter.addItem(new Store(R.drawable.main3));
//        homeStoreAdapter.addItem(new Store(R.drawable.main1));
//        homeStoreAdapter.addItem(new Store(R.drawable.main2));
//        homeStoreAdapter.addItem(new Store(R.drawable.main3));

        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        //rvHomeStore.setAdapter(homeStoreAdapter);

        StoreService storeService = StoreService.retrofit.create(StoreService.class);
        Call<List<Store>> call = storeService.스토어목록가져오기("rating",7);
        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                if(response.isSuccessful() == true) {
                    List<Store> store = response.body();
                    //리사이클러뷰에 연결
                    homeStoreAdapter.addItems(store);
                    rvHomeStore.setAdapter(homeStoreAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {

            }
        });

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

        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);



        return rootView;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


}
