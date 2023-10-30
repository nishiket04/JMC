package com.nishiket.jmc.user;

import static com.nishiket.jmc.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.nishiket.jmc.views.ActiveFragment;
import com.nishiket.jmc.views.HomeFragment;
import com.nishiket.jmc.views.NotificationFragment;
import com.nishiket.jmc.views.OtherFragment;
import com.nishiket.jmc.views.ProfileFragment;

public class MainActivity extends AppCompatActivity {
   private TextView txt;
    private Toolbar toolbar;
    private  BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        assignId();
        loadHome();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                if(id== R.id.nav_home){
                    loadFrag(new HomeFragment(),true);
                } else if (id == R.id.nav_other) {
                    loadFrag(new OtherFragment(),false);
                } else if (id == R.id.nav_notification) {
                    loadFrag(new NotificationFragment(),false);
                } else if (id == R.id.nav_profile) {
                    loadFrag(new ProfileFragment(),false);
                }

                return true;
            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(string.app_name_caps);
        toolbar.setTitleTextColor(getColor(color.white));

    }

    private void loadHome() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(id.fragment_contain,new HomeFragment());
        ft.commit();
    }

    private void loadFrag(Fragment fragment,boolean flag) {
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        if(flag == true){
            ft.remove(fragment);
            ft.replace(R.id.fragment_contain,fragment);
            fm.popBackStack(fragment.getId(),FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(String.valueOf(fragment.getId()));
        }else {
            ft.replace(id.fragment_contain,fragment);
        }
        ft.addToBackStack(null);
        ft.commit();
    }

    private void assignId() {
        toolbar=findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }

}