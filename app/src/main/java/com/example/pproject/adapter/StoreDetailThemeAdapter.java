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
import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.ReviewRespDto;
import com.example.pproject.model.dto.ThemeRespDto;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.DetailThemeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailThemeAdapter extends RecyclerView.Adapter<StoreDetailThemeAdapter.MyViewHolder> {
    private static final String TAG = "StoreDetailThemeAdapter";
    private List<ThemeRespDto> themeRespDtos = new ArrayList<>();
    private DetailStoreActivity detailStoreActivity;

    public StoreDetailThemeAdapter() {

    }

    public StoreDetailThemeAdapter(DetailStoreActivity detailStoreActivity) {
        this.detailStoreActivity = detailStoreActivity;
    }

    public void addItem(ThemeRespDto themeRespDto) {
        themeRespDtos.add(themeRespDto);
    }

    public void addItems(List<ThemeRespDto> themeRespDtos) {
        this.themeRespDtos = themeRespDtos;
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
        ThemeRespDto themeRespDto = themeRespDtos.get(position);
        holder.setThemeRespDto(themeRespDto);
        holder.storeDetailThemePoint.setText(Float.toString(themeRespDto.getRating()));
        holder.storeDetailThemeTitle.setText(themeRespDto.getName());
        Picasso.get().load(themeRespDto.getThemeImg()).into(holder.storeDetailThemeImage);
       // Picasso.get().load("http://222.234.36.82:58004"+themeRespDto.getThemeImg()).into(holder.storeDetailThemeImage);


    }

    @Override
    public int getItemCount() {
        return themeRespDtos.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ThemeRespDto themeRespDto;
        private ImageView storeDetailThemeImage;
        private TextView storeDetailThemePoint, storeDetailThemelevel , storeDetailThemeTitle;

        public void setThemeRespDto(ThemeRespDto themeRespDto) {
            this.themeRespDto = themeRespDto;
        }

        public MyViewHolder(final View itemView) {
            super(itemView);

            storeDetailThemePoint = itemView.findViewById(R.id.store_detail_theme_point);
            storeDetailThemeImage = itemView.findViewById(R.id.store_detail_theme_image);
            storeDetailThemelevel = itemView.findViewById(R.id.store_detail_theme_level);
            storeDetailThemeTitle = itemView.findViewById(R.id.store_detail_theme_title);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(itemView.getContext(), DetailThemeActivity.class);
//
//                    v.getContext().startActivity(intent);
//                }
//            });

        }

    }
}
