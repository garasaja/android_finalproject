package com.example.pproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewWriteActivity extends AppCompatActivity {

    private static final String TAG = "ReviewWriteActivity";
    private Button register,back;
    private RadioGroup radioGroup;
    private RadioButton radiobutton;
    private TextView reviewemail;
    private EditText reviewcontent;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storereview);

        register = findViewById(R.id.register);
        back = findViewById(R.id.back);
        radioGroup = findViewById(R.id.radiogroup);
        reviewemail = findViewById(R.id.reviewemail);

        reviewcontent = findViewById(R.id.reviewcontent);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        final int storeId = intent.getIntExtra("storeId",0);
        String getemail = intent.getStringExtra("googleId");
        reviewemail.setText(getemail);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radiobutton = findViewById(radioGroup.getCheckedRadioButtonId());
                 String radioresult = radiobutton.getText().toString();
                Log.d(TAG, "onClick: 라디오버튼 체크가져옴?" + radioresult);
                  String reviewtext = reviewcontent.getText().toString();

                  Intent intent = new Intent(ReviewWriteActivity.this,DetailStoreActivity.class);
                  intent.putExtra("storeId",storeId);
                  intent.putExtra("radioresult",radioresult);
                  intent.putExtra("reviewtext",reviewtext);
                  startActivity(intent);
            }
        });





    }
}
