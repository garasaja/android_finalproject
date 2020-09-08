package com.example.pproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.adapter.LikeStoreAdapter;
import com.example.pproject.adapter.LikeThemeAdapter;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.adapter.ThemeAdapter;
import com.example.pproject.model.LikeStoreModel;
import com.example.pproject.model.LikeThemeModel;
import com.example.pproject.model.Theme;
import com.example.pproject.viewmodel.store.StoreViewModel;
import com.example.pproject.viewmodel.theme.ThemeViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LikeThemeActivity extends AppCompatActivity {
    private static final String TAG = "LikeThemeActivity";
    private Button back;
    private RecyclerView rvTheme;
    private ThemeViewModel ThemeViewModel;
    private ThemeAdapter ThemeAdapter;
    private LikeThemeAdapter likeThemeAdapter;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ArrayList<LikeThemeModel> arrayList;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private Theme theme;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liketheme);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        arrayList = new ArrayList<>();

        init();
        listener();
        adapter();
        object();
    }

    private void adapter() {
        likeThemeAdapter = new LikeThemeAdapter();
        Log.d(TAG, "adapter: 알브이스토어" + rvTheme);
        rvTheme.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvTheme.setAdapter(likeThemeAdapter);
    }

    private void object() {
        String Uid = currentUser.getUid();
        Log.d(TAG, "object: 유아이디는 ? " + Uid);
        databaseReference = database.getReference("likeTheme/"+Uid+"/"+theme.getId());
        Log.d(TAG, "object: 테마아디는?" + theme.getId());
        Log.d(TAG, "object: 데이터레퍼런스" + databaseReference);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LikeThemeModel likeThemeModel = snapshot.getValue(LikeThemeModel.class);
                    Log.d(TAG, "onDataChange: arraylist는" + arrayList);
                    arrayList.add(likeThemeModel);
                    Log.d(TAG, "onDataChange: arraylist는" + arrayList);
                }
                likeThemeAdapter.addItems(arrayList);
                likeThemeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", databaseError.toException());
            }
        });
    }

    private void listener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void init() {
        back = findViewById(R.id.back);
        rvTheme = findViewById(R.id.rv_like_theme);
    }
}
