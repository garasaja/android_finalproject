package com.example.pproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Theme extends Fragment {
    private RecyclerView rvHomeStore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.theme,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);

        HomeStoreAdapter homeStoreAdapter = new HomeStoreAdapter();
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));

        rvHomeStore.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvHomeStore.setAdapter(homeStoreAdapter);

        return  rootView;
    }
}
