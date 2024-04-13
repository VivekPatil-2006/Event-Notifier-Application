package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.eventapp.databinding.ActivitySplashScreenBinding;

/*
public class SplashScreenActivity extends AppCompatActivity {

    public static int Splash_screen = 3000;
    ImageView logo;
    TextView tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, userselection.class);
                startActivity(intent);
                finish();
            }
        }, Splash_screen);
    }
}
*/



public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        showAnimation();
        showMainActivity();
    }

    private void showAnimation() {
        //Applynig Animations on Icon
       // Animation logoAnim= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.logo_comming);
        //binding.splashscreenicon.startAnimation(logoAnim);
    }

    private void showMainActivity() {
        // Changing screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash_to_Signup=new Intent(SplashScreenActivity.this, userselection.class);
                startActivity(splash_to_Signup);
                finish();
            }
        },2000);
    }
}


