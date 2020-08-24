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
import com.example.pproject.model.Theme;
import com.example.pproject.view.DetailStoreActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {
    private static final String TAG = "StoreDetailAdapter";
    private List<Theme> themeList = new ArrayList<>();
    private DetailStoreActivity detailStoreActivity;

    public NoticeAdapter() {
    }

    public NoticeAdapter(DetailStoreActivity detailStoreActivity) {
        this.detailStoreActivity = detailStoreActivity;
    }

    public void addItem(Theme theme) {
        themeList.add(theme);
    }

    public void addItems(List<Theme> themeList) {
        this.themeList = themeList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_store_detail_theme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Theme theme = themeList.get(position);
        holder.storeDetailThemePoint.setText(Float.toString(theme.getRating()/2));
        holder.storeDetailThemeTitle.setText(theme.getName());
        Picasso.get().load("http://www.yologuys.com/Escape_img/company/668.jpg").into(holder.storeDetailThemeImage);
   //     Picasso.get().load(store.getStoreImg()).into(holder.storeDetailThemeImage);


    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView storeDetailThemeImage;
        private TextView storeDetailThemePoint, storeDetailThemelevel , storeDetailThemeTitle;


        public MyViewHolder(final View itemView) {
            super(itemView);

            storeDetailThemePoint = itemView.findViewById(R.id.store_detail_theme_point);
            storeDetailThemeImage = itemView.findViewById(R.id.store_detail_theme_image);
            storeDetailThemelevel = itemView.findViewById(R.id.store_detail_theme_level);
            storeDetailThemeTitle = itemView.findViewById(R.id.store_detail_theme_title);

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
