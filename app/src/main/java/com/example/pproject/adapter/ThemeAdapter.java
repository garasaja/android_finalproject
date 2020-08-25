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
import com.example.pproject.model.Theme;
import com.example.pproject.view.DetailStoreActivity;
//import com.example.pproject.view.fragment.HomeFragment;
import com.example.pproject.view.DetailThemeActivity;
import com.example.pproject.view.fragment.ThemeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.MyViewHolder> {
    private static final String TAG = "themeAdapter";
    private List<Theme> themeList = new ArrayList<>();
    private ThemeFragment themeFragment;

    public ThemeAdapter() {
        this.themeFragment = themeFragment;
    }

    public ThemeAdapter(ThemeFragment themeFragment) {
        this.themeFragment = themeFragment;
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
        View view = inflater.inflate(R.layout.item_theme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Theme theme = themeList.get(position);
//        holder.homeLocation.setText(store.getLocation()+"");
        holder.tvPoint.setText(Integer.toString(theme.getRating()/2));
        holder.tvTitle.setText(theme.getName());
   //     Picasso.get().load("http://www.yologuys.com/Escape_img/company/668.jpg").into(holder.ivThemeImage);
       Picasso.get().load(theme.getThemeImg().replace("localhost","192.168.0.21")).into(holder.ivThemeImage);



    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivThemeImage;
        private TextView tvPoint, tvTitle;
        private Theme theme;
       // private Button btnFavorite;


        public MyViewHolder(final View itemView) {
            super(itemView);

            ivThemeImage = itemView.findViewById(R.id.theme_image);
            tvPoint = itemView.findViewById(R.id.theme_point);
            tvTitle = itemView.findViewById(R.id.theme_title);
            //btnFavorite = itemView.findViewById(R.id.store_favorite_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailThemeActivity.class);
                    intent.putExtra("themeId", theme.getId());
                    v.getContext().startActivity(intent);
                }
            });

        }

    }
}
