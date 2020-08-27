package com.example.pproject.adapter;

import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.Hometheme;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;
import com.example.pproject.view.DetailStoreActivity;
import com.example.pproject.view.DetailThemeActivity;
import com.example.pproject.view.fragment.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeThemeAdapter extends RecyclerView.Adapter<HomeThemeAdapter.MyViewHolder> {
    private static final String TAG = "HomeThemeAdapter";
    private List<Theme> themeList = new ArrayList<>();
    private HomeFragment homeFragment;
    private final int limit = 6;


    public HomeThemeAdapter() {
        this.homeFragment = homeFragment;
    }

    public HomeThemeAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
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
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_home_theme,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Theme theme = themeList.get(position);
        holder.themeLevel.setText(Integer.toString(theme.getLevel()));
        Log.d(TAG, "onBindViewHolder: "+theme.getName());
        holder.setTheme(theme);
        holder.themeTitle.setText(theme.getName());
        holder.themePoint.setText(Float.toString(theme.getRating()));
////        holder.themePoint.setText(theme.getRating()/2);
      //  Picasso.get().load("http://www.yologuys.com/Escape_img/company/668.jpg").into(holder.themeImage);
    //   Picasso.get().load(theme.getThemeImg().replace("192.168.0.21:8080","222.234.36.82:58004")).into(holder.themeImage);
        Picasso.get().load(theme.getThemeImg()).into(holder.themeImage);
        Log.d(TAG, "onBindViewHolder: " + theme.getThemeImg());
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView themeImage;
        private Button themeFavoriteBtn;
        private TextView themePoint;
        private TextView themeLevel;
        private TextView themeTitle;
        private Theme theme;
//        private ImageView storeIntro;

        public void setTheme(Theme theme) {
            this.theme = theme;
        }

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

//            storeIntro = itemView.findViewById(R.id.store_intro);

            themeImage = itemView.findViewById(R.id.theme_image);
            themePoint =  itemView.findViewById(R.id.theme_point);
            themeLevel =  itemView.findViewById(R.id.theme_level);
            themeTitle =  itemView.findViewById(R.id.theme_title);
            themeFavoriteBtn =  itemView.findViewById(R.id.theme_favorite_btn);



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
