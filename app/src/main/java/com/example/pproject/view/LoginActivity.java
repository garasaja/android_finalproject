package com.example.pproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pproject.R;
import com.example.pproject.model.User;
import com.example.pproject.view.fragment.MyMenuFragment;
import com.example.pproject.viewmodel.join.JoinViewModel;
import com.example.pproject.viewmodel.login.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText et_login_username,et_login_password;
    private Button btn_login_signup,btn_findid,btn_findpassword,btn_login_join;
    private LoginViewModel loginViewModel;
    private String username, password;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        btn_login_signup = findViewById(R.id.btn_login_signup);
        btn_findid = findViewById(R.id.btn_findid);
        btn_findpassword = findViewById(R.id.btn_findpassword);
        btn_login_join = findViewById(R.id.btn_login_join);

        username = et_login_username.getText().toString();
        password = et_login_password.getText().toString();

        btn_login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });

        btn_login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
//
//                loginViewModel.subscribe().observe(LoginActivity.this, new Observer<User>() {
//                    @Override
//                    public void onChanged(User user) {
//                        loginViewModel.로그인하기(etJoinUsername.getText().toString(),etJoinPassword.getText().toString());
//                        Intent intent = new Intent(LoginActivity.this, MyMenuFragment.class);
//                        startActivity(intent);
//                    }
//                });
            }
        });

    }

    public void 로그인하기(User user) {
        loginViewModel.로그인하기(user.getUsername(),user.getPassword());

    }

}
