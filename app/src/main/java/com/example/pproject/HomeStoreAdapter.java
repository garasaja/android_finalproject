package com.example.pproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeStoreAdapter extends RecyclerView.Adapter<HomeStoreAdapter.MyViewHolder> {
    private static final String TAG = "ProfileAdapter";
    private List<HomeStore> items = new ArrayList<>();

    public void addItem(HomeStore homeStore) {
        items.add(homeStore);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_item1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeStore homeStore = items.get(position);
        holder.setItem(homeStore);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        private ImageView storeIntro;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            storeIntro = itemView.findViewById(R.id.store_intro);

        }

        public void setItem(HomeStore homeStore) {
            storeIntro.setImageResource(homeStore.getImage1());
        }
    }
}
