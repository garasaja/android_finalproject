package com.example.pproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pproject.R;
import com.example.pproject.model.User;
import com.example.pproject.viewmodel.join.JoinViewModel;
import com.example.pproject.viewmodel.theme.ThemeViewModel;

public class JoinActivity extends AppCompatActivity {
    private EditText etJoinUsername,etJoinPassword,etJoinEmail;
    private Button btnJoinSignin;
    private JoinViewModel joinViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        etJoinUsername = findViewById(R.id.et_join_username);
        etJoinPassword = findViewById(R.id.et_join_password);
        etJoinEmail = findViewById(R.id.et_join_email);
        btnJoinSignin = findViewById(R.id.btn_join_signin);




        btnJoinSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinViewModel = ViewModelProviders.of(JoinActivity.this).get(JoinViewModel.class);
                joinViewModel.회원가입하기(etJoinUsername.getText().toString(),etJoinEmail.getText().toString(),etJoinPassword.getText().toString());
                Toast.makeText(JoinActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
