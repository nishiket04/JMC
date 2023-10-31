package com.nishiket.jmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nishiket.jmc.user.MainActivity;
import com.nishiket.jmc.viewmodel.AuthViewModel;

import java.lang.annotation.Annotation;

public class SplashScreen extends AppCompatActivity {
   private Animation pop;
   private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // finding logo and animation ids
        logo=findViewById(R.id.pic);
        pop= AnimationUtils.loadAnimation(this,R.anim.splash_screen);

        // passing Intent
        Intent toRe = new Intent(SplashScreen.this, login.class);
        Intent toHome = new Intent(SplashScreen.this, MainActivity.class);
        // all Intent Ends

        // setup animation on logo
        logo.startAnimation(pop);

        // Setup viewModel
        AuthViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);

        // background thread to delay activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewModel.getCurrentUser() != null){ // if there is any user logged In then
                    startActivity(toHome); // goto home
                }else {
                    startActivity(toRe); // goto login page
                }

                finish(); // kill the activity from stack
            }
        },4000); // delay by 4sec

    } // onCreate Ends
} //Splash Screen Ends