package com.example.pproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.Store;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.fragment.HomeFragment;

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



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_home_store,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Store store = storeList.get(position);
        holder.homeLocation.setText(store.getLocation()+"");
        holder.homeStar.setText(Float.toString(store.getRating()));
        //holder.storeImage.setImageDrawable(store.getStoreImg());

    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView storeImage;
        private TextView homeStar, homeLocation;


        public MyViewHolder(final View itemView) {
            super(itemView);

            storeImage = itemView.findViewById(R.id.store_intro);
            homeStar = itemView.findViewById(R.id.home_star);
            homeLocation = itemView.findViewById(R.id.home_location);

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
