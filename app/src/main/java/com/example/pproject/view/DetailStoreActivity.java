package com.example.pproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.pproject.model.StoreReview;
import com.example.pproject.model.dto.StoreDetailRespDto;
import com.example.pproject.viewmodel.storedetail.StoreDetailViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailStoreActivity extends AppCompatActivity {
    private static final String TAG = "DetailStoreActivity";

    private Button call, btnReserve, back, store_review_write;
    private TextView storeDetailTitle, storeDetailIntro;
    private StoreDetailThemeAdapter storeDetailThemeAdapter;
    private StoreDetailReviewAdapter storeDetailReviewAdapter;
    private RecyclerView rvDetailStoreTheme, rvDetailStoreReview;
    private StoreDetailViewModel storeDetailViewModel;
    private ImageView storeDetailImage;

    private int storeId;
    private String homepageurl;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    private ArrayList<StoreReview> arrayList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        arrayList = new ArrayList<>();


        init();
        object();
        listener();

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
        store_review_write = findViewById(R.id.store_review_write);

    }

    private void object() {
        Intent intent = getIntent();
        storeId = intent.getIntExtra("storeId", 0);
        homepageurl = intent.getStringExtra("homepage");
        Log.d(TAG, "onCreate: storeid : " + storeId);
        // storeDetailTitle.setText(Integer.toString(getIntent().getIntExtra("storeId",0)));

        //리사이클러뷰에 연결
        storeDetailThemeAdapter = new StoreDetailThemeAdapter();
        rvDetailStoreTheme.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvDetailStoreTheme.setAdapter(storeDetailThemeAdapter);


        rvDetailStoreReview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        storeDetailViewModel = ViewModelProviders.of(this).get(StoreDetailViewModel.class);

        storeDetailViewModel.subscribe1().observe(this, new Observer<StoreDetailRespDto>() {
            @Override
            public void onChanged(StoreDetailRespDto storeDetailRespDto) {
                storeDetailTitle.setText(storeDetailRespDto.getStore().getName());
                storeDetailIntro.setText(storeDetailRespDto.getStore().getInfo());
                //Picasso.get().load("http://222.234.36.82:58004"+storeDetailRespDto.getStore().getStoreImg()).into(storeDetailImage);
                Picasso.get().load(storeDetailRespDto.getStore().getStoreImg()).into(storeDetailImage);
                storeDetailThemeAdapter.addItems(storeDetailRespDto.getThemes());
                //      storeDetailReviewAdapter.addItems(storeDetailRespDto.getReviews());

                Log.d(TAG, "onChanged: 리뷰보기 " + storeDetailRespDto.getReviews());
                Log.d(TAG, "onChanged: gettheme는" + storeDetailRespDto.getThemes());
                databaseReference = database.getReference("storeId"+storeId);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            StoreReview storeReview = snapshot.getValue(StoreReview.class);
                            Log.d(TAG, "onDataChange: arraylist는" + arrayList);
                            arrayList.add(storeReview);
                            Log.d(TAG, "onDataChange: arraylist는" + arrayList);
                        }
                        storeDetailReviewAdapter.addItems(arrayList);
                        storeDetailReviewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled: ", databaseError.toException());
                    }
                });
                storeDetailThemeAdapter.notifyDataSetChanged();
            }
        });

        storeDetailReviewAdapter = new StoreDetailReviewAdapter(arrayList,this);
        rvDetailStoreReview.setAdapter(storeDetailReviewAdapter);

        storeDetailViewModel.initLiveData1(storeId);
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

        store_review_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentUser == null) {
                    Toast.makeText(DetailStoreActivity.this, "로그인을 하셔야합니다.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DetailStoreActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), ReviewWriteActivity.class);
                    intent.putExtra("storeId", storeId);
                    intent.putExtra("useremail", auth.getCurrentUser().getEmail());
                    Log.d(TAG, "onClick: 이메일은 ? " + auth.getCurrentUser().getEmail());
                    startActivity(intent);
                }

            }
        });

    }


}
