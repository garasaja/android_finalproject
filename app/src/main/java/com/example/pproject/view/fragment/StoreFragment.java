package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;

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

public class StoreFragment extends Fragment implements Filterable {
    private static final String TAG = "StoreFragment";
    private RecyclerView rvStore;
    private StoreAdapter storeAdapter;
    private List<Store> storeList = new ArrayList<>();
    private StoreViewModel storeViewModel;
    private SearchView store_search_view;
    private ImageButton store_favorite_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.store,container,false);

        init(rootView);
        listener();
        adapter();
        object();

        return  rootView;
    }

    private void init(ViewGroup rootView) {
        rvStore = rootView.findViewById(R.id.rv_store);
//        store_search_view = rootView.findViewById(R.id.store_search_view);
        store_favorite_btn = rootView.findViewById(R.id.store_favorite_btn);
    }

    private void listener() {

//        store_favorite_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//              //  store_favorite_btn.getResources().getDrawable(R.drawable.ic_after_favorite_black_24dp);
//
//            }
//        });



//        store_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
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
        Log.d(TAG, "adapter: 알브이 스토어" +rvStore);
        rvStore.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvStore.setAdapter(storeAdapter);
    }



    @Override
    public Filter getFilter() {
        return null;
    }
}
