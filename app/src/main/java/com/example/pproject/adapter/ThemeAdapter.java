package com.example.pproject.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.LikeStoreModel;
import com.example.pproject.model.LikeThemeModel;
import com.example.pproject.model.Theme;
import com.example.pproject.view.DetailStoreActivity;
//import com.example.pproject.view.fragment.HomeFragment;
import com.example.pproject.view.DetailThemeActivity;
import com.example.pproject.view.LoginActivity;
import com.example.pproject.view.fragment.ThemeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        holder.setTheme(theme);
//        holder.homeLocation.setText(store.getLocation()+"");
        holder.tvPoint.setText(Integer.toString(theme.getRating()));
        holder.tvTitle.setText(theme.getName());
        Picasso.get().load(theme.getThemeImg()).into(holder.ivThemeImage);
    //   Picasso.get().load(theme.getThemeImg().replace("192.168.0.21:8080","222.234.36.82:58004")).into(holder.ivThemeImage);



    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivThemeImage;
        private TextView tvPoint, tvTitle;
        private Theme theme;
        private ImageButton theme_favorite_btn;
        private Boolean image = false;
        private FirebaseAuth auth;
        private FirebaseUser currentUser;
        private FirebaseDatabase database;
        private DatabaseReference myRef;

        public void setTheme(Theme theme) {
            this.theme = theme;
        }

        public MyViewHolder(final View itemView) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: theme : " + theme);
            ivThemeImage = itemView.findViewById(R.id.theme_image);
            tvPoint = itemView.findViewById(R.id.theme_point);
            tvTitle = itemView.findViewById(R.id.theme_title);
            theme_favorite_btn = itemView.findViewById(R.id.theme_favorite_btn);
            //btnFavorite = itemView.findViewById(R.id.store_favorite_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailThemeActivity.class);
                    Log.d(TAG, "onClick: themeId : " + theme);
                    intent.putExtra("themeId", theme.getId());

                    v.getContext().startActivity(intent);
                }
            });

            theme_favorite_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth = FirebaseAuth.getInstance();
                    currentUser = auth.getCurrentUser();
                    database = FirebaseDatabase.getInstance();

                    if (currentUser == null) {
                        Toast.makeText(itemView.getContext(), "로그인을 하셔야합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(itemView.getContext(), LoginActivity.class);
                        v.getContext().startActivity(intent);
                    } else {

                        if (image == false) {
                            theme_favorite_btn.setImageResource(R.drawable.ic_after_favorite_black_24dp);
                            image = true;
                            String Uid = currentUser.getUid();
                            Log.d(TAG, "onClick: 유아디진짜" + Uid);

                            myRef = database.getReference();
                            LikeThemeModel likeThemeModel = new LikeThemeModel(Uid,theme);
                            myRef.child("likeTheme/"+Uid+"/"+theme.getId()).push().setValue(likeThemeModel);
                            Log.d(TAG, "onClick: 유아디는? " + Uid);

                        } else {
                            theme_favorite_btn.setImageResource(R.drawable.ic_before_favorite);
                            image = false;
                            String Uid = currentUser.getUid();
                            myRef.child("likeTheme/"+Uid+"/"+theme.getId()).removeValue();
                        }
                    }
                }
            });

        }

    }
}
