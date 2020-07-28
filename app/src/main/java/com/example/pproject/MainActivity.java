package com.example.pproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Home home;
    private Map map;
    private MyMenu myMenu;
    private Store store;
    private Theme theme;
    private ViewPager viewPager1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        home = new Home();
        map = new Map();
        myMenu = new MyMenu();
        store = new Store();
        theme = new Theme();

        //첫화면 지정해주기
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,home).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,home).commit();
                        return true;
                    case R.id.bottom_map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,map).commit();
                        return true;
                    case R.id.bottom_store:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,store).commit();
                        return true;
                    case R.id.bottom_mymenu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,myMenu).commit();
                        return true;
                    case R.id.bottom_theme:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,theme).commit();
                        return true;
                }
                return false;
            }
        });

        viewPager1 = findViewById(R.id.viewPager1);
        viewPager1.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addItem(home);
//        adapter.addItem(home);
//        adapter.addItem(home);
//        adapter.addItem(home);
//        adapter.addItem(home);

        viewPager1.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(gapTime >= 0 && gapTime <=2000) {
            super.onBackPressed();
        } else {
            backBtnTime = curTime;
            Toast.makeText(this,"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
