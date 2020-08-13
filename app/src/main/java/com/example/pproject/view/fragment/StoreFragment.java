package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.model.Store;
import com.example.pproject.HomeStoreAdapter;
import com.example.pproject.R;

public class StoreFragment extends Fragment {

    private RecyclerView rvHomeStore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.store,container,false);

        rvHomeStore = rootView.findViewById(R.id.rv_home_store);

        HomeStoreAdapter homeStoreAdapter = new HomeStoreAdapter();
//        homeStoreAdapter.addItem(new Store(R.drawable.main1));
//        homeStoreAdapter.addItem(new Store(R.drawable.main2));
//        homeStoreAdapter.addItem(new Store(R.drawable.main3));
//        homeStoreAdapter.addItem(new Store(R.drawable.main1));
//        homeStoreAdapter.addItem(new Store(R.drawable.main2));
//        homeStoreAdapter.addItem(new Store(R.drawable.main3));

        rvHomeStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvHomeStore.setAdapter(homeStoreAdapter);
        return  rootView;
    }
}
