package com.example.pproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Home extends Fragment {
    private RecyclerView rvHomeStore,rvHometheme;
    private ImageView storeIntro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.home,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);
        rvHometheme = rootView.findViewById(R.id.rv_home_theme);
        storeIntro = rootView.findViewById(R.id.store_intro);

        HomeStoreAdapter homeStoreAdapter = new HomeStoreAdapter();
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));

        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHomeStore.setAdapter(homeStoreAdapter);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        HomethemeAdapter homethemeAdapter = new HomethemeAdapter();
        homethemeAdapter.addItem(new Hometheme(R.drawable.main1));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main2));
        homethemeAdapter.addItem(new Hometheme(R.drawable.main3));

        rvHometheme.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvHometheme.setAdapter(homethemeAdapter);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        return rootView;
    }
}
