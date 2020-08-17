package com.example.pproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.model.Store;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.fragment.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeStoreAdapter extends RecyclerView.Adapter<HomeStoreAdapter.MyViewHolder> {
    private static final String TAG = "HomeStoreAdapter";
    private List<Store> storeList = new ArrayList<>();
    private HomeFragment homeFragment;

    public HomeStoreAdapter() {
        this.homeFragment = homeFragment;
    }

    public HomeStoreAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public void addItem(Store store) {
        storeList.add(store);
    }

    public void addItems(List<Store> storeList) {
        this.storeList = storeList;
    }

    //한거
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_item1,parent,false);

        return new MyViewHolder(view);
    }
    //한거
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Store store = storeList.get(position);
        holder.homeLocation.setText(store.getLocation()+"");
        holder.homeStar.setText(store.getRating());
        //holder.storeImage.setImageDrawable(store.getStoreImg());

    }
    // 한거
    @Override
    public int getItemCount() {
        return storeList.size();
    }

    //한거
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView storeImage;
        private TextView homeStar, homeLocation;


        public MyViewHolder(View itemView) {
            super(itemView);

            storeImage = itemView.findViewById(R.id.store_intro);
            homeStar = itemView.findViewById(R.id.home_star);
            homeLocation = itemView.findViewById(R.id.home_location);

        }

    }
}
