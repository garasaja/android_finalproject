package com.example.pproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.RetrofitService;
import com.example.pproject.adapter.HomeStoreAdapter;
import com.example.pproject.R;
import com.example.pproject.adapter.StoreAdapter;
import com.example.pproject.adapter.ThemeAdapter;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThemeFragment extends Fragment {
    private static final String TAG = "ThemeFragment";
    private RecyclerView rvTheme;
    private ThemeAdapter themeAdapter;
    private ImageButton favorite_btn;
    private List<Theme> themeList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.theme,container,false);

        rvTheme = rootView.findViewById(R.id.rv_theme);
        favorite_btn = rootView.findViewById(R.id.theme_favorite_btn);

        /////////////////////////////
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.21:8080/")
                //.baseUrl("http://222.234.36.82:58003/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        final Call<List<Theme>> call = retrofitService.테마목록가져오기();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    themeList = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //리사이클러뷰에 연결
        themeAdapter = new ThemeAdapter();
        rvTheme.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        themeAdapter.addItems(themeList);
        rvTheme.setAdapter(themeAdapter);

        /////////////////////////////


        rvTheme.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvTheme.setAdapter(themeAdapter);

        return  rootView;
    }
}
