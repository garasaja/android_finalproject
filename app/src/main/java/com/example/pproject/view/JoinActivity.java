package com.example.pproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pproject.R;
import com.example.pproject.model.User;
import com.example.pproject.viewmodel.join.JoinViewModel;
import com.example.pproject.viewmodel.theme.ThemeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {
    private EditText etJoinUsername,etJoinPassword,etJoinEmail;
    private Button btnJoinSignin;
    private JoinViewModel joinViewModel;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        etJoinUsername = findViewById(R.id.et_join_username);
        etJoinPassword = findViewById(R.id.et_join_password);
        etJoinEmail = findViewById(R.id.et_join_email);
        btnJoinSignin = findViewById(R.id.btn_join);

        auth = FirebaseAuth.getInstance();

        btnJoinSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etJoinUsername.getText().toString();
                String email = etJoinEmail.getText().toString();
                String password = etJoinPassword.getText().toString();

                joinstart(username,email,password);

            }
        });
    }

    private void joinstart(final String username,String email,String password) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                Toast.makeText(JoinActivity.this, "비밀번호가 간단합니다.", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(JoinActivity.this, "email 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                            } catch(FirebaseAuthUserCollisionException e) {
                                Toast.makeText(JoinActivity.this,"이미존재하는 email 입니다." ,Toast.LENGTH_SHORT).show();
                            } catch(Exception e) {
                                Toast.makeText(JoinActivity.this,"다시 확인해주세요.." ,Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            currentUser = auth.getCurrentUser();

                            Toast.makeText(JoinActivity.this, "가입성공" + username + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });


    }

}
