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

public class HomethemeAdapter extends RecyclerView.Adapter<HomethemeAdapter.MyViewHolder> {
    private static final String TAG = "ProfileAdapter";
    private List<Hometheme> items = new ArrayList<>();

    public void addItem(Hometheme hometheme) {
        items.add(hometheme);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_item2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hometheme hometheme = items.get(position);
        holder.setItem(hometheme);
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

        public void setItem(Hometheme hometheme) {
            storeIntro.setImageResource(hometheme.getImage1());
        }
    }
}
