package com.example.pproject.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.LikeStoreModel;
import com.example.pproject.model.LikeThemeModel;
import com.example.pproject.model.Theme;
import com.example.pproject.view.DetailThemeActivity;
import com.example.pproject.view.LikeThemeActivity;
import com.example.pproject.view.fragment.ThemeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import com.example.pproject.view.fragment.HomeFragment;

public class LikeThemeAdapter extends RecyclerView.Adapter<LikeThemeAdapter.MyViewHolder> {
    private static final String TAG = "LikeThemeAdapter";
    private List<LikeThemeModel> likeThemeModels = new ArrayList<>();
    private LikeThemeActivity likeThemeActivity;

    public LikeThemeAdapter() {
    }

    public LikeThemeAdapter(LikeThemeActivity likeThemeActivity) {
        this.likeThemeActivity = likeThemeActivity;
    }

    public void addItem(LikeThemeModel likeThemeModel) {
        likeThemeModels.add(likeThemeModel);
    }

    public void addItems(List<LikeThemeModel> likeThemeModels) {
        this.likeThemeModels = likeThemeModels;
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
        LikeThemeModel likeThemeModel = likeThemeModels.get(position);
        holder.setLikeThemeModel(likeThemeModel);
//        holder.homeLocation.setText(store.getLocation()+"");
        holder.tvPoint.setText(Integer.toString(likeThemeModel.getTheme().getRating()));
        holder.tvTitle.setText(likeThemeModel.getTheme().getName());
        Picasso.get().load(likeThemeModel.getTheme().getThemeImg()).into(holder.ivThemeImage);
    //   Picasso.get().load(theme.getThemeImg().replace("192.168.0.21:8080","222.234.36.82:58004")).into(holder.ivThemeImage);



    }

    @Override
    public int getItemCount() {
        return likeThemeModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivThemeImage;
        private TextView tvPoint, tvTitle;
        private Theme theme;
        private FirebaseAuth auth;
        private FirebaseUser currentUser;
        private FirebaseDatabase database;
        private DatabaseReference myRef;
        private LikeThemeModel likeThemeModel;
        // private Button btnFavorite;

        public void setTheme(Theme theme) {
            this.theme = theme;
        }

        public void setLikeThemeModel(LikeThemeModel likeThemeModel) {
            this.likeThemeModel = likeThemeModel;
        }

        public MyViewHolder(final View itemView) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: theme : " + theme);
            ivThemeImage = itemView.findViewById(R.id.theme_image);
            tvPoint = itemView.findViewById(R.id.theme_point);
            tvTitle = itemView.findViewById(R.id.theme_title);
            String Uid = currentUser.getUid();
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

        }

    }
}
