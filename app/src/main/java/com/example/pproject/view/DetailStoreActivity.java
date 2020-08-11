package com.example.pproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pproject.R;

public class DetailStoreActivity extends AppCompatActivity {
    private static final String TAG = "DetailStore";
    private Button call,btnReserve;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);

        //Intent intent = getIntent();

        call = findViewById(R.id.call);
        btnReserve = findViewById(R.id.btn_reserve);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
                startActivity(intent);
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailStoreActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
