package com.example.pproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.adapter.StoreDetailThemeAdapter;
import com.example.pproject.model.Store;
import com.example.pproject.viewmodel.StoreDetailViewModel;
import com.example.pproject.viewmodel.StoreViewModel;

import java.util.List;

public class DetailStoreActivity extends AppCompatActivity {
    private static final String TAG = "DetailStore";
    private Button call,btnReserve;
    private TextView storeDetailTitle , storeDetailIntro;
    private StoreDetailThemeAdapter storeDetailThemeAdapter;
    private RecyclerView rvDetailStoreTheme, rvDetailStoreReview;
    private StoreDetailViewModel storeDetailViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);

        call = findViewById(R.id.call);
        btnReserve = findViewById(R.id.btn_reserve);
        storeDetailTitle = findViewById(R.id.store_detail_title);
        storeDetailIntro = findViewById(R.id.store_detail_intro);
        rvDetailStoreTheme = findViewById(R.id.rv_store_detail_theme);
        rvDetailStoreReview = findViewById(R.id.rv_detail_review);

        //리사이클러뷰에 연결
        storeDetailThemeAdapter =  new StoreDetailThemeAdapter();
        rvDetailStoreTheme.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvDetailStoreTheme.setAdapter(storeDetailThemeAdapter);

        storeDetailViewModel = ViewModelProviders.of(this).get(StoreDetailViewModel.class);

        storeDetailViewModel.subscribe1().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> storeList) {

            }
        });

        storeDetailViewModel.subscribe1().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Theme> storeList) {
                storeDetailThemeAdapter.addItems(storeList);
                storeDetailThemeAdapter.notifyDataSetChanged();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
                startActivity(intent);
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailStoreActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
