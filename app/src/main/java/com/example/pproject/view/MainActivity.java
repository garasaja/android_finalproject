package com.example.pproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pproject.view.fragment.MapFragment;
import com.example.pproject.view.fragment.MyMenuFragment;
import com.example.pproject.R;
import com.example.pproject.view.fragment.StoreFragment;
import com.example.pproject.view.fragment.ThemeFragment;
import com.example.pproject.view.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private long backBtnTime = 0;

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private MapFragment mapFragment;
    private MyMenuFragment myMenuFragment;
    private StoreFragment storeFragment;
    private ThemeFragment themeFragment;
    private ViewPager viewPager1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        replaceFragment();
        permissionListener();



     //   viewPager1 = findViewById(R.id.viewPager1);
//        viewPager1.setOffscreenPageLimit(3);

//        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
//        adapter.addItem(home);
////        adapter.addItem(home);
////        adapter.addItem(home);
////        adapter.addItem(home);
////        adapter.addItem(home);
//
//        viewPager1.setAdapter(adapter);

    }

    public void permissionListener() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottomNavi);
        homeFragment = new HomeFragment();
        mapFragment = new MapFragment();
        myMenuFragment = new MyMenuFragment();
        storeFragment = new StoreFragment();
        themeFragment = new ThemeFragment();
    }

    public void replaceFragment() {
        //첫화면 지정해주기
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, homeFragment).commit();
                        return true;
                    case R.id.bottom_map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mapFragment).commit();
                        return true;
                    case R.id.bottom_store:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, storeFragment).commit();
                        return true;
                    case R.id.bottom_mymenu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, myMenuFragment).commit();
                        return true;
                    case R.id.bottom_theme:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, themeFragment).commit();
                        return true;
                }
                return false;
            }
        });
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

//    class MyPagerAdapter extends FragmentStatePagerAdapter {
//        ArrayList<Fragment> items = new ArrayList<Fragment>();
//
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        public void addItem(Fragment item) {
//            items.add(item);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return items.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return items.size();
//        }
//    }
}
