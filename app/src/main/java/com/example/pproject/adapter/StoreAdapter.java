package com.example.pproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.Store;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.fragment.HomeFragment;
import com.example.pproject.view.fragment.StoreFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private static final String TAG = "StoreAdapter";
    private List<Store> storeList = new ArrayList<>();
    private StoreFragment storeFragment;

    public StoreAdapter() {
        this.storeFragment = storeFragment;
    }

    public StoreAdapter(HomeFragment homeFragment) {
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
//        holder.homeLocation.setText(store.getLocation()+"");
        holder.tvPoint.setText(Float.toString(store.getRating()/2));
        holder.tvTitle.setText(store.getName());
        Picasso.get().load("http://www.yologuys.com/Escape_img/company/668.jpg").into(holder.ivStoreImage);
   //     Picasso.get().load(store.getStoreImg()).into(holder.storeImage);



    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivStoreImage;
        private TextView tvPoint, tvTitle;
        private Button btnFavorite;


        public MyViewHolder(final View itemView) {
            super(itemView);

            ivStoreImage = itemView.findViewById(R.id.iv_store_image);
            tvPoint = itemView.findViewById(R.id.store_point);
            tvTitle = itemView.findViewById(R.id.store_title);
//            btnFavorite = itemView.findViewById(R.id.store_favorite_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailStoreActivity.class);

                    v.getContext().startActivity(intent);
                }
            });

        }

    }
}
