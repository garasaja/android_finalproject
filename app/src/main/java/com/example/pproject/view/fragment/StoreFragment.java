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

//import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.R;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.model.Store;
import com.example.pproject.viewmodel.store.StoreViewModel;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {
    private static final String TAG = "StoreFragment";
    private RecyclerView rvStore;
    private StoreAdapter storeAdapter;
    private List<Store> storeList = new ArrayList<>();
    private StoreViewModel storeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.store,container,false);

        init(rootView);
        adapter();
        object();

        return  rootView;
    }

    private void object() {
        storeViewModel = ViewModelProviders.of(this).get(StoreViewModel.class);

        storeViewModel.subscribe().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> storeList) {
                storeAdapter.addItems(storeList);
                storeAdapter.notifyDataSetChanged();
            }
        });

        storeViewModel.initLiveData();
    }

    private void adapter() {
        //리사이클러뷰에 연결
        storeAdapter = new StoreAdapter();
        rvStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvStore.setAdapter(storeAdapter);
    }

    private void init(ViewGroup rootView) {
        rvStore = rootView.findViewById(R.id.rv_store);
    }
}
