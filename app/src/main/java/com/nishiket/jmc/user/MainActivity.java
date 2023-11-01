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
import com.nishiket.jmc.views.VerificationDoneFragment;

public class MainActivity extends AppCompatActivity {
   private TextView txt;
    private Toolbar toolbar;
    private  BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        assignId(); // assigning Id to the all Views

        loadHome(true); // This will load Home Fragment and we are passing True to identify that its first time added

        // below 3 lines of codes setup ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(string.app_name_caps);
        toolbar.setTitleTextColor(getColor(color.white));
        // Action Bar ends

        // Bottom navigation views setup
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId(); // getting selected item id
                if(id== R.id.nav_home){
                    loadHome(false); // passing false to identify that home is selected again
                    loadFrag(new HomeFragment(),true); // passing fragment and true to identify that home is selected again
                } else if (id == R.id.nav_other) {
                    loadFrag(new OtherFragment(),false);  // passing fragment and false to identify that home is not selected again
                } else if (id == R.id.nav_notification) {
                    loadFrag(new NotificationFragment(),false); // passing fragment and false to identify that home is not selected again
                } else if (id == R.id.nav_profile) {
                    loadFrag(new ProfileFragment(),false); // passing fragment and false to identify that home is not selected again
                }
                return true; // return true to change selection of the item
            }
        }); // Bottom navigation Ends


    } // onCreate Ends

    // This Method load home fragment and its accept flag as an boolean value
    private void loadHome(boolean flag) {
        FragmentManager fragmentManager = getSupportFragmentManager(); // getting support of the fragment manager
        FragmentTransaction ft = fragmentManager.beginTransaction(); // set Fragment transection on fragmentmanager

        if(flag){ // if the flag is true then add home as an fragment
            ft.add(id.fragment_contain,new HomeFragment()); // to the fragment contain
            ft.commit();
        }else { // else remove that from fragment contain
            ft.remove(new HomeFragment());
            ft.commit();
        }
    } // loadHome ends

    // This Method load Fragment when item is selected and its accept fragment object and flag
    private void loadFrag(Fragment fragment,boolean flag) {
        FragmentManager fm =getSupportFragmentManager(); // getting support of the fragment manager
        FragmentTransaction ft =fm.beginTransaction(); // set Fragment transection on fragmentmanager

        if(flag == true){ // if the flag is true then replace that fragment with the new fragment and selected item is Home so clear stack
            ft.replace(R.id.fragment_contain,fragment);
            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE); // clear stack included Home
        }else { // else just replace with the new fragment
            ft.replace(id.fragment_contain,fragment);
        }
        ft.addToBackStack(null); // adding to back stack with null
        ft.commit();
    } // loadFrag ends

    // This Method assign Ids to its global variable
    private void assignId() {
        toolbar=findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    } // assignId ends

} // MainActivity Ends