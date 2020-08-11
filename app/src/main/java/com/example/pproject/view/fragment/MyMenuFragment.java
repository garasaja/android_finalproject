package com.example.pproject.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pproject.R;
import com.example.pproject.view.LikeStoreActivity;
import com.example.pproject.view.LikeThemeActivity;
import com.example.pproject.view.NoticeActivity;
import com.example.pproject.view.QuestionActivity;
import com.example.pproject.view.ReserveListActivity;
import com.example.pproject.view.SettingActivity;

public class MyMenuFragment extends Fragment {
    private Button setting,likestore,liketheme,notice,question,reservelist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.mymenu,container,false);

        setting = rootView.findViewById(R.id.mymenu_setting);
        likestore = rootView.findViewById(R.id.mymenu_likestore);
        liketheme = rootView.findViewById(R.id.mymenu_liketheme);
        notice = rootView.findViewById(R.id.mymenu_notice);
        question = rootView.findViewById(R.id.mymenu_question);
        reservelist = rootView.findViewById(R.id.mymenu_reservelist);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        likestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LikeStoreActivity.class);
                startActivity(intent);
            }
        });
        liketheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LikeThemeActivity.class);
                startActivity(intent);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
        reservelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReserveListActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
