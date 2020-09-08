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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.adapter.LikeStoreAdapter;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.model.LikeStoreModel;
import com.example.pproject.model.Store;
import com.example.pproject.model.StoreReview;
import com.example.pproject.viewmodel.store.StoreViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LikeStoreActivity extends AppCompatActivity {
    private static final String TAG = "LikeStoreActivity";
    private Button back;
    private RecyclerView rvStore;
    private StoreViewModel storeViewModel;
    private StoreAdapter storeAdapter;
    private LikeStoreAdapter likeStoreAdapter;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ArrayList<Store> arrayList;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private LikeStoreModel likeStoreModel;
    private Store store;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likestore);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        arrayList = new ArrayList<>();

        init();
        listener();
        adapter();
        object();
    }

    private void init() {
        back = findViewById(R.id.back);
        rvStore = findViewById(R.id.rv_like_store);
    }

    private void adapter() {
        storeAdapter = new StoreAdapter();
        Log.d(TAG, "adapter: 알브이스토어" + rvStore);
        rvStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvStore.setAdapter(storeAdapter);
    }

    private void object() {
        String Uid = currentUser.getUid();
        Intent intent = getIntent();
        Log.d(TAG, "object: 인텐트는 ? " + intent);
        int storeId = intent.getIntExtra("storeId", 0);
        Log.d(TAG, "object: 유아이디는 ? " + Uid);
        Log.d(TAG, "object: 스토어 아디는 ? " + storeId);
        databaseReference = database.getReference("likeStore/"+Uid);

        Log.d(TAG, "object: 데이터레퍼런스" + databaseReference);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Store store = snapshot.getValue(Store.class);
                    Log.d(TAG, "onDataChange: arraylist는" + arrayList);
                    arrayList.add(store);
                    Log.d(TAG, "onDataChange: arraylist는" + arrayList);
                }
                storeAdapter.addItems(arrayList);
                storeAdapter.notifyDataSetChanged();
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


}
