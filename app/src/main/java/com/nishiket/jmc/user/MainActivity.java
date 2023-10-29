package com.nishiket.jmc.user;

import static com.nishiket.jmc.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nishiket.jmc.R;
import com.nishiket.jmc.adapter.ViewPagerComplainAdapter;

public class MainActivity extends AppCompatActivity {
   private TextView txt;
   private ActionBar actionBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        assignId();

        ViewPagerComplainAdapter viewPagerComplainAdapter = new ViewPagerComplainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerComplainAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(string.app_name_caps);
        toolbar.setTitleTextColor(getColor(color.white));

    }

    private void assignId() {
        toolbar=findViewById(R.id.toolbar);
        txt=findViewById(R.id.txt);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}