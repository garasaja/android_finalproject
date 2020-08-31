package com.example.pproject.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pproject.R;
import com.example.pproject.view.JoinActivity;
import com.example.pproject.view.LikeStoreActivity;
import com.example.pproject.view.LikeThemeActivity;
import com.example.pproject.view.LoginActivity;
import com.example.pproject.view.NoticeActivity;
import com.example.pproject.view.QuestionActivity;
import com.example.pproject.view.ReserveListActivity;
import com.example.pproject.view.SettingActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class MyMenuFragment extends Fragment {
    private static final String TAG = "MyMenuFragment";
    private Button setting,likestore,liketheme,notice,question,reservelist,btnMymenuLogin;
    private TextView nickname;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.mymenu,container,false);

        init(rootView);
        initobject();
        listener();



        return rootView;
    }

    private void initobject() {
        setting.setVisibility(View.GONE);
        Intent intent = getActivity().getIntent();
        String nickname2 = intent.getStringExtra("nickname");
       // btnMymenuLogin.setText(nickname);
        nickname.setText(nickname2);
        if (nickname.getText() == nickname2) {
            btnMymenuLogin.setVisibility(View.GONE);
            setting.setVisibility(View.VISIBLE);
        }

    }

    private void listener() {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(getContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                nickname.setVisibility(View.GONE);
                btnMymenuLogin.setVisibility(View.VISIBLE);
                setting.setVisibility(View.GONE);
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
//        notice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), NoticeActivity.class);
//                startActivity(intent);
//            }
//        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
//        reservelist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ReserveListActivity.class);
//                startActivity(intent);
//            }
//        });
        btnMymenuLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void init(ViewGroup rootView) {
        setting = rootView.findViewById(R.id.mymenu_setting);
        likestore = rootView.findViewById(R.id.mymenu_likestore);
        liketheme = rootView.findViewById(R.id.mymenu_liketheme);
      //  notice = rootView.findViewById(R.id.mymenu_notice);
        question = rootView.findViewById(R.id.mymenu_question);
      //  reservelist = rootView.findViewById(R.id.mymenu_reservelist);
        btnMymenuLogin = rootView.findViewById(R.id.btn_mymenu_login);
        nickname = rootView.findViewById(R.id.nickname);
        auth = FirebaseAuth.getInstance();
    }
}
