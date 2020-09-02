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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {
    private EditText etJoinUsername,etJoinPassword,etJoinEmail;
    private Button btnJoinSignin;
    private JoinViewModel joinViewModel;
    private FirebaseAuth auth;
    private FirebaseUser CurrentUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        etJoinUsername = findViewById(R.id.et_join_username);
        etJoinPassword = findViewById(R.id.et_join_password);
        etJoinEmail = findViewById(R.id.et_join_email);
        btnJoinSignin = findViewById(R.id.btn_join);




        btnJoinSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etJoinUsername.getText().toString();
                String email = etJoinEmail.getText().toString();
                String password = etJoinPassword.getText().toString();

                joinstart();

            }
        });

    }

    private void joinstart() {
        Toast.makeText(JoinActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
        startActivity(intent);
    }

}
