package com.example.pproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeShow extends AppCompatActivity {
    private RecyclerView rvHomeStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        rvHomeStore = findViewById(R.id.rv_home_store);

        HomeStoreAdapter homeStoreAdapter = new HomeStoreAdapter();
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main1));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main2));
        homeStoreAdapter.addItem(new HomeStore(R.drawable.main3));

        rvHomeStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvHomeStore.setAdapter(homeStoreAdapter);


    }
}
