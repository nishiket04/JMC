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
        logo=findViewById(R.id.pic);
        pop= AnimationUtils.loadAnimation(this,R.anim.splash_screen);
        Intent toRe = new Intent(SplashScreen.this, login.class);
        Intent toHome = new Intent(SplashScreen.this, MainActivity.class);
        logo.startAnimation(pop);

        AuthViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewModel.getCurrentUser() != null){
                    startActivity(toHome);
                }else {
                    startActivity(toRe);
                }

                finish();
            }
        },4000);

    }
}