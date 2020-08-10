package com.example.pproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class Home extends Fragment {
    private static final String TAG = "Home";
    private RecyclerView rvHomeStore,rvHometheme;
    CarouselView carouselView;
    private ImageView storeIntro;

    int[] sampleImages = {R.drawable.main1, R.drawable.main2, R.drawable.main3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.home,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);
        rvHometheme = rootView.findViewById(R.id.rv_home_theme);
        storeIntro = rootView.findViewById(R.id.store_intro);
        carouselView = (CarouselView) rootView.findViewById(R.id.carouselView);


        HomeStoreAdapter homeStoreAdapter = new HomeStoreAdapter();
        homeStoreAdapter.addItem(new HomeStore(R.drawable.cafe1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));

        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHomeStore.setAdapter(homeStoreAdapter);

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
