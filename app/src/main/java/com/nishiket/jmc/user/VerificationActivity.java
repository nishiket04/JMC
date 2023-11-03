package com.nishiket.jmc.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.nishiket.jmc.R;
import com.nishiket.jmc.views.VerificationFragment;

public class VerificationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        assignId(); // assigning Id to the all Views

        // below 3 lines of codes setup ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name_caps);
        toolbar.setTitleTextColor(getColor(R.color.white));
        // Action Bar ends
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a fragment transaction to replace the fragment within the FrameLayout
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.ver_fragmetn_contain, new VerificationFragment());
        transaction.commit();

    }

    private void assignId() {
        toolbar=findViewById(R.id.toolbarVer);
        frameLayout = findViewById(R.id.ver_fragmetn_contain);
    }
}