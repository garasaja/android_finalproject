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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.adapter.ThemeAdapter;
import com.example.pproject.model.Theme;
import com.example.pproject.viewmodel.theme.ThemeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ThemeFragment extends Fragment {
    private static final String TAG = "ThemeFragment";
    private RecyclerView rvTheme;
    private ThemeAdapter themeAdapter;
    private ImageButton favorite_btn;
    private ThemeViewModel themeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.theme,container,false);

        init(rootView);
        adapter();
        object();

        return  rootView;
    }

    private void object() {
        themeViewModel = ViewModelProviders.of(this).get(ThemeViewModel.class);
        themeViewModel.subscribe().observe(this, new Observer<List<Theme>>() {
            @Override
            public void onChanged(List<Theme> themeList) {
                themeAdapter.addItems(themeList);
                themeAdapter.notifyDataSetChanged();
            }
        });

        themeViewModel.initLiveData();
    }

    private void adapter() {
        themeAdapter = new ThemeAdapter();
        rvTheme.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvTheme.setAdapter(themeAdapter);
    }

    private void init(ViewGroup rootView) {
        rvTheme = rootView.findViewById(R.id.rv_theme);
        favorite_btn = rootView.findViewById(R.id.theme_favorite_btn);
    }
}
