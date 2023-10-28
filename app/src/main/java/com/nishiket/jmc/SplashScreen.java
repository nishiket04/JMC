package com.nishiket.jmc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.lang.annotation.Annotation;

public class SplashScreen extends AppCompatActivity {
    Animation pop;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo=findViewById(R.id.pic);
        pop= AnimationUtils.loadAnimation(this,R.anim.splash_screen);
        Intent toRe = new Intent(SplashScreen.this, login.class);
        logo.startAnimation(pop);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(toRe);
                finish();
            }
        },4000);

    }
}