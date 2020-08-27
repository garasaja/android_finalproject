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

import com.example.pproject.model.Store;
import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.adapter.HomeThemeAdapter;
import com.example.pproject.R;
import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.IndexRespDto;
import com.example.pproject.viewmodel.home.HomeViewModel;
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
    private HomeViewModel homeViewModel3;

    private int[] carrouselImage = {R.drawable.main1, R.drawable.main2, R.drawable.main3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.home,container,false);

        init(rootView);
        adapter();
        object();

        return rootView;
    }

    private void object() {
        homeViewModel3 = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel3.subscribe3().observe(this, new Observer<IndexRespDto>() {
            @Override
            public void onChanged(IndexRespDto indexRespDto) {

                homeStoreAdapter.addItems(indexRespDto.getStores());
                homeStoreAdapter.notifyDataSetChanged();
                homeThemeAdapter.addItems(indexRespDto.getThemes());
                homeThemeAdapter.notifyDataSetChanged();
            }
        });
        homeViewModel3.initLiveData3();

        carouselView.setPageCount(carrouselImage.length);
        carouselView.setImageListener(imageListener);
    }

    private void adapter() {
        homeStoreAdapter = new HomeStoreAdapter();
        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHomeStore.setAdapter(homeStoreAdapter);
        homeStoreAdapter.addItems(new ArrayList<Store>());


        homeThemeAdapter = new HomeThemeAdapter();
        rvHometheme.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHometheme.setAdapter(homeThemeAdapter);
        homeThemeAdapter.addItems(new ArrayList<Theme>());
    }

    private void init(ViewGroup rootView) {
        rvHomeStore = rootView.findViewById(R.id.rv_home_store);
        rvHometheme = rootView.findViewById(R.id.rv_home_theme);
        carouselView = rootView.findViewById(R.id.carouselView);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(carrouselImage[position]);

        }
    };
}
