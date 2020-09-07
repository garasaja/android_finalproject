package com.example.pproject.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.LikeStoreModel;
import com.example.pproject.model.Store;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.LikeStoreActivity;
import com.example.pproject.view.fragment.StoreFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import com.example.pproject.view.fragment.HomeFragment;

public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreAdapter.MyViewHolder> {
    private static final String TAG = "LikeStoreAdapter";
    private List<LikeStoreModel> likeStoreModels = new ArrayList<>();
    private LikeStoreActivity likeStoreActivity;

    public LikeStoreAdapter() {
        this.likeStoreActivity = likeStoreActivity;
    }

    public LikeStoreAdapter(LikeStoreActivity likeStoreActivity) {
        this.likeStoreActivity = likeStoreActivity;
    }

    public void addItem(LikeStoreModel likeStoreModel) {
        likeStoreModels.add(likeStoreModel);
    }

    public void addItems(ArrayList<LikeStoreModel> likeStoreModels) {
        this.likeStoreModels = likeStoreModels;
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
        LikeStoreModel likeStoreModel = likeStoreModels.get(position);
     //   holder.homeLocation.setText(store.getLocation()+"");
      //  holder.setStore(likeStoreModel.getStore());
        holder.setLikeStoreModel(likeStoreModel);
        holder.tvPoint.setText(Float.toString(likeStoreModel.getStore().getRating()));
        holder.tvTitle.setText(likeStoreModel.getStore().getName());
     //   Picasso.get().load("http://www.yologuys.com/Escape_img/company/668.jpg").into(holder.ivStoreImage);
    //   Picasso.get().load(store.getStoreImg().replace("192.168.0.21:8080","222.234.36.82:58004")).into(holder.ivStoreImage);
       Picasso.get().load(likeStoreModel.getStore().getStoreImg()).into(holder.ivStoreImage);
        Log.d(TAG, "onBindViewHolder: " + likeStoreModel.getStore().getStoreImg());





    }

    @Override
    public int getItemCount() {
        return likeStoreModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LikeStoreModel likeStoreModel;
        private Store store;
        private ImageView ivStoreImage;
        private TextView tvPoint, tvTitle;
        private Button btnFavorite;
        private FirebaseAuth auth;
        private FirebaseUser currentUser;
        private FirebaseDatabase database;
        private DatabaseReference myRef;


        public void setStore(Store store) {
            this.store = store;
        }
        public void setLikeStoreModel(LikeStoreModel likeStoreModel) {
            this.likeStoreModel = likeStoreModel;
        }


        public MyViewHolder(final View itemView) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: store" + store);
            ivStoreImage = itemView.findViewById(R.id.iv_store_image);
            tvPoint = itemView.findViewById(R.id.store_point);
            tvTitle = itemView.findViewById(R.id.store_title);
            String Uid = currentUser.getUid();
//            btnFavorite = itemView.findViewById(R.id.store_favorite_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(itemView.getContext(), DetailStoreActivity.class);
                    Log.d(TAG, "onClick: storeId : " + store);
                    intent.putExtra("storeId", likeStoreModel.getStore().getId());

                    v.getContext().startActivity(intent);
                }
            });

        }

    }
}
