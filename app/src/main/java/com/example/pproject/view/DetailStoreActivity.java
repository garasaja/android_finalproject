package com.example.pproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.adapter.StoreDetailReviewAdapter;
import com.example.pproject.adapter.StoreDetailThemeAdapter;
import com.example.pproject.model.Store;
import com.example.pproject.model.dto.StoreDetailRespDto;
import com.example.pproject.viewmodel.storedetail.StoreDetailViewModel;
import com.squareup.picasso.Picasso;

public class DetailStoreActivity extends AppCompatActivity {
    private static final String TAG = "DetailStoreActivity";

    private Button call,btnReserve , back;
    private TextView storeDetailTitle , storeDetailIntro;
    private StoreDetailThemeAdapter storeDetailThemeAdapter;
    private StoreDetailReviewAdapter storeDetailReviewAdapter;
    private RecyclerView rvDetailStoreTheme, rvDetailStoreReview;
    private StoreDetailViewModel storeDetailViewModel;
    private ImageView storeDetailImage;

    private int storeId;
    private String homepageurl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);

        init();
        object();
        listener();
    }

    private void listener() {
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
                Log.d(TAG, "onClick: " + homepageurl);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(homepageurl));
//                Intent intent = new Intent(DetailStoreActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

    }

    private void object() {
        Intent intent = getIntent();
        storeId =  intent.getIntExtra("storeId",0);
        homepageurl = intent.getStringExtra("homepage");
        Log.d(TAG, "onCreate: storeid : " + storeId);
        // storeDetailTitle.setText(Integer.toString(getIntent().getIntExtra("storeId",0)));

        //리사이클러뷰에 연결
        storeDetailThemeAdapter =  new StoreDetailThemeAdapter();
        rvDetailStoreTheme.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvDetailStoreTheme.setAdapter(storeDetailThemeAdapter);

        storeDetailReviewAdapter = new StoreDetailReviewAdapter();
        rvDetailStoreReview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        rvDetailStoreReview.setAdapter(storeDetailReviewAdapter);

        storeDetailViewModel = ViewModelProviders.of(this).get(StoreDetailViewModel.class);

        storeDetailViewModel.subscribe1().observe(this, new Observer<StoreDetailRespDto>() {
            @Override
            public void onChanged(StoreDetailRespDto storeDetailRespDto) {
                storeDetailTitle.setText(storeDetailRespDto.getStore().getName());
                storeDetailIntro.setText(storeDetailRespDto.getStore().getInfo());
                //Picasso.get().load("http://222.234.36.82:58004"+storeDetailRespDto.getStore().getStoreImg()).into(storeDetailImage);
                Picasso.get().load(storeDetailRespDto.getStore().getStoreImg()).into(storeDetailImage);
                storeDetailThemeAdapter.addItems(storeDetailRespDto.getThemes());
                storeDetailReviewAdapter.addItems(storeDetailRespDto.getReviews());
                Log.d(TAG, "onChanged: 리뷰보기 " + storeDetailRespDto.getReviews());
                Log.d(TAG, "onChanged: gettheme는" + storeDetailRespDto.getThemes());
                storeDetailThemeAdapter.notifyDataSetChanged();
            }
        });

        storeDetailViewModel.initLiveData1(storeId);
    }

    private void init() {
        call = findViewById(R.id.call);
        btnReserve = findViewById(R.id.btn_reserve);
        storeDetailTitle = findViewById(R.id.store_detail_title);
        storeDetailIntro = findViewById(R.id.store_detail_intro);
        rvDetailStoreTheme = findViewById(R.id.rv_store_detail_theme);
        rvDetailStoreReview = findViewById(R.id.rv_detail_review);
        storeDetailImage = findViewById(R.id.store_detail_image);
        back = findViewById(R.id.back);


    }
}
