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
import com.example.pproject.model.StoreReview;
import com.example.pproject.model.ThemeReview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
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
    private DatabaseReference myRef;
    private FirebaseUser currentUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storereview);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();


        register = findViewById(R.id.register);
        back = findViewById(R.id.back);
        radioGroup = findViewById(R.id.radiogroup);
        reviewemail = findViewById(R.id.reviewemail);

        reviewcontent = findViewById(R.id.reviewcontent);
        auth = FirebaseAuth.getInstance();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("storeId")) {
            final int storeId = intent.getIntExtra("storeId",0);
            Log.d(TAG, "onClick: 스토어아이디가져욤?" + storeId);
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();

                Intent intent = getIntent();
                if (intent.hasExtra("storeId")) {
                    final int storeId = intent.getIntExtra("storeId",0);
                    Log.d(TAG, "onClick: 스토어아이디가져욤?" + storeId);
                    radiobutton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String radioresult = radiobutton.getText().toString();
                    Log.d(TAG, "onClick: 라디오버튼 체크가져옴?" + radioresult);
                    String reviewtext = reviewcontent.getText().toString();
                    String useremail = intent.getStringExtra("useremail");
                    reviewemail.setText(useremail);
                    StoreReview storeReview = new StoreReview(
                        useremail,storeId,radioresult,reviewtext
                    );
                    myRef.child("storeId"+storeId).push().setValue(storeReview);
                    onBackPressed();
                   // finish();

//                    Intent intent1 = new Intent(ReviewWriteActivity.this,DetailStoreActivity.class);
//                    intent1.putExtra("storeId",storeId);
//                    intent1.putExtra("radioresult",radioresult);
//                    intent1.putExtra("reviewtext",reviewtext);
//                    startActivity(intent1);
//                    finish();

                } else if (intent.hasExtra("themeId")) {
                    database = FirebaseDatabase.getInstance();
                    final int themeId = intent.getIntExtra("themeId",0);
                    radiobutton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String radioresult = radiobutton.getText().toString();
                    Log.d(TAG, "onClick: 라디오버튼 체크가져옴?" + radioresult);
                    String reviewtext = reviewcontent.getText().toString();
                    String useremail = intent.getStringExtra("useremail");
                    reviewemail.setText(useremail);

                    ThemeReview themeReview = new ThemeReview(
                            useremail,themeId,radioresult,reviewtext
                    );
                    myRef.child("themeId"+themeId).push().setValue(themeReview);
                    onBackPressed();

                  //  Intent intent1 = new Intent(ReviewWriteActivity.this,DetailStoreActivity.class);
//                    intent1.putExtra("themeId",themeId);
//                    intent1.putExtra("radioresult",radioresult);
//                    intent1.putExtra("reviewtext",reviewtext);
                 //   startActivity(intent1);
//                    finish();
                }

            }
        });





    }
}
