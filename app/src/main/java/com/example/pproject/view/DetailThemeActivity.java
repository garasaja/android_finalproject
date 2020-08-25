package com.example.pproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.adapter.StoreDetailThemeAdapter;
import com.example.pproject.model.Theme;
import com.example.pproject.viewmodel.themedetail.ThemeDetailViewModel;
import com.squareup.picasso.Picasso;

public class DetailThemeActivity extends AppCompatActivity {
    private static final String TAG = "DetailStore";
    private Button call,btnReserve , back, btnThemeDetailReview;
    private TextView tvThemeDetailTitle , tvThemeDetailIntro;
    private StoreDetailThemeAdapter storeDetailThemeAdapter;
    private RecyclerView rvDetailStoreTheme, rvDetailStoreReview;
    private ThemeDetailViewModel themeDetailViewModel;
    private ImageView ivDetailThemeImage;

    private int themeId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);

        call = findViewById(R.id.call);
        btnReserve = findViewById(R.id.btn_reserve);
        btnThemeDetailReview = findViewById(R.id.btn_theme_detail_review);
        ivDetailThemeImage = findViewById(R.id.iv_detail_theme_image);
        tvThemeDetailTitle = findViewById(R.id.tv_theme_detail_title);
        tvThemeDetailIntro = findViewById(R.id.tv_theme_detail_intro);

        Intent intent = getIntent();
        themeId =  intent.getIntExtra("themeId",0);
       // storeDetailTitle.setText(Integer.toString(getIntent().getIntExtra("storeId",0)));

        //리사이클러뷰에 연결
       // storeDetailThemeAdapter =  new StoreDetailThemeAdapter();
       // rvDetailStoreTheme.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
       // rvDetailStoreTheme.setAdapter(storeDetailThemeAdapter);

        themeDetailViewModel = ViewModelProviders.of(this).get(ThemeDetailViewModel.class);

        themeDetailViewModel.subscribe().observe(this, new Observer<Theme>() {
            @Override
            public void onChanged(Theme theme) {
                tvThemeDetailTitle.setText(theme.getName());
                tvThemeDetailIntro.setText(theme.getIntro());
                Picasso.get().load(theme.getThemeImg()).into(ivDetailThemeImage);
            }
        });
        themeDetailViewModel.initLiveData(themeId);

//
//        storeDetailViewModel.subscribe1().observe(this, new Observer<List<Store>>() {
//            @Override
//            public void onChanged(List<Theme> storeList) {
//                storeDetailThemeAdapter.addItems(storeList);
//                storeDetailThemeAdapter.notifyDataSetChanged();
//            }
//        });

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
                Intent intent = new Intent(DetailThemeActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
