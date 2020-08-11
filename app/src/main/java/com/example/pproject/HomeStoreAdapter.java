package com.example.pproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.model.HomeStore;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeStoreAdapter extends RecyclerView.Adapter<HomeStoreAdapter.MyViewHolder> {
    private static final String TAG = "ProfileAdapter";
    private List<HomeStore> items = new ArrayList<>();
    private Context mContext;
    private HomeFragment homeFragment;

    public HomeStoreAdapter() {
        this.homeFragment = homeFragment;
    }


    public HomeStoreAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        HomeStore homeStore = items.get(position);
        holder.setItem(homeStore);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(home.getContext(),DetailStore.class);
//                home.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        private ImageView storeIntro;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            storeIntro = itemView.findViewById(R.id.store_intro);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        Toast.makeText(v.getContext(), pos+"리사이클러뷰클릭됨", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), DetailStoreActivity.class);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }

        public void setItem(HomeStore homeStore) {
            storeIntro.setImageResource(homeStore.getImage1());
        }
    }
}
