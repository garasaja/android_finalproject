package com.example.pproject.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.LikeStoreModel;
import com.example.pproject.model.Store;
import com.example.pproject.model.StoreReview;
import com.example.pproject.view.DetailStoreActivity;
//import com.example.pproject.view.fragment.HomeFragment;
import com.example.pproject.view.LikeStoreActivity;
import com.example.pproject.view.LoginActivity;
import com.example.pproject.view.ReviewWriteActivity;
import com.example.pproject.view.fragment.StoreFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private static final String TAG = "StoreAdapter";
    private List<Store> storeList = new ArrayList<>();
    private StoreFragment storeFragment;

    public StoreAdapter() {
        this.storeFragment = storeFragment;
    }

    public StoreAdapter(StoreFragment storeFragment) {
        this.storeFragment = storeFragment;
    }

    public void addItem(Store store) {
        storeList.add(store);
    }

    public void addItems(List<Store> storeList) {
        this.storeList = storeList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_store,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Store store = storeList.get(position);
     //   holder.homeLocation.setText(store.getLocation()+"");
        holder.setStore(store);
        holder.tvPoint.setText(Float.toString(store.getRating()));
        holder.tvTitle.setText(store.getName());
     //   Picasso.get().load("http://www.yologuys.com/Escape_img/company/668.jpg").into(holder.ivStoreImage);
    //   Picasso.get().load(store.getStoreImg().replace("192.168.0.21:8080","222.234.36.82:58004")).into(holder.ivStoreImage);
       Picasso.get().load(store.getStoreImg()).into(holder.ivStoreImage);
        Log.d(TAG, "onBindViewHolder: " + store.getStoreImg());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private Store store;
        private ImageView ivStoreImage;
        private TextView tvPoint, tvTitle;
        private Button btnFavorite;
        private ImageButton store_favorite_btn;
        private Boolean image = false;
        private FirebaseAuth auth;
        private FirebaseUser currentUser;
        private FirebaseDatabase database;
        private DatabaseReference myRef;

        public void setStore(Store store) {
            this.store = store;
        }

        public MyViewHolder(final View itemView) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: store" + store);
            ivStoreImage = itemView.findViewById(R.id.iv_store_image);
            tvPoint = itemView.findViewById(R.id.store_point);
            tvTitle = itemView.findViewById(R.id.store_title);
            store_favorite_btn = itemView.findViewById(R.id.store_favorite_btn);
//            btnFavorite = itemView.findViewById(R.id.store_favorite_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailStoreActivity.class);
                    Log.d(TAG, "onClick: storeId : " + store);
                    intent.putExtra("storeId", store.getId());
                    intent.putExtra("homepage",store.getHomepage());
                    v.getContext().startActivity(intent);
                }
            });

            store_favorite_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth = FirebaseAuth.getInstance();
                    currentUser = auth.getCurrentUser();
                    database = FirebaseDatabase.getInstance();

                    if (currentUser == null) {
                        Toast.makeText(itemView.getContext(), "로그인을 하셔야합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(itemView.getContext(), LoginActivity.class);
                        v.getContext().startActivity(intent);
                    } else {

                        if (image == false) {
                            store_favorite_btn.setImageResource(R.drawable.ic_after_favorite_black_24dp);
                            image = true;
                            String Uid = currentUser.getUid();
                            Log.d(TAG, "onClick: 유아디진짜" + Uid);

                            myRef = database.getReference();
                            LikeStoreModel likeStoreModel = new LikeStoreModel(Uid,store);

                            myRef.child("likeStore/"+Uid+"/"+store.getId()).push().setValue(likeStoreModel);
                            Log.d(TAG, "onClick: 유아디는? " + Uid);

                            Intent intent = new Intent(itemView.getContext(), LikeStoreActivity.class);
                            intent.putExtra("storeId",store.getId());
                            Log.d(TAG, "onClick: 스토어 아디는 ? " + store.getId());
                            v.getContext().startActivity(intent);

                        } else {
                            store_favorite_btn.setImageResource(R.drawable.ic_before_favorite);
                            image = false;
                            String Uid = currentUser.getUid();
                            myRef.child("likeStore/"+Uid+"/"+store.getId()).removeValue();

                            Intent intent = new Intent(itemView.getContext(), LikeStoreActivity.class);
                            intent.putExtra("storeId",store.getId());
                        }
                    }

                }
            });

        }

    }
}
