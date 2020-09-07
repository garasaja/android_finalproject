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
import com.example.pproject.adapter.StoreDetailThemeAdapter;
import com.example.pproject.adapter.ThemeDetailReviewAdapter;
import com.example.pproject.model.StoreReview;
import com.example.pproject.model.Theme;
import com.example.pproject.model.ThemeReview;
import com.example.pproject.model.dto.ThemeDetailRespDto;
import com.example.pproject.view.fragment.HomeFragment;
import com.example.pproject.viewmodel.themedetail.ThemeDetailViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailThemeActivity extends AppCompatActivity {
    private static final String TAG = "DetailThemeActivity";
    private Button gocafe, btnReserve, back, btnThemeDetailReview;
    private TextView tvThemeDetailTitle, tvThemeDetailIntro;
    private ThemeDetailReviewAdapter themeDetailReviewAdapter;
    private RecyclerView  rvDetailThemeReview;
    private ThemeDetailViewModel themeDetailViewModel;
    private ImageView ivDetailThemeImage;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    private ArrayList<ThemeReview> arrayList;

    private int themeId;
    private Theme theme;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreate: ");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.theme_detail);

            auth = FirebaseAuth.getInstance();
            currentUser = auth.getCurrentUser();
            database = FirebaseDatabase.getInstance();

            arrayList = new ArrayList<>();

            init();
            object();
            listener();

        }

    private void init() {
        // gocafe = findViewById(R.id.gocafe);
        btnReserve = findViewById(R.id.btn_reserve);
        btnThemeDetailReview = findViewById(R.id.btn_theme_detail_review);
        ivDetailThemeImage = findViewById(R.id.iv_detail_theme_image);
        tvThemeDetailTitle = findViewById(R.id.tv_theme_detail_title);
        tvThemeDetailIntro = findViewById(R.id.tv_theme_detail_intro);
        rvDetailThemeReview = findViewById(R.id.rv_theme_detail_review);
    }

        private void object() {
            Intent intent = getIntent();
            themeId = intent.getIntExtra("themeId", 0);
            Log.d(TAG, "onCreate: themeid : " + themeId);


        rvDetailThemeReview.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

            themeDetailViewModel = ViewModelProviders.of(this).get(ThemeDetailViewModel.class);
            themeDetailViewModel.subscribe().observe(this, new Observer<ThemeDetailRespDto>() {
                @Override
                public void onChanged(ThemeDetailRespDto themeDetailRespDto) {
                    Log.d(TAG, "onChanged: 이미지는" + themeDetailRespDto.getTheme().getThemeImg());
                //    Picasso.get().load("http://192.168.0.21:8080" + themeDetailRespDto.getTheme().getThemeImg()).into(ivDetailThemeImage);
                    //Picasso.get().load("http://222.234.36.82:58004"+themeDetailRespDto.getTheme().getThemeImg()).into(ivDetailThemeImage);
                    Picasso.get().load(themeDetailRespDto.getTheme().getThemeImg()).into(ivDetailThemeImage);
                    tvThemeDetailTitle.setText(themeDetailRespDto.getTheme().getName());
                    tvThemeDetailIntro.setText(themeDetailRespDto.getTheme().getIntro());

                    databaseReference = database.getReference("themeId"+themeId);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                ThemeReview themeReview = snapshot.getValue(ThemeReview.class);
                                arrayList.add(themeReview);
                            }
                            themeDetailReviewAdapter.addItems(arrayList);
                            themeDetailReviewAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled: ", databaseError.toException());
                        }
                    });
                    themeDetailReviewAdapter = new ThemeDetailReviewAdapter();
                    rvDetailThemeReview.setAdapter(themeDetailReviewAdapter);

                }

            });
            themeDetailViewModel.initLiveData(themeId);
        }

        private void listener() {
            btnThemeDetailReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentUser = auth.getCurrentUser();
                    if(currentUser == null){
                        Toast.makeText(DetailThemeActivity.this, "로그인을 하셔야합니다.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(DetailThemeActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(),ReviewWriteActivity.class);
                        intent.putExtra("themeId",themeId);
                        intent.putExtra("useremail",auth.getCurrentUser().getEmail());
                        Log.d(TAG, "onClick: 이메일은 ? " + auth.getCurrentUser().getEmail());
                        startActivity(intent);
                    }
                }
            });

        }


    }
