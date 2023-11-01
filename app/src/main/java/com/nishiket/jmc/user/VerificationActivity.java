package com.nishiket.jmc.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.nishiket.jmc.R;

public class VerificationActivity extends AppCompatActivity {

    private Toolbar toolbar;
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

    }

    private void assignId() {
        toolbar=findViewById(R.id.toolbarVer);
    }
}