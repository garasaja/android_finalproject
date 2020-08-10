package com.example.pproject;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class Calendar extends AppCompatActivity {

    private MaterialCalendarView materialCalendarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_calendar);

        materialCalendarView = findViewById(R.id.calendarView);
    }
}
